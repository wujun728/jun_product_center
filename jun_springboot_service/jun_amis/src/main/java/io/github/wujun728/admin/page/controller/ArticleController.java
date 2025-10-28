package io.github.wujun728.admin.page.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.util.PdfUtil;
import io.github.wujun728.admin.util.RequestUtil;
import io.github.wujun728.admin.util.ResourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Map;

@RestController
@RequestMapping("/admin/article")
@Slf4j
public class ArticleController {
    @Resource
    private JdbcService jdbcService;
    @RequestMapping("/pdfView/{id}")
    public String pdfView(@PathVariable Long id,HttpServletRequest request){
        Map<String, Object> article = jdbcService.getById("article", id);
        String common = ResourceUtil.getStr("pdf/css/common.css");
        return StrUtil.format("<html>" +
                "<head><style>{}</style></head>" +
                "<body>{}</body>" +
                "</html>",common,article.get("content").toString().replaceAll("\\.\\./","/"));
    }

    @RequestMapping("/pdf/{id}/{title}.pdf")
    public void pdf(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
        try {
            String contentRange = request.getHeader("Content-Range");
            log.info("range,{}",contentRange);
//            ServletOutputStream out = response.getOutputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfUtil.pdf(RequestUtil.getBasePath(request)+"/admin/article/pdfView/"+id,out);
            byte[] bytes = out.toByteArray();
            IoUtil.close(out);
            response.getOutputStream().write(bytes);
        } catch (ClientAbortException e) {
            //360极速浏览器下载的时候会发两次请求,第二次会失败
            log.info("用户取消,链接中断");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("下载失败",e);
        }
    }
}
