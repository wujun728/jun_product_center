package com.nbclass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nbclass.model.Role;
import com.nbclass.model.User;
import com.nbclass.service.RoleService;
import com.nbclass.service.UserService;
import com.nbclass.shiro.MyShiroRealm;
import com.nbclass.util.*;
import com.nbclass.vo.ChangePasswordVo;
import com.nbclass.vo.base.PageResultVo;
import com.nbclass.vo.base.ResponseVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
/**
 * @version V1.0
 * @date 2018年7月11日
 * @author superzheng
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MyShiroRealm myShiroRealm;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MyShiroRealm shiroRealm;

    /**用户列表数据*/
    @PostMapping("/list")
    @ResponseBody
    public PageResultVo loadUsers(User user, Integer limit, Integer offset){
        PageHelper.startPage(PageUtil.getPageNo(limit, offset),limit);
        List<User> userList = userService.selectUsers(user);
        PageInfo<User> pages = new PageInfo<>(userList);
        return ResultUtil.table(userList,pages.getTotal());
    }

    /**新增用户*/
    @PostMapping("/add")
    @ResponseBody
    public ResponseVo add(User userForm, String confirmPassword){
        String username = userForm.getUsername();
        User user = userService.selectByUsername(username);
        if (null != user) {
            return ResultUtil.error("用户名已存在");
        }
        String password = userForm.getPassword();
        //判断两次输入密码是否相等
        if (confirmPassword != null && password != null) {
            if (!confirmPassword.equals(password)) {
                return ResultUtil.error("两次密码不一致");
            }
        }
        userForm.setUserId(UUIDUtil.getUniqueIdByUUId());
        userForm.setStatus(CoreConst.STATUS_VALID);
        Date date = new Date();
        userForm.setCreateTime(date);
        userForm.setUpdateTime(date);
        userForm.setLastLoginTime(date);
        PasswordHelper.encryptPassword(userForm);
        int num = userService.register(userForm);
        if(num > 0){
            return ResultUtil.success("添加用户成功");
        }else {
            return ResultUtil.error("添加用户失败");
        }
    }

    /**编辑用户详情*/
    @GetMapping("/edit")
    public String userDetail(Model model, String userId){
        User user = userService.selectByUserId(userId);
        model.addAttribute("user", user);
        return "user/userDetail";
    }

    /**编辑用户*/
    @PostMapping("/edit")
    @ResponseBody
    public ResponseVo editUser(User userForm){
        int a = userService.updateByUserId(userForm);
        if (a > 0) {
            return ResultUtil.success("编辑用户成功！");
        } else {
            return ResultUtil.error("编辑用户失败");
        }
    }

    /**删除用户*/
    @GetMapping("/delete")
    @ResponseBody
    public ResponseVo deleteUser(String userId) {
        List<String> userIdsList = Arrays.asList(userId);
        int a = userService.updateStatusBatch(userIdsList,CoreConst.STATUS_INVALID);
        if (a > 0) {
            return ResultUtil.success("删除用户成功");
        } else {
            return ResultUtil.error("删除用户失败");
        }
    }

    /**批量删除用户*/
    @GetMapping("/batch/delete")
    @ResponseBody
    public ResponseVo batchDeleteUser(String userIdStr) {
        String[] userIds = userIdStr.split(",");
        List<String> userIdsList = Arrays.asList(userIds);
        int a = userService.updateStatusBatch(userIdsList,CoreConst.STATUS_INVALID);
        if (a > 0) {
            return ResultUtil.success("删除用户成功");
        } else {
            return ResultUtil.error("删除用户失败");
        }
    }

    /**分配角色列表查询*/
    @PostMapping("/assign/role/list")
    @ResponseBody
    public Map<String,Object> assignRoleList(String userId){
        List<Role> roleList = roleService.selectRoles(new Role());
        Set<String> hasRoles = roleService.findRoleByUserId(userId);
        Map<String, Object> jsonMap = new HashMap<>(2);
        jsonMap.put("rows", roleList);
        jsonMap.put("hasRoles",hasRoles);
        return jsonMap;
    }

    /**保存分配角色*/
    @PostMapping("/assign/role")
    @ResponseBody
    public ResponseVo assignRole(String userId, String roleIdStr){
        String[] roleIds = roleIdStr.split(",");
        List<String> roleIdsList = Arrays.asList(roleIds);
        ResponseVo responseVo = userService.addAssignRole(userId,roleIdsList);
        List<String> userIds = new ArrayList<>();
        userIds.add(userId);
        myShiroRealm.clearAuthorizationByUserId(userIds);
        return responseVo;
    }

    /*修改密码*/
    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo changePassword(ChangePasswordVo changePasswordVo) {
        if(!changePasswordVo.getNewPassword().equals(changePasswordVo.getConfirmNewPassword())){
            return ResultUtil.error("两次密码输入不一致");
        }
        User loginUser = userService.selectByUserId(((User) SecurityUtils.getSubject().getPrincipal()).getUserId());
        User newUser = CopyUtil.getCopy(loginUser,User.class);
        String sysOldPassword = loginUser.getPassword();
        newUser.setPassword(changePasswordVo.getOldPassword());
        String entryOldPassword = PasswordHelper.getPassword(newUser);
        if(sysOldPassword.equals(entryOldPassword)){
            newUser.setPassword(changePasswordVo.getNewPassword());
            PasswordHelper.encryptPassword(newUser);
            userService.updateUserByPrimaryKey(newUser);
            //*清除登录缓存*//
            List<String> userIds = new ArrayList<>();
            userIds.add(loginUser.getUserId());
            shiroRealm.removeCachedAuthenticationInfo(userIds);
            /*SecurityUtils.getSubject().logout();*/
        }else{
            return ResultUtil.error("您输入的旧密码有误");
        }
        return ResultUtil.success("修改密码成功");
    }

}
