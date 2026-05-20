package com.ruoyi.seal.utils.pdf;

import com.google.common.collect.Lists;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import com.ruoyi.seal.utils.pdf.param.KeyWordBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class LocalPdfKeywordUtil {
    private static com.itextpdf.awt.geom.Rectangle2D.Float boundingRectange = null;

    private static List<KeyWordBean> lists = null;
    private static Map<Integer, List<KeyWordBean>> map = new HashMap<Integer, List<KeyWordBean>>();

    /**
     * 获取文件总页数
     * @param fileUrl
     * @return
     */
    public static int getPdfPageTotal(String fileUrl) {
        int pageTotal = 0;
        PdfReader pdfReader = null;
        try {
            pdfReader = new PdfReader(fileUrl);
            pageTotal = pdfReader.getNumberOfPages();
        } catch (IOException e) {
            /* 忽略异常 */
        } finally {
            try {
                if (pdfReader != null) {
                    pdfReader.close();
                }
            } catch (Exception e) { /* 忽略关闭异常 */}
        }
        return pageTotal;
    }

    /**
     * 获取pdf内容
     * @param filePath
     * @return
     */
    public static Map<Integer, List<KeyWordBean>> getPDFText(String filePath) {
        PdfReader pdfReader = null;
        try {
            pdfReader = new PdfReader(filePath);
            int pageNum = pdfReader.getNumberOfPages();
            PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);
            for (int page = 1; page <= pageNum; page++) {
                lists = Lists.newArrayList();
                boundingRectange = new com.itextpdf.awt.geom.Rectangle2D.Float();
                int finalPage = page;
                pdfReaderContentParser.processContent(page, new RenderListener() {
                    @Override
                    public void renderText(TextRenderInfo textRenderInfo) {
                        String text = textRenderInfo.getText(); // 整页内容
                        boundingRectange = textRenderInfo.getBaseline().getBoundingRectange();
                        KeyWordBean bean = new KeyWordBean();
                        bean.setX(boundingRectange.x);
                        bean.setY(boundingRectange.y);
                        bean.setPage(finalPage);
                        bean.setText(text);
                        lists.add(bean);
                    }

                    @Override
                    public void renderImage(ImageRenderInfo arg0) {
                    }

                    @Override
                    public void endTextBlock() {
                    }

                    @Override
                    public void beginTextBlock() {
                    }
                });
                map.put(page, lists);
            }
        } catch (IOException e) {
            /* 忽略异常 */
        } finally {
            try {
                if (pdfReader != null) {
                    pdfReader.close();
                }
            } catch (Exception e) { /* 忽略关闭异常 */}
        }
        return map;
    }

    /**
     * 获取关键字
     * @param keyWord 关键字
     * @param page 页码
     * @param num 行
     * @param map
     * @return
     */
    public static KeyWordBean getKeyWord(String keyWord, int page, int num, Map<Integer, List<KeyWordBean>> map) {
        int keyMatch = 1;
        StringBuilder content = new StringBuilder();
        List<KeyWordBean> list = map.get(page);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            KeyWordBean bean = list.get(i);
            String text = bean.getText();
            if (i + 1 != list.size()) {
                KeyWordBean beanNext = list.get(i + 1);
                float x = beanNext.getX() - bean.getX();
                float y = beanNext.getY() - bean.getY();
                if (y == 0 && x <= 1) {
                    continue;
                }
            }
            if (StringUtils.contains(content.toString(), keyWord) || StringUtils.contains(text, keyWord)) {
                if (keyMatch == num) {
                    return bean;
                }
                keyMatch++;
            } else if ((StringUtils.isNotEmpty(text) && keyWord.startsWith(text)) || content.length() > 0) {
                content.append(text);
                if (content.length() >= keyWord.length()) {
                    if (StringUtils.contains(content.toString(), keyWord)) {
                        if (keyMatch == num) {
                            return bean;
                        }
                        keyMatch++;
                    }
                    content = new StringBuilder();
                }
            }
        }
        return null;
    }

    /**
     * 获取关键字
     * @param keyWord 关键字
     * @param pageTotal pdf总页数
     * @param map
     * @return
     */
    public static KeyWordBean getKeyWord(String keyWord, int pageTotal, Map<Integer, List<KeyWordBean>> map) {
        StringBuilder content = new StringBuilder();
        for (int page = 1; page <= pageTotal; page++) {
            List<KeyWordBean> list = map.get(page);
            Collections.sort(list);
            for (int row = 0; row < list.size(); row++) {
                KeyWordBean bean = list.get(row);
                String text = bean.getText();
                if (row + 1 != list.size()) {
                    KeyWordBean beanNext = list.get(row + 1);
                    float x = beanNext.getX() - bean.getX();
                    float y = beanNext.getY() - bean.getY();
                    if (y == 0 && x <= 1) {
                        continue;
                    }
                }
                if (StringUtils.contains(content.toString(), keyWord) || StringUtils.contains(text, keyWord)) {
                    return bean;
                }
                if ((StringUtils.isNotEmpty(text) && keyWord.startsWith(text)) || content.length() > 0) {
                    content.append(text);
                    if (content.length() >= keyWord.length()) {
                        if (StringUtils.contains(content.toString(), keyWord)) {
                            return bean;
                        }
                        content = new StringBuilder();
                    }
                }
            }
        }
        return null;
    }
}
