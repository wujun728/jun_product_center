package me.wuwenbin.noteblogv5.controllers.frontend;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.wuwenbin.noteblogv5.constant.DictGroup;
import me.wuwenbin.noteblogv5.constant.NBV5;
import me.wuwenbin.noteblogv5.controllers.BaseController;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.model.bo.MessageBo;
import me.wuwenbin.noteblogv5.model.entity.Dict;
import me.wuwenbin.noteblogv5.model.entity.Message;
import me.wuwenbin.noteblogv5.model.entity.Param;
import me.wuwenbin.noteblogv5.service.DictService;
import me.wuwenbin.noteblogv5.service.MailService;
import me.wuwenbin.noteblogv5.service.MessageService;
import me.wuwenbin.noteblogv5.service.ParamService;
import me.wuwenbin.noteblogv5.utils.NbUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * created by Wuwenbin on 2018/2/8 at 18:54
 *
 * @author wuwenbin
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

    private final ParamService paramService;
    private final DictService dictService;
    private final MessageService messageService;
    private final MailService mailService;

    public MessageController(ParamService paramService,
                             DictService dictService,
                             MessageService messageService, MailService mailService) {
        this.paramService = paramService;
        this.dictService = dictService;
        this.messageService = messageService;
        this.mailService = mailService;
    }

    @GetMapping
    public String messagePage(Model model, Page<MessageBo> messagePage) {
        messagePage.setSize(20);
        OrderItem oi = OrderItem.desc("post");
        messagePage.addOrder(oi);
        model.addAttribute("linkList", dictService.findList(DictGroup.GROUP_LINK));
        model.addAttribute("messages", messageService.findMessagePage(messagePage, null, null, true));
        model.addAttribute("messageRankList", messageService.findMessageRankBoList());
        return "frontend/message";
    }

    @PostMapping("/lists")
    @ResponseBody
    public IPage<MessageBo> comments(Page<MessageBo> page) {
        page.setSize(20);
        OrderItem oi = OrderItem.desc("post");
        page.addOrder(oi);
        return messageService.findMessagePage(page, null, null, true);
    }

    @PostMapping("/token/sub")
    @ResponseBody
    public ResultBeanObj sub(@Valid Message message, BindingResult bindingResult, HttpServletRequest request) {
        Param messageOnoff = paramService.findByName("message_onoff");
        if ("1".equals(messageOnoff.getValue())) {
            if (!bindingResult.hasErrors()) {
                message.setIpAddr(NbUtils.getRemoteAddress(request));
                boolean develop = NbUtils.getBean(Environment.class).getProperty("app.develop", Boolean.class, true);
                if (!develop) {
                    message.setIpInfo(NbUtils.getIpInfo(message.getIpAddr()).getAddress());
                } else {
                    message.setIpInfo("本地/未知");
                }
                message.setUserAgent(request.getHeader("user-agent"));
                message.setComment(
                        HtmlUtil.removeHtmlTag(NbUtils.stripSqlXss(message.getComment()),
                                false, "style", "link", "meta", "script"));
                message.setPost(LocalDateTime.now());
                message.setClearComment(HtmlUtil.cleanHtmlTag(message.getComment()));
                List<Dict> keywords = dictService.findList(DictGroup.GROUP_KEYWORD);
                keywords.forEach(
                        kw -> message.setComment(message.getComment().replace(kw.getName(), StrUtil.repeat("*", kw.getName().length()))));
                if (messageService.save(message)) {
                    if ("1".equals(paramService.findByName(NBV5.MESSAGE_MAIL_NOTICE_ONOFF).getValue())) {
                        mailService.sendMessageMail(basePath(request), message.getComment());
                    }
                    return ResultBeanObj.ok("发表评论成功");
                } else {
                    return ResultBeanObj.error("发表评论失败");
                }
            } else {
                return ajaxJsr303(bindingResult.getFieldErrors());
            }
        } else {
            return ResultBeanObj.error("未开放评论！");
        }
    }
}
