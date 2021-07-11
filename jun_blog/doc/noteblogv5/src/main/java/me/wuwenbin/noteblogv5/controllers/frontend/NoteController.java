package me.wuwenbin.noteblogv5.controllers.frontend;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.wuwenbin.noteblogv5.controllers.BaseController;
import me.wuwenbin.noteblogv5.model.entity.Note;
import me.wuwenbin.noteblogv5.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wuwen
 */
@Controller
@RequestMapping("/note")
public class NoteController extends BaseController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public String notePage(Model model, Page<Note> page,
                           @RequestParam(required = false, defaultValue = "1") String p, String w,
                           @RequestParam(required = false, defaultValue = "post") String o,
                           @RequestParam(required = false, defaultValue = "desc") String s) {
        addPageOrder(page, "desc", "`top`");
        addPageOrder(page, s, o);
        page.setCurrent(!NumberUtil.isNumber(p) || NumberUtil.parseLong(p) <= 0 ? 1 : NumberUtil.parseLong(p));
        IPage<Note> notePage = noteService.page(page,
                Wrappers.<Note>query()
                        .like(StrUtil.isNotEmpty(w), "clear_content", w)
                        .or()
                        .like(StrUtil.isNotEmpty(w), "title", w));
        model.addAttribute("notePage", notePage);
        model.addAttribute("searchWords", w);
        return "frontend/note";
    }

}
