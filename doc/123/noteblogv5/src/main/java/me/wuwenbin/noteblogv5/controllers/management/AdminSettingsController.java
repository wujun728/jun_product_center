package me.wuwenbin.noteblogv5.controllers.management;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import me.wuwenbin.noteblogv5.constant.NBV5;
import me.wuwenbin.noteblogv5.controllers.BaseController;
import me.wuwenbin.noteblogv5.model.entity.Param;
import me.wuwenbin.noteblogv5.model.entity.User;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.service.ParamService;
import me.wuwenbin.noteblogv5.service.UserService;
import me.wuwenbin.noteblogv5.utils.CacheUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wuwen
 */
@Controller
@RequestMapping("/management/settings")
public class AdminSettingsController extends BaseController {

    private final HttpServletRequest request;
    private final ParamService paramService;
    private final UserService userService;

    public AdminSettingsController(HttpServletRequest request, ParamService paramService, UserService userService) {
        this.request = request;
        this.paramService = paramService;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profilePage(Model model) {
        model.addAttribute("alipay", paramService.findByName(NBV5.QRCODE_ALIPAY).getValue());
        model.addAttribute("wechat", paramService.findByName(NBV5.QRCODE_WECHAT).getValue());
        return "management/settings/profile";
    }


    @GetMapping("/website")
    public String websitePage(Model model) {
        List<Param> params = paramService.list(Wrappers.<Param>query().ge("`group`", 0));
        Map<String, Object> attributeMap = params.stream().collect(Collectors.toMap(Param::getName, p -> p.getValue() == null ? "" : p.getValue()));
        String rechargeUrl = attributeMap.getOrDefault("cash_recharge_url", "{\"name\":\"\",\"url\":\"\"}").toString();
        JSONArray jsonArray = JSONUtil.parseArray(rechargeUrl);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String name = object.getStr("name");
            String url = object.getStr("url");
            res.append(name.concat(",").concat(url));
            if (i < jsonArray.size() - 1) {
                res.append("\n");
            }
        }
        attributeMap.put("recharges", res.toString());
        model.addAllAttributes(attributeMap);
        return "management/settings/website";
    }


    @RequestMapping("/update")
    @ResponseBody
    public ResultBeanObj update(String name, String value) {
        if (StringUtils.isEmpty(name)) {
            return ResultBeanObj.error("参数名为空！");
        } else {
            return paramService.updateSettings(name, value);
        }
    }

    @RequestMapping("/updateBatch")
    @ResponseBody
    public ResultBeanObj updateBatch(String params) {
        JSONArray array = JSONUtil.parseArray(params);
        int cnt = 0;
        for (int i = 0; i < array.size(); i++) {
            Param p = array.get(i, Param.class);
            ResultBeanObj rb = paramService.updateSettings(p.getName(), p.getValue());
            if (rb.isSuccess()) {
                cnt++;
            }
        }
        return cnt == array.size() ? ResultBeanObj.ok("修改成功") : ResultBeanObj.error("修改不完全成功");
    }


    @RequestMapping("/pay/update")
    @ResponseBody
    public ResultBeanObj updateQrcode(String value, String name, String msg) {
        boolean res = paramService.update(Wrappers.<Param>update().set("value", value).eq("name", name));
        if (res) {
            CacheUtils.clearAllParamCache();
        }
        return handle(res, StrUtil.format("修改{}成功！", msg), StrUtil.format("修改{}失败！", msg));
    }


    @RequestMapping(value = "/profile/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultBeanObj updateProfile(String nickname, String email, String password1, String password2, String avatar) {
        User loginUser = getSessionUser(request);
        if (StrUtil.isNotEmpty(nickname)) {
            userService.update(
                    Wrappers.<User>update().set("nickname", nickname).eq("id", loginUser.getId()));
            paramService.update(NBV5.INFO_LABEL_NICKNAME, nickname);
        }
        if (!StringUtils.isEmpty(password1)) {
            if (password1.equals(password2)) {
                String dbPass = SecureUtil.md5(password1);
                userService.update(
                        Wrappers.<User>update().set("password", dbPass).eq("id", loginUser.getId())
                );
            } else {
                return ResultBeanObj.error("两次输入的密码不一致！");
            }
        }
        if (!StringUtils.isEmpty(avatar)) {
            boolean res = userService.update(
                    Wrappers.<User>update().set("avatar", avatar).eq("id", loginUser.getId()));
            if (res) {
                paramService.update(NBV5.INFO_LABEL_LOGO, avatar);
            }
        }
        if (!StringUtils.isEmpty(email)) {
            userService.update(
                    Wrappers.<User>update().set("email", email).eq("id", loginUser.getId())
            );
        }
        return ResultBeanObj.ok("重新登录生效！");
    }


    @RequestMapping("/mail/update")
    @ResponseBody
    public ResultBeanObj updateMailConfig(HttpServletRequest request) {
        return paramService.updateMailConfig(WebUtils.getParametersStartingWith(request, ""));
    }

    @GetMapping("/qrcode4Alipay")
    @ResponseBody
    public ResultBeanObj getQrcode4Alipay() {
        Param alipay = paramService.findByName("qrcode_alipay");
        return alipay != null && !alipay.getValue().contains("/static/") ? ResultBeanObj.ok("已上传", alipay) : ResultBeanObj.error("未上传");
    }

    @GetMapping("/qrcode4Wechat")
    @ResponseBody
    public ResultBeanObj qrcode4Wechat() {
        Param wechat = paramService.findByName("qrcode_wechat");
        return wechat != null && !wechat.getValue().contains("/static/") ? ResultBeanObj.ok("已上传", wechat) : ResultBeanObj.error("未上传");
    }

}
