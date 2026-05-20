import com.itextpdf.text.pdf.security.DigestAlgorithms;
import com.ruoyi.seal.utils.pdf.LocalPdfUtil;
import com.ruoyi.seal.utils.pdf.param.CompanyPdfAcroFields;
import com.ruoyi.seal.utils.pdf.param.SignParam;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * pdf测试类
 * @Author wocurr.com
 */
public class PdfTest {
    public static void main(String[] args) {
        testPdf();
    }

    /**
     * pdf测试
     *  pdf生成的顺序应该是：
     *      内容替换 ->水印 -> 甲方：盖章+骑缝章 -> 乙方：盖章+骑缝章 或者 签名 -> 加密
     */
    public static void testPdf() {
        try {
            long t1 = System.currentTimeMillis();
            // 将证书文件放入指定路径，并读取keystore ，获得私钥和证书链
            String pkPath = "src/main/resources/cert/server.p12";
            KeyStore ks = KeyStore.getInstance("PKCS12");
//        ks.load(confg.getCertStream(), PdfUtil.PASSWORD);
            ks.load( PdfTest.class.getResourceAsStream("/cert/server.p12"), "111111".toCharArray());
            String alias = ks.aliases().nextElement();
            PrivateKey pk = (PrivateKey) ks.getKey(alias, "111111".toCharArray());
            // 得到证书链
            Certificate[] chain = ks.getCertificateChain(alias);

            // 封装签章信息
            SignParam signInfo = new SignParam();
            signInfo.setReason("理由");
            signInfo.setLocation("位置");
            signInfo.setPk(pk);
            signInfo.setChain(chain);
//        signInfo.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);
            signInfo.setDigestAlgorithm(DigestAlgorithms.SHA1);

            // 签章图片
            //// 值越大，代表向x轴坐标平移 缩小 （反之，值越小，印章会放大）
            signInfo.setRectllx(280);
            //// 值越大，代表向y轴坐标向上平移（大小不变）
            signInfo.setRectlly(120);
            // 值越大   代表向x轴坐标向右平移  （大小不变）
            signInfo.setRecturx(140);
            // 值越大，代表向y轴坐标向上平移（大小不变）
            signInfo.setRectury(220);

            // 内容替换
            String sourceFile = replaceAcroField();

            // 水印
//            String waterFile = LocalWaterUtil.addWaterMark(sourceFile, "测试科技", true);

            // 骑缝
//            String seamFile = LocalPdfUtil.seamSeal(waterFile, "公章2.png", true);

            //签章后的pdf路径
            //需要进行签章的pdf
            signInfo.setSealPage(6);
            // 盖章
//            String stampFile = LocalPdfUtil.stamp(seamFile,  signInfo, true);

            // 乙方盖章
            //// 值越大，代表向x轴坐标平移 缩小 （反之，值越小，印章会放大）
            signInfo.setRectllx(280);
            //// 值越大，代表向y轴坐标向上平移（大小不变）
            signInfo.setRectlly(120);
            // 值越大   代表向x轴坐标向右平移  （大小不变）
            signInfo.setRecturx(540);
            // 值越大，代表向y轴坐标向上平移（大小不变）
            signInfo.setRectury(220);
//        String stampFileB = LocalPdfUtil.stamp(stampFile,  signInfo);

            // 签名
            String signFile = LocalPdfUtil.writeSign("sign.png", sourceFile, "111111","乙方（章）", true);

//            PdfUtil.encrypt(signFile, "111111", "222222", true);
            long t2 = System.currentTimeMillis();
            System.out.println("总耗时：" + (t2-t1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String replaceAcroField() {
        try {
            String templateName = "template.pdf";
            CompanyPdfAcroFields acroFields = new CompanyPdfAcroFields();
            acroFields.setPartA("佛山测测试有限公司");
            acroFields.setPartAAddress("广东佛山禅城区工业大道芜湖路产业园B栋201室");
            acroFields.setPartAContacter("赵光缆");
            acroFields.setPartAContactPhone("13122323232");
            acroFields.setPartAContacter("刘禅与");
            acroFields.setYear("2025");
            acroFields.setMonth("02");
            acroFields.setDay("15");
            return LocalPdfUtil.createByTemplate(templateName, acroFields);
        } catch (Exception e) {}
        return null;
    }

}
