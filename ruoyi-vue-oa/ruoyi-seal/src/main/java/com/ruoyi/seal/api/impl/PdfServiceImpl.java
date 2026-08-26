package com.ruoyi.seal.api.impl;

import com.itextpdf.text.pdf.security.DigestAlgorithms;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.file.business.api.IFileService;
import com.ruoyi.file.business.module.FileQO;
import com.ruoyi.file.storage.upload.domain.UploadFile;
import com.ruoyi.seal.api.PdfService;
import com.ruoyi.seal.config.SealConfig;
import com.ruoyi.seal.constant.SealConstants;
import com.ruoyi.seal.enums.SealPositionEnum;
import com.ruoyi.seal.utils.EsignUtil;
import com.ruoyi.seal.utils.pdf.PdfUtil;
import com.ruoyi.seal.utils.pdf.param.CompanyPdfAcroFields;
import com.ruoyi.seal.utils.pdf.param.SignParam;
import com.ruoyi.seal.utils.water.WaterUtil;
import com.ruoyi.tools.utils.date.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;

/**
 * @Author wocurr.com
 */
@Slf4j
@Service
public class PdfServiceImpl implements PdfService {

    @Autowired
    private SealConfig config;
    @Autowired
    private IFileService fileService;

    private static final String SIGN_REASON = "电子合同签署 - 本人确认文档内容无误、协议条款有效并自愿签署";

