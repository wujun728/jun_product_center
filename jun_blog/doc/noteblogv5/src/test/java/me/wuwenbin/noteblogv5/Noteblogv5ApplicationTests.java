package me.wuwenbin.noteblogv5;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class Noteblogv5ApplicationTests {

    public static String replaceTagContent(String source, String element) {
//        List<String> result = new ArrayList<>();
        String reg = "<" + element + ">" + "(.+?)</" + element + ">";
        Matcher m = Pattern.compile(reg).matcher(source);
        return m.replaceAll("7777777");
//        while (m.find()) {
//            String r = m.group(1);
//            result.add(r);
//        }
//        return result;
    }

    /* *
     获取指定HTML标签的指定属性的值
     * @param source 要匹配的源文本
     * @param element 标签名称
     * @param attr 标签的属性名称
     * @return 属性值列表
     */
    public static List<String> getTagAttrValue(String source, String element, String attr) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?(\\s.*?)?>";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }

    /**
     * @param source  要匹配的源文本
     * @param element 标签名称
     * @return 内容集合
     */
    public static List<String> getTagContent(String source, String element) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element + ">" + "(.+?)</" + element + ">";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }


    public static String findTagContent(String source, String element, String id) throws IOException {
//        List<String> result = new ArrayList<>();
        String reg = "<" + element + ">" + "(.+?)</" + element + ">";
        Matcher m = Pattern.compile(reg).matcher(source);

        return "";
//        while (m.find()) {
//            String r = m.group(1);
//            result.add(r);
//        }
//        return result;
    }

    public static void main(String[] args) {
        String source = "<div>" +
                "<a title='中国体育报' href=''><hide4comment>打啊打<p class='sad'>啦啦啦" +
                "</p></hide4comment></a><a title='北京日报' href=''>bbb</a><hide4comment id='hc1'>啊啊啊啊阿<p class='sad'>wwww</p></hide4comment>" +
                "</div>";
        JXDocument doc = JXDocument.create(source);
        JXNode jxNode = doc.selNOne(StrUtil.format("//hide4comment[@id='{}']", "hc1"));
//        List<String> list = getTagContent(source, "hide4comment");
//        System.out.println(list);
//        System.out.println(replaceTagContent(source, "hide1comment"));
//        System.out.println(findTagContent(source, "hide4comment", "hc1"));
        System.out.println(jxNode.toString());
//        System.out.println(NbUtils.getTagContent(source, ArticleService.HIDE_COMMENT));
    }

    @Test
    public void contextLoads() {
//        ImgUtil.cut(
//                new File("E:\\upload\\noteblogv5\\img\\2019-09-11\\39fb0f85-2b57-41a3-9411-1b829b1c1a63.jpg"),
//                new File("E:\\upload\\noteblogv5\\img\\2019-09-11\\1.jpg"),
//                new Rectangle(0, 0, 200, 150)
//        );
        ImgUtil.scale(
                new File("E:\\upload\\noteblogv5\\img\\2019-09-11\\39fb0f85-2b57-41a3-9411-1b829b1c1a63.jpg"),
                new File("E:\\upload\\noteblogv5\\img\\2019-09-11\\39fb0f85-2b57-41a3-9411-1b829b1c1a63.jpg"),
                400, 300, Color.WHITE
        );
    }

    @Test
    public void mockData() {

    }
}
