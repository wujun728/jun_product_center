package com.ruoyi.seal.utils.pdf;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.security.*;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.seal.utils.Base64Utils;
import com.ruoyi.seal.utils.EsignUtil;
import com.ruoyi.seal.constant.SealConstants;
import com.ruoyi.seal.utils.pdf.param.CompanyPdfAcroFields;
import com.ruoyi.seal.utils.pdf.param.KeyWordBean;
import com.ruoyi.seal.utils.pdf.param.SignParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.util.IOUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


/**
 * pdf盖章工具类<br>
 *
 * @author wocurr.com
 */
@Slf4j
public class PdfUtil {

    /**
     * 字体属性
     */
    private static BaseFont cachedFont;
    /**
     * keystory密码
     */
    private static final String TIME_ZONE = "GMT+8";
    private static final String SIGN_PREFIX = "sig_";
    private static final String TEXT_FONT = "textfont";

    /**
     * 初始化中文字体
     */
    static {
        try {
            cachedFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        } catch (Exception e) {
            log.error("字体初始化失败", e);
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 根据模板生成pdf文件，并替换表单域值
     *
     * @param inputStream 模板文件流
     * @param acroFields  表单域值
     * @return
     */
    public static String createByTemplate(InputStream inputStream, CompanyPdfAcroFields acroFields) {
        String fileUrl = EsignUtil.getAbsolutePath(EsignUtil.createPdfContractUrl());
        PdfStamper stamper = null;
        PdfReader reader = null;
        try {
            reader = new PdfReader(inputStream);
            stamper = new PdfStamper(reader, new FileOutputStream(fileUrl));
            // 填充所有表单域
            Map<String, String> fieldValues = JSON.parseObject(JSON.toJSONString(acroFields),
                    new TypeReference<Map<String, String>>() {
                    });
            AcroFields form = stamper.getAcroFields();
            for (String field : form.getFields().keySet()) {
                if (!fieldValues.containsKey(field)) {
                    continue;
                }
                form.setFieldProperty(field, TEXT_FONT, cachedFont, null);
                // 必须在设置属性后，再设置字段值，否则中文字体会丢失
                form.setField(field, fieldValues.get(field));
            }
            // 使表单不可编辑
            stamper.setFormFlattening(true);
            return fileUrl;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("书签替换异常！");
        } finally {
            if (stamper != null) {
                try {
                    stamper.close();
                } catch (Exception e) { /* 忽略关闭异常 */}
            }
            if (reader != null) {
                reader.close();
            }
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 盖章<br>
     * 注意：如果文件设置了打开密码，再次盖章时，必须要传递密码，否则会造成读取失败
     *
     * @param inputStream 待盖章文件流
     * @param param       盖章参数
     * @return 返回盖章后的文件路径
     */
    public static String stamp(InputStream inputStream, SignParam param) {
        InputStream sealImageIn = null;
        FileOutputStream outputStream = null;
        ByteArrayOutputStream result = null;
        PdfStamper stamper = null;
        PdfReader reader = null;
        try {
            reader = new PdfReader(inputStream);
            // 存在密码时，必须传入
            // reader = new PdfReader(inputStream, param.getPassword().getBytes());
            // 创建签章工具PdfStamper，最后一个参数：是否允许被追加签名：
            // false：只允许被签名一次，多次签名，最后一次有效，验证签名时，前几个会显示无效；
            // true：可以被追加签名，验签工具可以识别出多个签名，每次签名之后文档是否被修改
            result = new ByteArrayOutputStream();
            stamper = PdfStamper.createSignature(reader, result, '\0', null, true);
            // 获取数字签章属性对象
            PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
            appearance.setReason(param.getReason());
            appearance.setLocation(param.getLocation());
            // 生成唯一签名域名称
            String signatureFieldName = SIGN_PREFIX + IdUtils.fastSimpleUUID();
            // 读取印章图片
            sealImageIn = param.getSealImageIn();
            byte[] sealImageByte = IOUtils.toByteArray(sealImageIn);
            Image image = Image.getInstance(sealImageByte);
            float imgSize = 90;
            // 设置印章固定大小
            image.scaleAbsolute(imgSize, imgSize);
            // 禁止缩放图片，如果需要根据位置改变大小，请注释掉下面代码
            appearance.setImageScale(0);
            appearance.setSignatureGraphic(image);
            // 印章坐标计算，需要处理图片尺寸
            float llx = param.getRecturx() - imgSize / 2;
            float urx = param.getRecturx() + imgSize / 2;
            float lly = param.getRectury() - imgSize < 0 ? 0 : param.getRectury() - imgSize;
            float ury = param.getRectury() + imgSize / 2;
            // 签名的位置，是相对于pdf页面的位置坐标，原点为pdf页面左下角，四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
            appearance.setVisibleSignature(new Rectangle(llx, lly, urx, ury), param.getSealPage(), signatureFieldName);
            // 签名等级，默认为0，禁止修改可以调整这里
            appearance.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);
            // 章的显示方式
            appearance.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE));
            appearance.setSignDate(calendar);
            // 摘要算法
            ExternalDigest digest = new BouncyCastleDigest();
            // 签名算法
            ExternalSignature signature = new PrivateKeySignature(
                    param.getPk(), param.getDigestAlgorithm(),
                    null);
            // 数字签名格式，CMS（wps需要使用此种方式才会显示正常）、CADE
            MakeSignature.signDetached(appearance, digest, signature,
                    param.getChain(), null, null, null, 0,
                    MakeSignature.CryptoStandard.CMS);
            // 输出文件
            String fileUrl = getNewFileAbsolutePath();
            outputStream = new FileOutputStream(new File(fileUrl));
            outputStream.write(result.toByteArray());
            outputStream.flush();
            return fileUrl;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("盖章失败，请稍后再试！");
        } finally {
            IOUtils.closeQuietly(outputStream);
            IOUtils.closeQuietly(result);
            IOUtils.closeQuietly(sealImageIn);
            if (stamper != null) {
                try {
                    stamper.close();
                } catch (Exception e) { /* 忽略关闭异常 */}
            }
            if (reader != null) {
                reader.close();
            }
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 手写签名
     *
     * @param inputStream 签名的源pdf文件
     * @param base64Image 手写签名图片base64
     * @param keywords    关键字
     * @return 返回签字的pdf文件路径
     */
    public static String writeSign(InputStream inputStream, String base64Image, String keywords, SignParam param) throws Exception {
        FileOutputStream out = null;
        try {
            // 解析pdf文件
            byte[] fileBytes = IOUtils.toByteArray(inputStream);
            Map<Integer, List<KeyWordBean>> map = PdfKeywordUtil.getPDFText(new ByteArrayInputStream(fileBytes));
            // 获取关键字坐标
            int pageTotal = PdfKeywordUtil.getPdfPageTotal(new ByteArrayInputStream(fileBytes));
            KeyWordBean bean = PdfKeywordUtil.getKeyWord(keywords, pageTotal, map);
            if (null == bean) {
                log.error("手写签名错误，未查询到关键字。。。");
                throw new BaseException("未查询到关键字！");
            }
            // 签字
            byte[] bytes = sign(new ByteArrayInputStream(fileBytes), base64Image, bean.getX(), bean.getY(), bean.getPage(), param);
            String outFileUrl = EsignUtil.getAbsolutePath(EsignUtil.createPdfContractUrl());
            out = new FileOutputStream(new File(outFileUrl));
            out.write(bytes);
            out.flush();
            return outFileUrl;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("手写签名失败！");
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 骑缝章
     *
     * @param inputStream          源文件
     * @param sealImageInputStream 印章
     * @param height               印章高度，0.33f
     * @throws Exception
     */
    public static String seamSeal(InputStream inputStream, InputStream sealImageInputStream, float height) throws Exception {
        PDDocument document = null;
        try {
            String fileUrl = getNewFileAbsolutePath();
            byte[] pdfBytes = IOUtils.toByteArray(inputStream);
            document = Loader.loadPDF(pdfBytes);
            int pageCount = document.getNumberOfPages();
            // 加载原图并旋转为纵向
            BufferedImage fullImage = ImageIO.read(sealImageInputStream);
            int partWidth = fullImage.getWidth() / pageCount;
            List<BufferedImage> imageParts = new ArrayList<>();
            // 横向切割图片（假设原图是纵向排列的骑缝章）
            for (int i = 0; i < pageCount; i++) {
                int x = i * partWidth;
                imageParts.add(fullImage.getSubimage(x, 0, partWidth, fullImage.getHeight()));
            }
            // 固定绘制尺寸参数（固定印章部分宽度）
            final float TARGET_WIDTH = 15f;
            // 为每页添加对应的切割部分
            for (int i = 0; i < pageCount; i++) {
                PDPage page = document.getPage(i);
                PDRectangle mediaBox = page.getMediaBox();
                // 转换切割后的图片为PDF格式
                BufferedImage part = imageParts.get(i);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(part, "PNG", baos);
                PDImageXObject partImage = PDImageXObject.createFromByteArray(document, baos.toByteArray(), "seal_part");
                // 计算保持原始比例的绘制高度
                float scaleFactor = TARGET_WIDTH / part.getWidth();
                float targetHeight = part.getHeight() * scaleFactor;
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page,
                        PDPageContentStream.AppendMode.APPEND, true, true)) {
                    // 位置计算
                    float pageWidth = mediaBox.getWidth();
                    float pageHeight = mediaBox.getHeight();
                    // 右侧边缘位置（留10单位边距）
                    float x = pageWidth - partImage.getWidth() - 10;
                    // 底部1/3位置（总高度的2/3处）
                    float y = pageHeight * height - partImage.getHeight() / 2;
                    contentStream.drawImage(partImage, x, y, TARGET_WIDTH, targetHeight);
                }
            }
            document.save(fileUrl);
            return fileUrl;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("盖章失败！");
        } finally {
            if (document != null) {
                document.close();
            }
            IOUtils.closeQuietly(sealImageInputStream);
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 设置打开密码
     *
     * @param inputStream 源文件
     * @param ownerPwd    拥有者密码
     * @param userPwd     用户密码
     * @return
     * @throws Exception
     */
    public static String encrypt(InputStream inputStream, String ownerPwd, String userPwd) throws Exception {
        PDDocument document = null;
        try {
            String fileUrl = getNewFileAbsolutePath();
            byte[] pdfBytes = IOUtils.toByteArray(inputStream);
            document = Loader.loadPDF(pdfBytes);
            AccessPermission accessPermission = new AccessPermission();
            accessPermission.setCanModify(false); // 禁止修改
            StandardProtectionPolicy policy = new StandardProtectionPolicy(ownerPwd, userPwd, accessPermission);
            policy.setEncryptionKeyLength(128);
            document.protect(policy);
            document.save(fileUrl);
            return fileUrl;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("盖章失败！");
        } finally {
            if (document != null) {
                document.close();
            }
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 创建新文件绝对路径
     *
     * @return
     */
    private static String getNewFileAbsolutePath() {
        String fileUrl = EsignUtil.getPdfContractPath() + IdUtils.fastSimpleUUID() + SealConstants.PDF_SUFFIX;
        return EsignUtil.getAbsolutePath(fileUrl);
    }

    /**
     * 签名
     *
     * @return
     */
    private static byte[] sign(InputStream inputStream, String base64Image, float x, float y, int page, SignParam param) {
        PdfReader reader = null;
        ByteArrayOutputStream outputStream = null;
        PdfStamper stamper = null;
        try {
            reader = new PdfReader(inputStream);
            outputStream = new ByteArrayOutputStream();
            stamper = PdfStamper.createSignature(reader, outputStream, '\0', null, true);
            stamper.setFullCompression();
            PdfSignatureAppearance sap = stamper.getSignatureAppearance();
            sap.setReason(param.getReason());
            sap.setLocation(param.getLocation());
            byte[] sealImageByte = Base64Utils.base64ToBytes(base64Image);
            Image image = Image.getInstance(sealImageByte);
            sap.setImageScale(0);
            sap.setSignatureGraphic(image);
            sap.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);
            // 设置签名级别，默认为0，禁止修改可以调整这里
            sap.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);
            // 签名图片的具体位置，可以在此处调整
            sap.setVisibleSignature(new Rectangle(x - param.getRectllx(), y - param.getRectlly(), x + param.getRecturx(), y + param.getRectury()), page, IdUtils.fastSimpleUUID());
            stamper.getWriter().setCompressionLevel(5);
            ExternalDigest digest = new BouncyCastleDigest();
            ExternalSignature signature = new PrivateKeySignature(param.getPk(), DigestAlgorithms.SHA512, param.getProvider().getName());
            MakeSignature.signDetached(sap, digest, signature, param.getChain(), null, null, null, 0, MakeSignature.CryptoStandard.CMS);
            return outputStream.toByteArray();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("处理签名失败！");
        } finally {
            IOUtils.closeQuietly(outputStream);
            if (stamper != null) {
                try {
                    stamper.close();
                } catch (Exception e) { /* 忽略关闭异常 */}
            }
            reader.close();
            IOUtils.closeQuietly(inputStream);
        }
    }
}
