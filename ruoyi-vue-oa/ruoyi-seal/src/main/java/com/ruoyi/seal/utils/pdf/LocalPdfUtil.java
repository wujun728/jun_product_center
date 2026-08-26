package com.ruoyi.seal.utils.pdf;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.security.*;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.uuid.IdUtils;
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
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.*;


/**
 * pdf盖章工具类（文件存储放在本地）<br>
 *
 * @author wocurr.com
 */
@Slf4j
public class LocalPdfUtil {

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
     * pdf表单域内容替换
     *
     * @param templateName 模板名称
     * @param acroFields   表单域字段
     * @return 返回生成的pdf文件地址（相对路径）
     */
    public static String createByTemplate(String templateName, CompanyPdfAcroFields acroFields) {
        // 生成的相对路径文件地址
        String fileUrl = EsignUtil.createPdfContractUrl();
        String absoluteFileUrl = EsignUtil.getAbsolutePath(fileUrl);
        String templatePath = EsignUtil.getPdfTemplatePath() + templateName;
        PdfStamper stamper = null;
        try {
            PdfReader reader = new PdfReader(templatePath);
            stamper = new PdfStamper(reader, new FileOutputStream(absoluteFileUrl));
            // 填充所有表单域
            Map<String, String> fieldValues = JSON.parseObject(JSON.toJSONString(acroFields),
                    new TypeReference<Map<String, String>>() {});
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
        }
    }

    /**
     * 盖章
     * @param sourceFile 需要盖章的源文件路径
     * @param param
     * @return
     */
    public static String stamp(String sourceFile, SignParam param, boolean isDelSourceFile) {
        boolean isSuccess = false;
        FileOutputStream outputStream = null;
        ByteArrayOutputStream result = null;
        PdfStamper stamper = null;
        try {
            String absoluteSourceFile = EsignUtil.getAbsolutePath(sourceFile);
            PdfReader reader = new PdfReader(absoluteSourceFile);
            // 盖章一般放在最后一页
            // int page = reader.getNumberOfPages();
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
            // 设置签名的位置、页码、签名域名称，多次追加签名的时候，签名预名称不能一样 图片大小受表单域大小影响（过小导致压缩）
            // 签名的位置，是相对于pdf页面的位置坐标，原点为pdf页面左下角，四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
            appearance.setVisibleSignature(new Rectangle(
                            param.getRectllx(),
                            param.getRectlly(),
                            param.getRecturx(),
                            param.getRectury()),
                    param.getSealPage(),
                    signatureFieldName
            );
            // 读取印章图片
            String absoluteImagePath = EsignUtil.getAbsolutePath("公章2.png");
            Image image = Image.getInstance(absoluteImagePath);
            appearance.setSignatureGraphic(image);
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
            String fileUrl = EsignUtil.getPdfContractPath() + IdUtils.fastSimpleUUID() + SealConstants.PDF_SUFFIX;
            String absoluteFileUrl = EsignUtil.getAbsolutePath(fileUrl);
            outputStream = new FileOutputStream(new File(absoluteFileUrl));
            outputStream.write(result.toByteArray());
            outputStream.flush();
            isSuccess = true;
            return fileUrl;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("盖章失败，请稍后再试！");
        } finally {
            IOUtils.closeQuietly(outputStream);
            IOUtils.closeQuietly(result);
            if (stamper != null) {
                try {
                    stamper.close();
                } catch (Exception e) { /* 忽略关闭异常 */}
            }
            // 删除源文件
            if (isSuccess && isDelSourceFile) {
                EsignUtil.deleteFile(sourceFile);
            }
        }
    }

    /**
     * 手写签名
     *
     * @param signImageUrl 手写签名图片路径
     * @param sourcePdfurl 签名的源pdf文件路径
     * @param keywords 关键字
     * @return 返回签字的pdf文件路径
     */
    public static String writeSign(String signImageUrl, String sourcePdfurl, String keyPassword, String keywords, boolean isDelSourceFile) throws Exception {
        boolean isSuccess = false;
        FileOutputStream out = null;
        try {
            // 解析pdf文件
            String absoluteFileUrl = EsignUtil.getAbsolutePath(sourcePdfurl);
            Map<Integer, List<KeyWordBean>> map = LocalPdfKeywordUtil.getPDFText(absoluteFileUrl);
            // 获取关键字坐标
            int pageTotal = LocalPdfKeywordUtil.getPdfPageTotal(absoluteFileUrl);
            KeyWordBean bean = PdfKeywordUtil.getKeyWord(keywords, pageTotal, map);
            if (null == bean) {
                log.error("手写签名错误，未查询到关键字。。。");
                throw new BaseException("未查询到关键字！");
            }
            // 签字
            String sealPath = EsignUtil.getAbsolutePath(signImageUrl);
            byte[] bytes = sign(keyPassword, EsignUtil.getCertPath(), absoluteFileUrl, sealPath, bean.getX(), bean.getY(), bean.getPage(), "个人签名", keywords);
            String outFileUrl = EsignUtil.createPdfContractUrl();
            String absoluteOutFileUrl = EsignUtil.getAbsolutePath(outFileUrl);
            out = new FileOutputStream(new File(absoluteOutFileUrl));
            out.write(bytes);
            out.flush();
            isSuccess = true;
            return outFileUrl;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("手写签名失败！");
        } finally {
            IOUtils.closeQuietly(out);
            // 删除源文件
            if (isSuccess && isDelSourceFile) {
                EsignUtil.deleteFile(sourcePdfurl);
            }
        }
    }

