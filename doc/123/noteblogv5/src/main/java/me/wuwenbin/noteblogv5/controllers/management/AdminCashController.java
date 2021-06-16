package me.wuwenbin.noteblogv5.controllers.management;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.wuwenbin.noteblogv5.controllers.BaseController;
import me.wuwenbin.noteblogv5.model.json.LayuiTable;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.model.entity.Cash;
import me.wuwenbin.noteblogv5.service.CashService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author wuwen
 */
@Controller
@RequestMapping("/management/cash")
public class AdminCashController extends BaseController {

    private final CashService cashService;

    public AdminCashController(CashService cashService) {
        this.cashService = cashService;
    }

    @GetMapping
    public String cashPage() {
        return "management/users/cash_list";
    }

    @PostMapping("/list")
    @ResponseBody
    public LayuiTable<Cash> cashLayuiTable(Page<Cash> page, String sort, String order,
                                           String nickname, Long userId, String cashNo) {
        addPageOrder(page, order, sort);
        IPage<Cash> cashPage = cashService.page(page, Wrappers.<Cash>query()
                .like(StrUtil.isNotEmpty(nickname), "nickname", nickname)
                .eq(userId != null, "user_id", userId)
                .like(StrUtil.isNotEmpty(cashNo), "cash_no", cashNo)
        );
        return new LayuiTable<>(cashPage.getTotal(), cashPage.getRecords());
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultBeanObj update(@RequestParam("id") Long id, boolean enable) {
        if (!enable && cashService.getById(id) != null) {
            return ResultBeanObj.error("已经使用过的不允许更改！");
        } else {
            boolean res = cashService.update(Wrappers.<Cash>update().set("enable", enable).eq("id", id));
            return handle(res, "状态修改成功！", "状态修改失败！");
        }
    }

    @RequestMapping("/generate")
    @ResponseBody
    public ResultBeanObj generateNewCash(@RequestParam(defaultValue = "100\n1") String cashes) {
        String[] cashArray = cashes.split("\n");
        int cashValue = Integer.parseInt(cashArray[0]);
        int count = cashArray.length == 2 ? Integer.parseInt(cashArray[1]) : 1;
        int actualCnt = 0;
        for (int i = 0; i < count; i++) {
            String cashNo = RandomUtil.randomString(16).toUpperCase();
            String regex = "(.{4})";
            cashNo = cashNo.replaceAll(regex, "$1-");
            cashNo = cashNo.substring(0, cashNo.length() - 1);
            int cnt = cashService.count(Wrappers.<Cash>query().eq("cash_no", cashNo));
            for (; cnt > 0; ) {
                cashNo = RandomUtil.randomString(16).toUpperCase();
                cashNo = cashNo.replaceAll(regex, "$1-");
                cashNo = cashNo.substring(0, cashNo.length() - 1);
                cnt = cashService.count(Wrappers.<Cash>query().eq("cash_no", cashNo));
            }
            Cash cash = Cash.builder().cashNo(cashNo).createTime(new Date())
                    .cashValue(cashValue).enable(true).build();
            boolean res = cashService.save(cash);
            if (res) {
                actualCnt++;
            }
        }
        return ResultBeanObj.ok(StrUtil.format("目标生成卡{}张,实际生成卡{}张", count, actualCnt));
    }
}
