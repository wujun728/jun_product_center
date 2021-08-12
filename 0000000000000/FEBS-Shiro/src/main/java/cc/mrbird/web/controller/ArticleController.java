package cc.mrbird.web.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.util.FebsConstant;
import cc.mrbird.common.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArticleController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Log("获取每日一文信息")
    @RequestMapping("article")
    @RequiresPermissions("article:list")
    public String index() {
        return "web/article/article";
    }

    @RequestMapping("article/query")
    @ResponseBody
    public ResponseBo queryArticle(String date) {
        String param;
        String data;
        try {
            if (StringUtils.isNotBlank(date)) {
                param = "dev=1&date=" + date;
                data = HttpUtils.sendSSLPost(FebsConstant.MRYW_DAY_URL, param);
            } else {
                param = "dev=1";
                data = HttpUtils.sendSSLPost(FebsConstant.MRYW_TODAY_URL, param);
            }
            return ResponseBo.ok(data);
        } catch (Exception e) {
            log.error("获取文章失败", e);
            return ResponseBo.error("获取文章失败，请联系网站管理员！");
        }
    }
}