    /**
     * 骑缝章
     *
     * @param sourcePdfUrl 源文件路径
     * @param sealImagePath 印章路径
     * @throws Exception
     */
    public static String seamSeal(String sourcePdfUrl, String sealImagePath, boolean isDelSourceFile) throws Exception {
        PDDocument document = null;
        try {
            String outputPdfUrl = EsignUtil.getPdfContractPath() + IdUtils.fastSimpleUUID() + SealConstants.PDF_SUFFIX;
            String absoluteOutFileUrl = EsignUtil.getAbsolutePath(outputPdfUrl);
            String absoluteFileUrl = EsignUtil.getAbsolutePath(sourcePdfUrl);
            document = Loader.loadPDF(new File(absoluteFileUrl));
            int pageCount = document.getNumberOfPages();
            // 加载原图并旋转为纵向
            String absoluteSealImagePath = EsignUtil.getAbsolutePath(sealImagePath);
            BufferedImage fullImage = ImageIO.read(new File(absoluteSealImagePath));
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
                    float y = pageHeight * 0.33f - partImage.getHeight() / 2;
                    contentStream.drawImage(partImage, x, y, TARGET_WIDTH, targetHeight);
                }
            }
            document.save(absoluteOutFileUrl);
            return outputPdfUrl;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("盖章失败！");
        } finally {
            if (document != null) {
                document.close();
            }
            // 删除源文件
            if (isDelSourceFile) {
                EsignUtil.deleteFile(sourcePdfUrl);
            }
        }
    }

    /**
     * 设置打开密码
     * @param sourcePdfUrl 源文件路径
     * @param ownerpass 拥有者密码
     * @param userpass 用户密码
     * @param isDelSourceFile 是否删除源文件
     * @return
     * @throws Exception
     */
    public static String encrypt(String sourcePdfUrl, String ownerpass, String userpass, boolean isDelSourceFile) throws Exception {
        PDDocument document = null;
        try {
            String outputPdfUrl = EsignUtil.getPdfContractPath() + IdUtils.fastSimpleUUID() + SealConstants.PDF_SUFFIX;
            String absoluteOutFileUrl = EsignUtil.getAbsolutePath(outputPdfUrl);
            String absoluteFileUrl = EsignUtil.getAbsolutePath(sourcePdfUrl);
            document = Loader.loadPDF(new File(absoluteFileUrl));
            AccessPermission accessPermission = new AccessPermission();
            accessPermission.setCanModify(false); // 禁止修改
            StandardProtectionPolicy policy = new StandardProtectionPolicy(ownerpass, userpass, accessPermission);
            policy.setEncryptionKeyLength(128);
            document.protect(policy);
            document.save(absoluteOutFileUrl);
            return outputPdfUrl;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("盖章失败！");
        } finally {
            if (document != null) {
                document.close();
            }
            // 删除源文件
            if (isDelSourceFile) {
                EsignUtil.deleteFile(sourcePdfUrl);
            }
        }
    }

    /**
     * 签名
     * @return
     */
    private static byte[] sign(String password, String keyStorePath, String absoluteFileUrl, String signImage, float x, float y, int page, String reason, String location) {
        PdfReader reader = null;
        ByteArrayOutputStream outputStream = null;
        PdfStamper stamper = null;
        FileInputStream fos = null;
        try {
            BouncyCastleProvider provider = new BouncyCastleProvider();
            Security.addProvider(provider);
            KeyStore ks = KeyStore.getInstance("PKCS12", new BouncyCastleProvider());
            fos = new FileInputStream(keyStorePath);
            ks.load(fos, password.toCharArray());
            String alias = (String) ks.aliases().nextElement();
            PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password.toCharArray());
            Certificate[] chain = ks.getCertificateChain(alias);
            reader = new PdfReader(absoluteFileUrl);
            outputStream = new ByteArrayOutputStream();
            stamper = PdfStamper.createSignature(reader, outputStream, '\0', null, true);
            stamper.setFullCompression();
            PdfSignatureAppearance sap = stamper.getSignatureAppearance();
            sap.setReason(reason);
            sap.setLocation(location);
            Image image = Image.getInstance(signImage);
            sap.setImageScale(0);
            sap.setSignatureGraphic(image);
            sap.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);
            // 设置签名级别，默认为0，禁止修改可以调整这里
            sap.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);
            // 签名图片的具体位置，可以在此处调整
            sap.setVisibleSignature(new Rectangle(x + 10f, y - 30 , x + 240f, y + 40), page, IdUtils.fastSimpleUUID());
            stamper.getWriter().setCompressionLevel(5);
            ExternalDigest digest = new BouncyCastleDigest();
            ExternalSignature signature = new PrivateKeySignature(privateKey, DigestAlgorithms.SHA512, provider.getName());
            MakeSignature.signDetached(sap, digest, signature, chain, null, null, null, 0, MakeSignature.CryptoStandard.CMS);
            return outputStream.toByteArray();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("处理签名失败！");
        } finally {
            if (stamper != null) {
                try {
                    stamper.close();
                } catch (Exception e) { /* 忽略关闭异常 */}
            }
            IOUtils.closeQuietly(fos);
            IOUtils.closeQuietly(outputStream);
        }
    }
}
