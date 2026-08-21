
import com.ruoyi.seal.utils.seal.SealUtil;
import com.ruoyi.seal.utils.seal.param.SealCircle;
import com.ruoyi.seal.utils.seal.param.SealConfiguration;
import com.ruoyi.seal.utils.seal.param.SealFont;

import java.awt.*;
import java.io.IOException;

/**
 * 印章测试类（根据企业名称做章大小动态调整，保证显示完全）
 * @Author wocurr.com
 */
public class SealTest {
    public static void main(String[] args) throws Exception {
        test();
        test2();
    }

    public static void test() throws Exception {
        /**
         * 印章配置文件
         */
        SealConfiguration configuration = new SealConfiguration();

        /**
         * 主文字
         */
        SealFont mainFont = new SealFont();
        mainFont.setIsBold(true);
        mainFont.setFontFamily("宋体");
        mainFont.setMarginSize(10);
        /**************************************************/
        mainFont.setFontText("广州眨哇科技的一级有限公司");
        mainFont.setFontSize(35);
        mainFont.setFontSpace(35.0);
        /**************************************************/
        //mainFont.setFontText("ZHITUWANG CO.LTDECIDDO SH  NANNINGSHI");
        //mainFont.setFontSize(20);
        //mainFont.setFontSpace(15.0);
        /**************************************************/
//        mainFont.setFontText("欢乐无敌制图网淘宝店专用章");
//        mainFont.setFontSize(25);
//        mainFont.setFontSpace(12.0);

        /**
         * 副文字
         */
        SealFont viceFont = new SealFont();
        viceFont.setIsBold(true);
        viceFont.setFontFamily("宋体");
        viceFont.setMarginSize(5);
        /**************************************************/
        viceFont.setFontText("91440101MA9UPCME4X");
        viceFont.setFontSize(13);
        viceFont.setFontSpace(10.0);
        /**************************************************/
//        viceFont.setFontText("正版认证");
//        viceFont.setFontSize(22);
//        viceFont.setFontSpace(12.0);

        /**
         * 中心文字
         */
        SealFont centerFont = new SealFont();
        centerFont.setIsBold(true);
        centerFont.setFontFamily("宋体");
        /**************************************************/
        centerFont.setFontText("★");
        centerFont.setFontSize(100);
        /**************************************************/
        //centerFont.setFontText("淘宝欢乐\n制图网淘宝\n专用章");
        //centerFont.setFontSize(20);
        /**************************************************/
        //centerFont.setFontText("123456789012345");
        //centerFont.setFontSize(20);
        /**************************************************/
//        centerFont.setFontText("发货专用");
//        centerFont.setFontSize(25);

        /**
         * 抬头文字
         */
        SealFont titleFont = new SealFont();
        titleFont.setIsBold(true);
        titleFont.setFontFamily("宋体");
        titleFont.setFontSize(22);
        /**************************************************/
        //titleFont.setFontText("发货专用");
        //titleFont.setMarginSize(68);
        //titleFont.setFontSpace(10.0);
        /**************************************************/
        titleFont.setFontText("正版认证");
        titleFont.setMarginSize(68);
        titleFont.setMarginSize(27);

        /**
         * 添加主文字
         */
        configuration.setMainFont(mainFont);
        /**
         * 添加副文字
         */
        configuration.setViceFont(viceFont);
        /**
         * 添加中心文字
         */
        configuration.setCenterFont(centerFont);
        /**
         * 添加抬头文字
         */
        //configuration.setTitleFont(titleFont);

        /**
         * 图片大小
         */
        configuration.setImageSize(300);
        /**
         * 背景颜色
         */
        configuration.setBackgroudColor(Color.RED);
        /**
         * 边线粗细、半径
         */
        configuration.setBorderCircle(new SealCircle(5, 140, 140));
//        configuration.setBorderCircle(new SealCircle(3, 140, 100));
        /**
         * 内边线粗细、半径
         */
//        configuration.setBorderInnerCircle(new SealCircle(1, 135, 135));
//        configuration.setBorderInnerCircle(new SealCircle(1, 135, 95));
        /**
         * 内环线粗细、半径
         */
        //configuration.setInnerCircle(new SealCircle(2, 105, 105));
//        configuration.setInnerCircle(new SealCircle(2, 85, 45));

        //1.生成公章
        try {
            SealUtil.buildAndStoreSeal(configuration, "E:\\rad-platform\\公章8.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //2.生成私章
//        SealFont font = new SealFont();
//        font.setFontSize(120).setIsBold(true).setFontText("诸葛孔明");
//        SealUtil.buildAndStorePersonSeal(300, 16, font, "印", "E:\\rad-platform\\私章1.png");
    }

    public static void test2() throws Exception {
        /**
         * 印章配置文件
         */
        SealConfiguration configuration = new SealConfiguration();

        /**
         * 主文字
         */
        SealFont mainFont = new SealFont();
        mainFont.setIsBold(true);
        mainFont.setFontFamily("宋体");
        mainFont.setMarginSize(10);
        /**************************************************/
        mainFont.setFontText("测试科技有限责任公司");
        mainFont.setFontSize(30);
        mainFont.setFontSpace(22.0);
        /**************************************************/
        //mainFont.setFontText("ZHITUWANG CO.LTDECIDDO SH  NANNINGSHI");
        //mainFont.setFontSize(20);
        //mainFont.setFontSpace(15.0);
        /**************************************************/
//        mainFont.setFontText("欢乐无敌制图网淘宝店专用章");
//        mainFont.setFontSize(25);
//        mainFont.setFontSpace(12.0);

        /**
         * 副文字
         */
        SealFont viceFont = new SealFont();
        viceFont.setIsBold(true);
        viceFont.setFontFamily("宋体");
        viceFont.setMarginSize(10);
        /**************************************************/
        viceFont.setFontText("123456789012678");
        viceFont.setFontSize(13);
        viceFont.setFontSpace(11.0);
        /**************************************************/
//        viceFont.setFontText("正版认证");
//        viceFont.setFontSize(22);
//        viceFont.setFontSpace(12.0);

        /**
         * 中心文字
         */
        SealFont centerFont = new SealFont();
        centerFont.setIsBold(true);
        centerFont.setFontFamily("宋体");
        /**************************************************/
        centerFont.setFontText("");
        centerFont.setFontSize(100);
        /**************************************************/
        //centerFont.setFontText("淘宝欢乐\n制图网淘宝\n专用章");
        //centerFont.setFontSize(20);
        /**************************************************/
        //centerFont.setFontText("123456789012345");
        //centerFont.setFontSize(20);
        /**************************************************/
//        centerFont.setFontText("发货专用");
//        centerFont.setFontSize(25);

        /**
         * 抬头文字
         */
        SealFont titleFont = new SealFont();
        titleFont.setIsBold(true);
        titleFont.setFontFamily("宋体");
        titleFont.setFontSize(22);
        /**************************************************/
        titleFont.setFontText("发票专用");
        titleFont.setMarginSize(58);
        titleFont.setFontSpace(10.0);
        /**************************************************/
//        titleFont.setFontText("正版认证");
//        titleFont.setMarginSize(68);
//        titleFont.setMarginSize(27);

        /**
         * 添加主文字
         */
        configuration.setMainFont(mainFont);
        /**
         * 添加副文字
         */
        configuration.setViceFont(viceFont);
        /**
         * 添加中心文字
         */
        configuration.setCenterFont(centerFont);
        /**
         * 添加抬头文字
         */
        configuration.setTitleFont(titleFont);

        /**
         * 图片大小
         */
        configuration.setImageSize(300);
        /**
         * 背景颜色
         */
        configuration.setBackgroudColor(Color.RED);
//        configuration.setBackgroudColor(Color.blue);
        /**
         * 边线粗细、半径
         */
//        configuration.setBorderCircle(new SealCircle(5, 140, 140));
        configuration.setBorderCircle(new SealCircle(5, 140, 100));
        /**
         * 内边线粗细、半径
         */
//        configuration.setBorderInnerCircle(new SealCircle(1, 135, 135));
//        configuration.setBorderInnerCircle(new SealCircle(1, 135, 95));
        /**
         * 内环线粗细、半径
         */
//        configuration.setInnerCircle(new SealCircle(2, 105, 105));
//        configuration.setInnerCircle(new SealCircle(2, 85, 45));


        //1.生成公章
        try {
            // 印章生成后，页面显示的大小：42*42 或40*40
            SealUtil.buildAndStoreSeal(configuration, "E:\\rad-platform\\公章9.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
