package io.github.wujun728.admin.rbac.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.common.config.SessionContext;
import io.github.wujun728.admin.common.config.UserSession;
import io.github.wujun728.admin.common.constants.Constants;
import io.github.wujun728.admin.common.constants.ResultCode;
import io.github.wujun728.admin.common.constants.UserStatus;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.page.service.FormService;
import io.github.wujun728.admin.rbac.constants.UserType;
import io.github.wujun728.admin.rbac.data.Enterprise;
import io.github.wujun728.admin.rbac.data.EnterpriseUser;
import io.github.wujun728.admin.rbac.data.User;
import io.github.wujun728.admin.rbac.service.ConfigService;
import io.github.wujun728.admin.rbac.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Resource
    private JdbcService jdbcService;
    @Resource
    private ConfigService configService;

    @Resource
    private FormService formService;

    @Resource
    @Lazy
    private SessionContext sessionContext;

    @Resource
    private UserService userService;

//    @Resource
//    private DbCacheService dbCacheService;
//
//    @RequestMapping("/get/{code}")
//    public Result getByCode(@PathVariable String code){
//        return Result.success(dbCacheService.getData("user",code));
//    }

    @RequestMapping("/{formCode}/save")
    public Result save(@RequestBody User user, @PathVariable("formCode") String formCode){
        user = formService.getObj(user, formCode);
        Map<String,Object> params = new HashMap<>();
        params.put("id",user.getId());
        params.put("userCode",user.getUserCode());
        boolean repeat = jdbcService.isRepeat("select id from user where user_code = '$userCode' and id <> $id ", params);
        if(repeat){
            return Result.error("用户编号重复");
        }

        if(user.getId() == null){
            user.setCreateTime(new Date());
            user.setSalt(UUID.fastUUID().toString());

            String defaultPassword = configService.getValue("defaultPassword");

            String password = SecureUtil.md5(defaultPassword + user.getSalt());
            user.setPassword(password);
            //user.setPassword();
        }
        user.setUpdateTime(new Date());
        jdbcService.saveOrUpdate(user);
        return Result.success(user.getId());
    }
    @RequestMapping("/getUserSession")
    public Result getUserSession(HttpServletRequest request){
        UserSession session = sessionContext.getSession(request);
        User user = jdbcService.getById(User.class, session.getUserId());
        Enterprise enterprise = jdbcService.getById(Enterprise.class, session.getEnterpriseId());
        Map<String,Object> data = new HashMap<>();
        data.put("name", StrUtil.format("{}({})",user.getName(),enterprise.getName()));
        data.put("avatar",user.getAvatar());
        data.put("menus",session.getCurrentUserMenu());
        data.put("menusMobile",session.getCurrentUserMenuMobile());

        return Result.success(data);
    }
    @PostMapping("/postLogin")
    public Result postLogin(@RequestBody Map<String,Object> map, HttpServletRequest request, HttpServletResponse response){
        return this.login((String)map.get("username"),(String)map.get("password"),(String)map.get("captcha"),request,response);
    }
    @PostMapping("/login")
    @ResponseBody
    public Result login(String username,String password,String captcha, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String captchaCode = (String) session.getAttribute(Constants.CAPTCHA_CODE);
        Long captchaTimeout = (Long) session.getAttribute(Constants.CAPTCHA_TIMEOUT);
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return Result.error("用户名/密码不能为空");
        }
        if(StringUtils.isBlank(captcha)){
            return Result.error("验证码不能为空");
        }
        if(StringUtils.isBlank(captchaCode) || captchaTimeout == null || captchaTimeout < System.currentTimeMillis() || !captchaCode.equalsIgnoreCase(captcha)){
            return Result.error("验证码错误");
        }

        User user = jdbcService.findOne(User.class,new String[]{
                "userCode"
        },new Object[]{
                username
        });
        if(user == null){
            return Result.error("用户名/密码错误");
        }
        if(!user.getPassword().equals(SecureUtil.md5(password + user.getSalt()))){
            return Result.error("用户名/密码错误");
        }
        if(UserStatus.Quit.equals(user.getUserStatus())){
            return Result.error("用户已离职");
        }
        List<Enterprise> userEnterpriseList = userService.getUserEnterpriseList(user);
        //if(userEnterpriseList.size() == 1){
            UserSession userSession = sessionContext.newSession(request, user/*,userEnterpriseList.get(0).getId()*/);
            return Result.success(userSession);
        //}
        /*if(userEnterpriseList.isEmpty()){
            return Result.error("当前用户没有加入任何企业");
        }
        session.setAttribute(Constants.USER_CHOOSE_ENTERPRISE,user.getId());
        return Result.success("choose","选择企业");*/
    }

    @Deprecated
    @RequestMapping("/login/getUserChooseEnterprise")
    public Result getUserChooseEnterprise(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(Constants.USER_CHOOSE_ENTERPRISE);
        if(userId == null){
            return Result.error("重新登录");
        }
        User user = jdbcService.getById(User.class, userId);
        List<Enterprise> userEnterpriseList = userService.getUserEnterpriseList(user);
        return Result.success(userEnterpriseList);
    }

    @Deprecated
    @RequestMapping("/login/userChooseEnterprise")
    public Result userChooseEnterprise(HttpServletRequest request,Long enterpriseId){
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(Constants.USER_CHOOSE_ENTERPRISE);
        if(userId == null){
            return Result.error("重新登录");
        }
        if(enterpriseId == null){
            return Result.error("请选择企业");
        }
        User user = jdbcService.getById(User.class, userId);
        List<Enterprise> userEnterpriseList = userService.getUserEnterpriseList(user);
        for(Enterprise enterprise:userEnterpriseList){
            if(enterprise.getId().equals(enterpriseId)){
                sessionContext.newSession(request,user/*,enterpriseId*/);
                session.removeAttribute(Constants.USER_CHOOSE_ENTERPRISE);
                return Result.success();
            }
        }
        return Result.error("没有加入此企业");
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        sessionContext.deleteSession(request);
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/postLogout")
    public Result postLogout(HttpServletRequest request, HttpServletResponse response){
        sessionContext.deleteSession(request);
        return Result.success();
    }

    @PostMapping("/saveEnterpriseUser")
    public Result saveEnterpriseUser(@RequestBody JSONObject jsonObject){

        Long id = jsonObject.getLong("id");
        User user = JSONUtil.toBean(jsonObject,User.class);
        EnterpriseUser enterpriseUser = JSONUtil.toBean(jsonObject,EnterpriseUser.class);
        if(id == null){
            user.setCreateTime(new Date());
            user.setSalt(UUID.fastUUID().toString());
            user.setUserType(UserType.Common);

            String defaultPassword = configService.getValue("defaultPassword");

            String password = SecureUtil.md5(defaultPassword + user.getSalt());
            user.setPassword(password);

            enterpriseUser.setEnterpriseId(SessionContext.getSession().getEnterpriseId());
        }else{
            enterpriseUser = jdbcService.getById(EnterpriseUser.class,id);
            User dbUser = jdbcService.getById(User.class,enterpriseUser.getUserId());

            enterpriseUser.setDeptId(jsonObject.getLong("deptId"));

            dbUser.setUserCode(user.getUserCode());
            dbUser.setName(user.getName());
            dbUser.setMobile(user.getMobile());
            dbUser.setUserStatus(user.getUserStatus());
            dbUser.setOtherFiles(user.getOtherFiles());
            dbUser.setAvatar(user.getAvatar());

            user = dbUser;
        }
        User finalUser = user;
        finalUser.setUpdateTime(new Date());

        Map<String,Object> params = new HashMap<>();
        params.put("id",finalUser.getId());
        params.put("userCode",finalUser.getUserCode());
        boolean repeat = jdbcService.isRepeat("select id from user where user_code = '$userCode' and id <> $id ", params);
        if(repeat){
            return Result.error("用户编号重复");
        }

        EnterpriseUser finalEnterpriseUser = enterpriseUser;
        jdbcService.transactionOption(() -> {
            jdbcService.saveOrUpdate(finalUser);
            if(finalEnterpriseUser.getUserId() == null){
                finalEnterpriseUser.setUserId(finalUser.getId());
            }
            jdbcService.saveOrUpdate(finalEnterpriseUser);
        });
        return Result.success();
    }
    @PostMapping("/updatePwd")
    @ResponseBody
    public Result updatePwd(@RequestBody Map<String,String> params,HttpServletRequest request){
        String oldPwd = params.get("oldPwd");
        String newPwd = params.get("newPwd");
        String confirmPwd = params.get("confirmPwd");
        UserSession session = SessionContext.getSession();
        User user = jdbcService.getById(User.class, session.getUserId());
        if(StringUtils.isBlank(oldPwd) || StringUtils.isBlank(newPwd)|| StringUtils.isBlank(confirmPwd)){
            return Result.error("请填完表单后提交");
        }
        if(!newPwd.equals(confirmPwd)){
            return Result.error("新密码两次输入密码不一致");
        }
        if(!user.getPassword().equals(SecureUtil.md5(oldPwd + user.getSalt()))){
            return Result.error("原密码错误");
        }
        user.setPassword(SecureUtil.md5(newPwd + user.getSalt()));
        jdbcService.update(user);
        sessionContext.deleteSession(request);
        return new Result(ResultCode.NotLogin,"重新登录",null);
    }

    @RequestMapping("/resetPwd/{id}")
    @ResponseBody
    public Result resetPwd(@PathVariable Long id){
        User user = jdbcService.getById(User.class, id);

        String defaultPassword = configService.getValue("defaultPassword");

        String password = SecureUtil.md5(defaultPassword + user.getSalt());
        user.setPassword(password);
        jdbcService.update(user);
        return Result.success("重置后用户需要重新登录");
    }

    @RequestMapping("/auth.css")
    public String authCss(HttpServletRequest request){
        UserSession session = sessionContext.getSession(request);
        if(UserType.Admin.equals(session.getUserType())){
            return ".auth {display:inline-block !important};";
        }
        Set<String> buttonCodes = session.getButtonCodes();
        StringBuilder sb = new StringBuilder();
        for(String code:buttonCodes){
            sb.append(".auth-").append(code).append(" {display:inline-block !important;}\n");
        }
        return sb.toString();
    }
}
