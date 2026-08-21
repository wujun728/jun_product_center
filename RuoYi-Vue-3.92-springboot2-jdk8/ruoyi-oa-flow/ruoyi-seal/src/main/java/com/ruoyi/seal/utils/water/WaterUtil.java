package com.ruoyi.seal.utils.water;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.seal.utils.EsignUtil;
import com.ruoyi.seal.constant.SealConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 水印工具类
 *
 * @Author wocurr.com
 */
@Slf4j
public class WaterUtil {

    /**
     * 字体属性
     */
    private static BaseFont cachedFont;
    // 参数配置
    private static float fontSize = 10;
    private static float fillOpacity = 0.4f; // 字体透明度
    private static float horizontalSpacing = 100; // 水印之间的水平间隔
    private static float verticalSpacing = 150; // 水印之间的垂直间隔
    private static float startX = 100; // 水印开始位置
    private static int rowsPerPage = 5; // 每页每行插入水印的个数
    private static int colPerPage = 5; // 每页每列插入水印的个数

    /**
     * 初始化字体
     */
    static {
        try {
            cachedFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (Exception e) {
            log.error("字体初始化失败", e);
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 添加水印
     *
     * @param inputStream   源文件
     * @param waterContent 水印内容
     * @return 返回文件绝对访问路径
     * @throws Exception
     */
    public static String addWaterMark(InputStream inputStream, String waterContent) throws Exception {
        PdfReader reader = null;
        PdfStamper stamper = null;
        try {
            String fileUrl = EsignUtil.getPdfContractPath() + IdUtils.fastSimpleUUID() + SealConstants.PDF_SUFFIX;
            String absoluteFileUrl = EsignUtil.getAbsolutePath(fileUrl);
            reader = new PdfReader(inputStream);
            stamper = new PdfStamper(reader, new FileOutputStream(absoluteFileUrl));
            PdfGState gs = new PdfGState();
            // 设置透明度
            gs.setFillOpacity(fillOpacity);
            int pageTotal = reader.getNumberOfPages();
            int total = pageTotal + 1;
            PdfContentByte content;
            // 从第二页开始
            for (int page = 2; page < total; page++) {
                Rectangle pageSize = reader.getPageSize(page);
                float pageHeight = pageSize.getHeight();
                float startY = pageHeight - 100;
                content = stamper.getOverContent(page);
                content.beginText();
                content.setGState(gs);
                // 水印颜色
                content.setColorFill(BaseColor.GRAY);
                // 水印字体样式和大小
                content.setFontAndSize(cachedFont, fontSize);
                // 插入水印
                for (int row = 0; row < rowsPerPage; row++) {
                    float y = startY - row * verticalSpacing;
                    // 每行生成横向重复水印
                    for (int col = 0; col < colPerPage; col++) {
                        float x = startX + col * horizontalSpacing;
                        content.showTextAligned(Element.ALIGN_CENTER, waterContent, x, y, 30);
                    }
                }
                content.endText();
            }
            stamper.close();
            reader.close();
            return absoluteFileUrl;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("水印添加异常！");
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
}
