package me.wuwenbin.noteblogv5.controllers.management;

import me.wuwenbin.noteblogv5.constant.NBV5;
import me.wuwenbin.noteblogv5.controllers.BaseController;
import me.wuwenbin.noteblogv5.service.UserService;
import me.wuwenbin.noteblogv5.service.LogService;
import me.wuwenbin.noteblogv5.service.CommentService;
import me.wuwenbin.noteblogv5.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wuwen
 */
@Controller
@RequestMapping("/management")
public class AdminDashboardController extends BaseController {

    private final CommentService commentService;
    private final MessageService messageService;
    private final LogService logService;
    private final UserService userService;

    public AdminDashboardController(CommentService commentService,
                                    MessageService messageService, LogService logService, UserService userService) {
        this.commentService = commentService;
        this.messageService = messageService;
        this.logService = logService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("nbv5version", NBV5.VERSION);
        model.addAttribute("latestComment", commentService.findLatestComment());
        model.addAttribute("latestMessage", messageService.findLatestMessage());
        model.addAttribute("ipSummary", logService.ipSummary());
        model.addAttribute("urlSummary", logService.urlSummary());
        model.addAttribute("browserSummary", logService.browserSummary());
        model.addAttribute("todayComment", commentService.findTodayComment());
        model.addAttribute("todayMessage", messageService.findTodayMessage());
        model.addAttribute("todayUser", userService.findTodayUser());
        model.addAttribute("todayVisit", logService.todayVisit());
        model.addAttribute("version",NBV5.VERSION);
        return "management/dashboard";
    }

}
