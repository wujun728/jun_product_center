package io.github.wujun728.admin.util;

import com.lowagie.text.pdf.BaseFont;
import lombok.extern.slf4j.Slf4j;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.OutputStream;

@Slf4j
public class PdfUtil {
    public static void pdf(String url, OutputStream out){

        try{
            ITextRenderer renderer = new ITextRenderer();

//            WebClient browser = new WebClient();
//            browser.getOptions().setCssEnabled(false);
//            browser.getOptions().setJavaScriptEnabled(true);
//            browser.getOptions().setThrowExceptionOnScriptError(false);
//            browser.getOptions().setThrowExceptionOnFailingStatusCode(false);
//
//            HtmlPage htmlPage = browser.getPage(url);
//            browser.waitForBackgroundJavaScript(20000);
//
//            String html = htmlPage.asXml();
//            log.info("html:{}",html);
//            renderer.setDocumentFromString(html);
            renderer.getSharedContext().setReplacedElementFactory(new Base64ImgReplacedElementFactory());
            renderer.setDocument(url);
            renderer.getSharedContext().getTextRenderer().setSmoothingThreshold(1);
            // 解决中文不显示问题
            ITextFontResolver fontResolver = renderer.getFontResolver();
            fontResolver.addFont("pdf/fonts/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            fontResolver.addFont("pdf/fonts/STZHONGS.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            fontResolver.addFont("pdf/fonts/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            fontResolver.addFont("pdf/fonts/STHeiti.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            fontResolver.addFont("pdf/fonts/STPINGF.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            fontResolver.addFont("pdf/fonts/PINGFANG.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

            renderer.layout();
            renderer.createPDF(out);
        }catch (Exception e){
            e.printStackTrace();
            log.error("pdf生成失败",e);
        }
    }
}
