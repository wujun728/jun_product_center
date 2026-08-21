package com.ruoyi.workflow.el;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flowable.el.AbstractFlowableSingleExpression;
import com.ruoyi.system.service.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 角色表达式对象
 *
 * <p>
 *     角色表达式，校验用户角色是否符合条件要求
 *     eg:${roleSel.execute(ngr,"角色ID")}，
 *        ${roleSel.execute(ngr,"角色名称")}
 *        ${roleSel.execute(ngr,"测试")} // 模糊匹配角色名称
 *        ${!roleSel.execute(ngr,"角色名称")} // 取反
 *        ${roleSel.execute(ngr,"角色名称") || roleSel.execute(ngr,"测试")} // 多条件或
 *        ${roleSel.execute(ngr,"角色名称") && roleSel.execute(ngr,"测试")) // 多条件且
 *        其中ngr为选人组件返回的JSON对象
 *        JSON对象格式：{"createBy":"superAdmin","createTime":"2025-07-01 15:18:35","params":{"@type":"java.util.HashMap"},"userId":"60C905F10A2F465FA4DECD692DB12AED","deptId":"105","userName":"ceshiA","nickName":"测试人员-A","email":"ces@qq.com","sex":"0","avatar":"/profile/avatar/2025/07/01/89A195B0F85A483893ECCC2E9523F903.png","password":"$2a$10$CmA9rmv3tsb2qp1Apf7tPugYiuC0rKt41EZJBCiMWT99y8/6Op05C","status":"0","delFlag":"0","loginIp":"172.20.10.2","loginDate":"2025-08-26T14:04:29.000+08:00","dept":{"params":{"@type":"java.util.HashMap"},"deptId":"105","parentId":"101","ancestors":"0,100,101","deptName":"测试部门","orgType":"1","orderNum":3,"leader":"若依","status":"0","children":[]},"roles":[{"params":{"@type":"java.util.HashMap"},"roleId":"BE63F3F093C1411892284CDF9AD0BE07","roleName":"普通用户","roleKey":"common","roleSort":12,"dataScope":"1","menuCheckStrictly":false,"deptCheckStrictly":false,"status":"0","flag":false,"permissions":["worksetting:entrust:list","worksetting:secretary:add","workflow:draft:list","worksetting:entrust:query","system:user:list","worksetting:entrust:add","topic:info:list","workflow:recycle:list","worksetting:entrust:remove","worksetting:entrust:edit","information:information:query","workflow:done:list","worksetting:secretary:query","worksetting:secretary:remove","worksetting:secretary:edit","worksetting:secretary:list"],"admin":false}],"zhFullSpell":"CeShiRenYun-A","admin":false}
 * </p>
 * 
 * @author wocurr.com
 */
@Slf4j
@Component(value = "roleSel")
public class RoleFlowableSingleExpression extends AbstractFlowableSingleExpression {

    @Autowired
    private ISysRoleService roleService;

    private static final String USER_ID = "userId";
    private static final String ROLES = "roles";
    private static final String ROLE_ID = "roleId";
    private static final String ROLE_NAME = "roleName";

    @Override
    public Boolean executeJSONObject(JSONObject jsonObject, Object targetValue) {
        String targetValueStr = StringUtils.EMPTY;
        if (targetValue instanceof String) {
            targetValueStr = targetValue.toString();
        }
        if (StringUtils.isBlank(targetValueStr)) {
            log.error("RoleFlowableExpression is error, target value is null");
            throw new NullPointerException("RoleFlowableExpression is error, target value is null");
        }
        JSONArray roleJsonArray = jsonObject.getJSONArray(ROLES);
        if (roleJsonArray != null && !roleJsonArray.isEmpty()) {
            return handleRoles(roleJsonArray, targetValueStr);
        }
        // 处理选人组件
        if (!jsonObject.containsKey(USER_ID)) {
            return false;
        }
        String userId = jsonObject.getString(USER_ID);
        List<SysRole> userRoles = roleService.selectRolesByUserIds(Collections.singletonList(userId));
        for (SysRole role : userRoles) {
            if (targetValueStr.contains(Convert.toStr(role.getRoleId()))) {
                return true;
            } else if (StringUtils.isNotBlank(role.getRoleName()) && targetValueStr.contains(role.getRoleName())) {
                return true;
            } else if (StringUtils.isNotBlank(role.getRoleName()) && role.getRoleName().contains(targetValueStr)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected Boolean executeJSONArray(JSONArray jsonArray, Object targetValue) {
        String targetValueStr = StringUtils.EMPTY;
        if (targetValue instanceof String) {
            targetValueStr = targetValue.toString();
        }
        if (StringUtils.isBlank(targetValueStr)) {
            log.error("RoleFlowableExpression is error, target value is null");
            throw new NullPointerException("RoleFlowableExpression is error, target value is null");
        }
        List<String> userIds = new ArrayList<>();
        for (Object object : jsonArray) {
            if (object instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) object;
                JSONArray roleJsonArray = jsonObject.getJSONArray(ROLES);
                if (roleJsonArray != null && !roleJsonArray.isEmpty()) {
                    return handleRoles(roleJsonArray, targetValueStr);
                }
                if (!jsonObject.containsKey(USER_ID)) {
                    continue;
                }
                userIds.add(jsonObject.getString(USER_ID));
            }
        }
        if (userIds.isEmpty()) {
            return false;
        }
        List<SysRole> userRoles = roleService.selectRolesByUserIds(userIds);
        for (SysRole role : userRoles) {
            if (targetValueStr.contains(Convert.toStr(role.getRoleId()))) {
                return true;
            } else if (StringUtils.isNotBlank(role.getRoleName()) && targetValueStr.contains(role.getRoleName())) {
                return true;
            } else if (StringUtils.isNotBlank(role.getRoleName()) && role.getRoleName().contains(targetValueStr)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 处理角色
     *
     * @param jsonArray 角色数组
     * @param targetValue 目标值
     * @return
     */
    private Boolean handleRoles(JSONArray jsonArray, String targetValue) {
        for (Object object : jsonArray) {
            if (object instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) object;
                String roleId = jsonObject.getString(ROLE_ID);
                String roleName = jsonObject.getString(ROLE_NAME);
                if (targetValue.contains(roleId)) {
                    return true;
                } else if (StringUtils.isNotBlank(roleName) && targetValue.contains(roleName)) {
                    return true;
                } else if (StringUtils.isNotBlank(roleName) && roleName.contains(targetValue)) {
                    return true;
                }
            }
        }
        return false;
    }
}