    @Override
    public String createByTemplate(String fileName, String templateFileId, CompanyPdfAcroFields acroFields) {
        try {
            if (StringUtils.isBlank(templateFileId) || acroFields == null) {
                throw new BaseException("参数错误！");
            }
            // 根据模板，创建文件并替换表单域字段值
            InputStream inputStream = getInputStream(templateFileId);
            String fileUrl = PdfUtil.createByTemplate(inputStream, acroFields);
            // 上传到文件服务
            return uploadFile(fileUrl, fileName, true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("创建文件失败！");
        }
    }

    @Override
    public String waterMark(String fileId, String fileName, String waterContent) {
        if (StringUtils.isBlank(fileId) || StringUtils.isBlank(waterContent)) {
            throw new BaseException("参数错误！");
        }
        try {
            // 从文件服务获取文件流
            InputStream inputStream = getInputStream(fileId);
            String fileUrl = WaterUtil.addWaterMark(inputStream, waterContent);
            // 上传到文件服务
            return uploadFile(fileUrl, fileName, true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("添加水印失败！");
        }
    }

    @Override
    public String seamSeal(String fileId, String fileName, String sealImgFileId) {
        try {
            // 从文件服务获取文件流
            InputStream inputStream = getInputStream(fileId);
            InputStream sealInputStream = getInputStream(sealImgFileId);
            // 骑缝章
            String seamSealUrl = PdfUtil.seamSeal(inputStream, sealInputStream, 0.33f);
            // 上传到文件服务并删除本地临时文件
            return uploadFile(seamSealUrl, fileName, true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("盖章失败！");
        }
    }

    @Override
    public String stamp(String fileId, String fileName, String sealImgFileId, String position, int sealPage) {
        if (StringUtils.isBlank(fileId) || StringUtils.isBlank(sealImgFileId)) {
            throw new BaseException("参数错误！");
        }
        try {
            // 从文件服务获取文件流
            InputStream inputStream = getInputStream(fileId);
            // 封装印章信息
            InputStream sealFileInputStream = getInputStream(sealImgFileId);
            SignParam param = getBaseSignParam(false);
            param.setSealPage(sealPage);
            param.setSealImageIn(sealFileInputStream);
            setPosition(position, param);
            // 盖章
            String fileUrl = PdfUtil.stamp(inputStream, param);
            // 上传到文件服务并删除本地临时文件
            return uploadFile(fileUrl, fileName, true);
        } catch (Exception e) {
            throw new BaseException("盖章失败！");
        }
    }
    @Override
    public String stamp(String fileId, String fileName, String sealImgFileId,  float positionX, float positionY, int sealPage, boolean isUsePreviewUrl) {
        if (StringUtils.isBlank(fileId) || StringUtils.isBlank(sealImgFileId)) {
            throw new BaseException("参数错误！");
        }
        try {
            // 从文件服务获取文件流
            InputStream inputStream = getInputStream(fileId, isUsePreviewUrl);
            // 封装印章信息
            InputStream sealFileInputStream = getInputStream(sealImgFileId);
            SignParam param = getBaseSignParam(false);
            param.setSealPage(sealPage);
            param.setSealImageIn(sealFileInputStream);
            // 值越大，代表向x轴坐标平移，缩小（反之，值越小，印章会放大）
            param.setRectllx(0f);
            // 值越大，代表向y轴坐标向上平移（大小不变）
            param.setRectlly(0f);
            // 值越大，代表向x轴坐标向右平移（大小不变）
            param.setRecturx(positionX);
            // 值越大，代表向y轴坐标向上平移（大小不变）
            param.setRectury(positionY);
            // 盖章
            String fileUrl = PdfUtil.stamp(inputStream, param);
            // 上传到文件服务并删除本地临时文件
            return uploadFile(fileUrl, fileName, true);
        } catch (Exception e) {
            throw new BaseException("盖章失败！");
        }
    }

    @Override
    public String stamp(String fileId, String fileName, String sealImgFileId, String position, int sealPage, boolean isSeamSeal, boolean isWatermark, String waterContent) {
        InputStream sealInputStream = null;
        try {
            String waterMarkUrl = "";
            String seamSealUrl = "";
            // 从文件服务获取文件流
            InputStream inputStream = getInputStream(fileId);
            sealInputStream = getInputStream(sealImgFileId);
            byte[] sealABytes = IOUtils.toByteArray(sealInputStream);
            // 添加水印
            if (isWatermark) {
                waterMarkUrl = WaterUtil.addWaterMark(inputStream, waterContent);
                inputStream = Files.newInputStream(Paths.get(waterMarkUrl));
            }
            // 骑缝章
            if (isSeamSeal) {
                seamSealUrl = PdfUtil.seamSeal(inputStream, new ByteArrayInputStream(sealABytes), 0.33f);
                inputStream = Files.newInputStream(Paths.get(seamSealUrl));
            }
            // 封装签章信息
            SignParam param = getBaseSignParam(false);
            param.setSealImageIn(new ByteArrayInputStream(sealABytes));
            param.setSealPage(sealPage);
            setPosition(position, param);
            String pdfUrl = PdfUtil.stamp(inputStream, param);
            // 上传到文件服务并删除本地临时文件
            EsignUtil.deleteFile(waterMarkUrl);
            EsignUtil.deleteFile(seamSealUrl);
            return uploadFile(pdfUrl, fileName, true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("盖章失败！");
        } finally {
            IOUtils.closeQuietly(sealInputStream);
        }
    }

    @Override
    public String stampBothFinal(String fileId, String fileName, String partASealFileId, String partBSealFileId, int sealPage, boolean isSeamSeal, boolean isWatermark, String waterContent) {
        InputStream partASealInputStream = null;
        InputStream partBSealInputStream = null;
        try {
            String waterMarkUrl = "";
            String seamSealAUrl = "";
            String seamSealBUrl = "";
            // 这里的逻辑是：
            // 由上一层处理是否调用此方法，例如，发起方先调用stampSeamAndWaterMark进行水印+骑缝+盖章，接收方看到发起方已处理盖章，再调用此方法，重新生成一遍
            // 从文件服务获取文件流
            InputStream inputStream = getInputStream(fileId);
            // 水印
            if (isWatermark) {
                waterMarkUrl = WaterUtil.addWaterMark(inputStream, waterContent);
                inputStream = Files.newInputStream(Paths.get(waterMarkUrl));
            }
            // 印章文件不会变化，因此做流的复用
            partASealInputStream = getInputStream(partASealFileId);
            partBSealInputStream = getInputStream(partBSealFileId);
            byte[] sealABytes = IOUtils.toByteArray(partASealInputStream);
            byte[] sealBBytes = IOUtils.toByteArray(partBSealInputStream);
            // 骑缝章
            if (isSeamSeal) {
                seamSealAUrl = PdfUtil.seamSeal(inputStream, new ByteArrayInputStream(sealABytes), 0.33f);
                seamSealBUrl = PdfUtil.seamSeal(Files.newInputStream(Paths.get(seamSealAUrl)), new ByteArrayInputStream(sealBBytes), 0.55f);
                inputStream = Files.newInputStream(Paths.get(seamSealBUrl));
            }
            SignParam param = getBaseSignParam(false);
            param.setSealPage(sealPage);

            // 甲方盖章
            param.setSealImageIn(new ByteArrayInputStream(sealABytes));
            setPosition(SealPositionEnum.LEFT.getParticipant(), param);
            String partAPdfUrl = PdfUtil.stamp(inputStream, param);
            // 乙方盖章
            param.setSealImageIn(new ByteArrayInputStream(sealBBytes));
            setPosition(SealPositionEnum.RIGHT.getParticipant(), param);
            String partBPdfUrl = PdfUtil.stamp(Files.newInputStream(Paths.get(partAPdfUrl)), param);

            // 上传到文件服务并删除本地临时文件
            EsignUtil.deleteFile(waterMarkUrl);
            EsignUtil.deleteFile(seamSealAUrl);
            EsignUtil.deleteFile(seamSealBUrl);
            EsignUtil.deleteFile(partAPdfUrl);
            return uploadFile(partBPdfUrl, fileName, true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("盖章失败！");
        } finally {
            IOUtils.closeQuietly(partASealInputStream);
            IOUtils.closeQuietly(partBSealInputStream);
        }
    }

    @Override
    public String signature(String fileId, String fileName, String signBase64Img, String keyword) {
        try {
            InputStream inputStream = getInputStream(fileId);
            SignParam param = getBaseSignParam(true);
            // 设置签名位置，签名一般是乙方、右侧，这里可以根据实际位置自行调整
            // 值越大，代表向x轴坐标平移，缩小（反之，值越小，印章会放大）
            param.setRectllx(10f);
            // 值越大，代表向y轴坐标向上平移（大小不变）
            param.setRectlly(30f);
            // 值越大，代表向x轴坐标向右平移（大小不变）
            param.setRecturx(240f);
            // 值越大，代表向y轴坐标向上平移（大小不变）
            param.setRectury(40f);
            String partBPdfUrl = PdfUtil.writeSign(inputStream, signBase64Img, keyword, param);
            return uploadFile(partBPdfUrl, fileName, true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("签名失败！");
        }
    }

    @Override
    public String encrypt(String fileId, String fileName, String ownerPwd, String userPwd) {
        try {
            InputStream inputStream = getInputStream(fileId);
            String pdfUrl = PdfUtil.encrypt(inputStream, ownerPwd, userPwd);
            return uploadFile(pdfUrl, fileName, true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("文件加密失败！");
        }
    }

    /**
     * 获取基本签章信息
     *
     * @return
     * @throws Exception
     */
    private SignParam getBaseSignParam(boolean hasProvider) throws Exception {
        SignParam signInfo = new SignParam();
        KeyStore ks = null;
        if (hasProvider) {
            BouncyCastleProvider provider = new BouncyCastleProvider();
            Security.addProvider(provider);
            ks = KeyStore.getInstance(config.getSignType(), provider);
            signInfo.setProvider(provider);
        } else {
            ks = KeyStore.getInstance(config.getSignType());
        }
        char[] password = config.getSignPassword().toCharArray();
        ks.load(config.getCertStream(), password);
        String alias = ks.aliases().nextElement();
        PrivateKey pk = (PrivateKey) ks.getKey(alias, password);
        // 得到证书链
        Certificate[] chain = ks.getCertificateChain(alias);
        signInfo.setReason(SIGN_REASON);
        signInfo.setLocation("位置");
        signInfo.setPk(pk);
        signInfo.setChain(chain);
        signInfo.setDigestAlgorithm(DigestAlgorithms.SHA1);
        return signInfo;
    }

    /**
     * 获取文件存储路径
     *
     * @return
     */
    private String getFileStorePath() {
        StringBuilder filePath = new StringBuilder();
        filePath.append(SealConstants.STORE_DIR).append(Constants.SEPARATOR)
                .append(SealConstants.PDF_CONTRACT_DIR)
                .append(LocalDateTimeUtil.formatNow(LocalDateTimeUtil.FORMAT_YMD))
                .append(Constants.SEPARATOR)
                .append(IdUtils.fastSimpleUUID())
                .append(SealConstants.PDF_SUFFIX);
        return filePath.toString();
    }

    /**
     * 获取文件流<br>
     *
     * @param fileId 文件id
     * @return
     */
    private InputStream getInputStream(String fileId) {
        FileQO sealFile = new FileQO();
        sealFile.setFileId(fileId);
        return fileService.getInputStream(sealFile);
    }

    /**
     * 获取文件流
     * @param fileId
     * @param isUsePreviewUrl
     * @return
     */
    private InputStream getInputStream(String fileId, boolean isUsePreviewUrl) {
        FileQO sealFile = new FileQO();
        sealFile.setFileId(fileId);
        sealFile.setUsePreviewUrl(isUsePreviewUrl);
        return fileService.getInputStream(sealFile);
    }

    /**
     * 文件上传到文件服务
     *
     * @param fileUrl
     * @param fileName
     * @param isDelete
     * @return
     */
    private String uploadFile(String fileUrl, String fileName, boolean isDelete) {
        try {
            UploadFile uploadFile = new UploadFile();
            String filePath = getFileStorePath();
            byte[] data = Files.readAllBytes(Paths.get(fileUrl));
            uploadFile.setFilePath(filePath);
            uploadFile.setFilename(fileName);
            uploadFile.setExtendName(SealConstants.PDF_EXT);
            uploadFile.setFileBytes(data);
            String fileId = fileService.uploadFile(uploadFile);
            // 删除临时文件
            if (isDelete) {
                EsignUtil.deleteFile(fileUrl);
            }
            return fileId;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("文件上传失败！");
        }
    }

    /**
     * 设置盖章位置
     *
     * @param position
     * @param param
     */
    private void setPosition(String position, SignParam param) {
        float llx = 280f;
        float lly = 120f;
        float urx = 150f;
        float ury = 200f;
        SealPositionEnum positionEnum = SealPositionEnum.getByParticipant(position);
        switch (positionEnum) {
            case LEFT:
                urx = 140f;
                ury = 220f;
                break;
            case RIGHT:
                urx = 540f;
                ury = 220f;
                break;
            default:
                log.error("盖章位置未定义！");
        }
        // 值越大，代表向x轴坐标平移，缩小（反之，值越小，印章会放大）
        param.setRectllx(llx);
        // 值越大，代表向y轴坐标向上平移（大小不变）
        param.setRectlly(lly);
        // 值越大，代表向x轴坐标向右平移（大小不变）
        param.setRecturx(urx);
        // 值越大，代表向y轴坐标向上平移（大小不变）
        param.setRectury(ury);
    }
}
