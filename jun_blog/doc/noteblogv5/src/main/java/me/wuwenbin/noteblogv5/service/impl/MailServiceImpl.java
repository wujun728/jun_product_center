package me.wuwenbin.noteblogv5.service.impl;

import cn.hutool.cache.Cache;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import me.wuwenbin.noteblogv5.constant.NBV5;
import me.wuwenbin.noteblogv5.enums.RoleEnum;
import me.wuwenbin.noteblogv5.model.entity.Article;
import me.wuwenbin.noteblogv5.model.entity.User;
import me.wuwenbin.noteblogv5.service.UserService;
import me.wuwenbin.noteblogv5.service.MailService;
import me.wuwenbin.noteblogv5.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by Wuwenbin on 2019-01-08 at 22:27
 *
 * @author wuwenbin
 */
@Service
public class MailServiceImpl implements MailService {

    private final ParamService paramService;
    private final UserService userService;
    private final Cache<String, String> codeCache;

    @Autowired
    public MailServiceImpl(ParamService paramService, UserService userService, Cache<String, String> codeCache) {
        this.paramService = paramService;
        this.userService = userService;
        this.codeCache = codeCache;
    }

    @Override
    public void sendMail(String subject, String targetMail, String content, boolean isHtml) {
        String host = paramService.findByName(NBV5.MAIL_SMPT_SERVER_ADDR).getValue();
        String port = paramService.findByName(NBV5.MAIL_SMPT_SERVER_PORT).getValue();
        String from = paramService.findByName(NBV5.MAIL_SERVER_ACCOUNT).getValue();
        String user = paramService.findByName(NBV5.MAIL_SENDER_NAME).getValue();
        String pass = paramService.findByName(NBV5.MAIL_SERVER_PASSWORD).getValue();
        if (StrUtil.isNotEmpty(host)
                && StrUtil.isNotEmpty(port)
                && StrUtil.isNotEmpty(from)
                && StrUtil.isNotEmpty(pass)) {
            MailAccount account = new MailAccount();
            account.setHost(host);
            account.setPort(Integer.valueOf(port));
            account.setAuth(true);
            account.setSslEnable(true);
            account.setFrom(from);
            account.setUser(user);
            account.setPass(pass);
            MailUtil.send(
                    account,
                    CollUtil.newArrayList(targetMail),
                    subject,
                    content,
                    isHtml);
        }
    }

    @Override
    public void sendNoticeMail(String site, Article article, String comment) {
        User admin = userService.getOne(Wrappers.<User>query().eq("role", RoleEnum.ADMIN));
        String targetMail = admin.getEmail();
        String subject = "你的文章 - 【{}】 有人发表评论了";
        String content = "<p>" +
                "您发布的文章<span style='color:red;font-weight:bolder;'>「{}」</span>" +
                "有人发表了新评论：</p>" +
                "<p style='font-style:italic;'>{}</p>" +
                "<p>，请<a href='{}article/{}' target='_blank'>查看</a></p>";
        sendMail(StrUtil.format(subject, article.getTitle()), targetMail, StrUtil.format(content, article.getTitle(), comment, site, article.getId()), true);
    }


    @Override
    public void sendMessageMail(String site, String message) {
        User admin = userService.getOne(Wrappers.<User>query().eq("role", RoleEnum.ADMIN));
        String targetMail = admin.getEmail();
        String subject = "你的网站 - 【{}】 有人发表新留言了，快去查看吧！";
        String content = "<p>" +
                "你的博客网站<span style='color:red;font-weight:bolder;'>【{}】 </span>" +
                "有人发表了新留言：</p>" +
                "<p style='font-style:italic;'>{}</p>" +
                "<p>，请<a href='{}message' target='_blank'>查看</a></p>";

        String websiteTitle = paramService.findByName(NBV5.WEBSITE_TITLE).getValue();
        sendMail(StrUtil.format(subject, websiteTitle), targetMail, StrUtil.format(content, websiteTitle, message, site), true);

    }

    @Override
    public void sendMailCode(String email) {
        if (ReUtil.isMatch("^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$", email)) {
            String websiteTitle = paramService.findByName(NBV5.WEBSITE_TITLE).getValue();
            String subject = "【" + websiteTitle + "】注册验证码";
            String content = "您的验证码为<b>【{}】</b>，10分钟内有效。";
            String code = RandomUtil.randomString(6);
            content = StrUtil.format(content, code);
            sendMail(subject, email, content,
                    true);
            String key = email + "-" + NBV5.MAIL_CODE_KEY;
            codeCache.remove(key);
            codeCache.put(key, code);
        }
    }
}
