package cc.mrbird.web.controller.system;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.domain.FebsConstant;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.utils.FileUtils;
import cc.mrbird.system.domain.MyUser;
import cc.mrbird.system.service.UserService;
import cc.mrbird.web.controller.base.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class UserController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String ON = "on";

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("user")
    @PreAuthorize("hasAuthority('user:list')")
    public String index(Model model) {
        return "system/user/user";
    }

    @RequestMapping("user/checkUserName")
    @ResponseBody
    public boolean checkUserName(String username, String oldusername) {
        if (StringUtils.isNotBlank(oldusername) && StringUtils.equalsIgnoreCase(username, oldusername)) {
            return true;
        }
        MyUser result = this.userService.findByName(username);
        return result == null;
    }

    @RequestMapping("user/getUser")
    @ResponseBody
    public ResponseBo getUser(Long userId) {
        try {
            MyUser user = this.userService.findById(userId);
            return ResponseBo.ok(user);
        } catch (Exception e) {
            log.error("获取用户失败", e);
            return ResponseBo.error("获取用户失败，请联系网站管理员！");
        }
    }

    @Log("获取用户信息")
    @RequestMapping("user/list")
    @PreAuthorize("hasAuthority('user:list')")
    @ResponseBody
    public Map<String, Object> userList(QueryRequest request, MyUser user) {
        return super.selectByPageNumSize(request, () -> this.userService.findUserWithDept(user));
    }

    @RequestMapping(FebsConstant.FEBS_REGIST_URL)
    @ResponseBody
    public ResponseBo regist(MyUser user) {
        try {
            MyUser result = this.userService.findByName(user.getUsername());
            if (result != null) {
                return ResponseBo.warn("该用户名已被使用！");
            }
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            this.userService.registUser(user);
            return ResponseBo.ok();
        } catch (Exception e) {
            log.error("注册失败", e);
            return ResponseBo.error("注册失败，请联系网站管理员！");
        }
    }

    @Log("更换主题")
    @RequestMapping("user/theme")
    @ResponseBody
    public ResponseBo updateTheme(MyUser user) {
        try {
            this.userService.updateTheme(user.getTheme(), user.getUsername());
            return ResponseBo.ok();
        } catch (Exception e) {
            log.error("修改主题失败", e);
            return ResponseBo.error();
        }
    }

    @Log("新增用户")
    @PreAuthorize("hasAuthority('user:add')")
    @RequestMapping("user/add")
    @ResponseBody
    public ResponseBo addUser(MyUser user, Long[] roles) {
        try {
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            if (ON.equalsIgnoreCase(user.getStatus()))
                user.setStatus(MyUser.STATUS_VALID);
            else
                user.setStatus(MyUser.STATUS_LOCK);
            this.userService.addUser(user, roles);
            return ResponseBo.ok("新增用户成功！");
        } catch (Exception e) {
            log.error("新增用户失败", e);
            return ResponseBo.error("新增用户失败，请联系网站管理员！");
        }
    }

    @Log("修改用户")
    @PreAuthorize("hasAuthority('user:update')")
    @RequestMapping("user/update")
    @ResponseBody
    public ResponseBo updateUser(MyUser user, Long[] rolesSelect) {
        try {
            if (ON.equalsIgnoreCase(user.getStatus()))
                user.setStatus(MyUser.STATUS_VALID);
            else
                user.setStatus(MyUser.STATUS_LOCK);
            this.userService.updateUser(user, rolesSelect);
            return ResponseBo.ok("修改用户成功！");
        } catch (Exception e) {
            log.error("修改用户失败", e);
            return ResponseBo.error("修改用户失败，请联系网站管理员！");
        }
    }

    @Log("删除用户")
    @PreAuthorize("hasAuthority('user:delete')")
    @RequestMapping("user/delete")
    @ResponseBody
    public ResponseBo deleteUsers(String ids) {
        try {
            this.userService.deleteUsers(ids);
            return ResponseBo.ok("删除用户成功！");
        } catch (Exception e) {
            log.error("删除用户失败", e);
            return ResponseBo.error("删除用户失败，请联系网站管理员！");
        }
    }

    @RequestMapping("user/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password) {
        MyUser currentUser = super.getCurrentUser();
        return this.passwordEncoder.matches(password, currentUser.getPassword());
    }

    @RequestMapping("user/updatePassword")
    @ResponseBody
    public ResponseBo updatePassword(String newPassword) {
        try {
            MyUser currentUser = super.getCurrentUser();
            this.userService.updatePassword(this.passwordEncoder.encode(newPassword), currentUser.getUsername());
            return ResponseBo.ok("更改密码成功，请重新登录！");
        } catch (Exception e) {
            log.error("修改密码失败", e);
            return ResponseBo.error("更改密码失败，请联系网站管理员！");
        }
    }

    @RequestMapping("user/profile")
    public String profileIndex(Model model) {
        MyUser user = super.getCurrentUser();
        user = this.userService.findUserProfile(user);
        String ssex = user.getSsex();
        if (MyUser.SEX_MALE.equals(ssex)) {
            user.setSsex("性别：男");
        } else if (MyUser.SEX_FEMALE.equals(ssex)) {
            user.setSsex("性别：女");
        } else {
            user.setSsex("性别：保密");
        }
        model.addAttribute("user", user);
        return "system/user/profile";
    }

    @RequestMapping("user/getUserProfile")
    @ResponseBody
    public ResponseBo getUserProfile(Long userId) {
        try {
            MyUser user = new MyUser();
            user.setUserId(userId);
            return ResponseBo.ok(this.userService.findUserProfile(user));
        } catch (Exception e) {
            log.error("获取用户信息失败", e);
            return ResponseBo.error("获取用户信息失败，请联系网站管理员！");
        }
    }

    @RequestMapping("user/updateUserProfile")
    @ResponseBody
    public ResponseBo updateUserProfile(MyUser user) {
        try {
            this.userService.updateUserProfile(user);
            return ResponseBo.ok("更新个人信息成功！");
        } catch (Exception e) {
            log.error("更新用户信息失败", e);
            return ResponseBo.error("更新用户信息失败，请联系网站管理员！");
        }
    }

    @RequestMapping("user/changeAvatar")
    @ResponseBody
    public ResponseBo changeAvatar(String imgName) {
        try {
            String[] img = imgName.split("/");
            String realImgName = img[img.length - 1];
            MyUser user = getCurrentUser();
            user.setAvatar(realImgName);
            this.userService.updateNotNull(user);
            return ResponseBo.ok("更新头像成功！");
        } catch (Exception e) {
            log.error("更换头像失败", e);
            return ResponseBo.error("更新头像失败，请联系网站管理员！");
        }
    }


    @RequestMapping("user/excel")
    @ResponseBody
    public ResponseBo userExcel(MyUser user) {
        try {
            List<MyUser> list = this.userService.findUserWithDept(user);
            return FileUtils.createExcelByPOIKit("用户表", list, MyUser.class);
        } catch (Exception e) {
            log.error("导出用户信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("user/csv")
    @ResponseBody
    public ResponseBo userCsv(MyUser user) {
        try {
            List<MyUser> list = this.userService.findUserWithDept(user);
            return FileUtils.createCsv("用户表", list, MyUser.class);
        } catch (Exception e) {
            log.error("导出用户信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }
}
