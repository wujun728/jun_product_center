package com.ruoyi.workflow.el;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flowable.el.AbstractFlowableSingleExpression;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.service.ISysPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 岗位表达式对象
 * <p>
 *     岗位表达式，校验用户岗位是否符合条件要求
 *     eg:${postSel.execute(ngr,"岗位ID")}，
 *        ${postSel.execute(ngr,"岗位名称")}
 *        ${postSel.execute(ngr,"测试")} // 模糊匹配岗位名称
 *        ${!postSel.execute(ngr,"岗位名称")} // 取反
 *        ${postSel.execute(ngr,"岗位名称") || postSel.execute(ngr,"测试")} // 多条件或
 *        ${postSel.execute(ngr,"岗位名称") && postSel.execute(ngr,"测试")) // 多条件且
 *        其中ngr为选人组件返回的JSON对象
 *        JSON对象格式：{"createBy":"superAdmin","createTime":"2025-07-01 15:18:35","params":{"@type":"java.util.HashMap"},"userId":"60C905F10A2F465FA4DECD692DB12AED","deptId":"105","userName":"ceshiA","nickName":"测试人员-A","email":"ces@qq.com","sex":"0","avatar":"/profile/avatar/2025/07/01/89A195B0F85A483893ECCC2E9523F903.png","password":"$2a$10$CmA9rmv3tsb2qp1Apf7tPugYiuC0rKt41EZJBCiMWT99y8/6Op05C","status":"0","delFlag":"0","loginIp":"172.20.10.2","loginDate":"2025-08-26T14:04:29.000+08:00","dept":{"params":{"@type":"java.util.HashMap"},"deptId":"105","parentId":"101","ancestors":"0,100,101","deptName":"测试部门","orgType":"1","orderNum":3,"leader":"若依","status":"0","children":[]},"roles":[{"params":{"@type":"java.util.HashMap"},"roleId":"BE63F3F093C1411892284CDF9AD0BE07","roleName":"普通用户","roleKey":"common","roleSort":12,"dataScope":"1","menuCheckStrictly":false,"deptCheckStrictly":false,"status":"0","flag":false,"permissions":["worksetting:entrust:list","worksetting:secretary:add","workflow:draft:list","worksetting:entrust:query","system:user:list","worksetting:entrust:add","topic:info:list","workflow:recycle:list","worksetting:entrust:remove","worksetting:entrust:edit","information:information:query","workflow:done:list","worksetting:secretary:query","worksetting:secretary:remove","worksetting:secretary:edit","worksetting:secretary:list"],"admin":false}],"zhFullSpell":"CeShiRenYun-A","admin":false}
 * </p>
 *
 * @author wocurr.com
 */
@Slf4j
@Component(value = "postSel")
public class PostFlowableSingleExpression extends AbstractFlowableSingleExpression {

    @Autowired
    private ISysPostService postService;

    private static final String USER_ID = "userId";

    /**
     * 执行JSON对象表达式
     *
     * @param jsonObject json对象，表单字段解析后得到的JSON对象
     * @param targetValue 目标值，支持精准匹配岗位ID（可包含多个，用逗号隔开）、精准匹配岗位名称和模糊匹配岗位名称
     * @return 表达式结果
     */
    @Override
    public Boolean executeJSONObject(JSONObject jsonObject, Object targetValue) {
        String targetValueStr = StringUtils.EMPTY;
        if (targetValue instanceof String) {
            targetValueStr = targetValue.toString();
        }
        if (StringUtils.isBlank(targetValueStr)) {
            log.error("PostFlowableExpression is error, target value is null");
            throw new NullPointerException("PostFlowableExpression is error, target value is null");
        }
        // 处理选人组件
        if (!jsonObject.containsKey(USER_ID)) {
            return false;
        }
        String userId = jsonObject.getString(USER_ID);
        List<SysPost> posts = postService.listUserPostByUserIds(Collections.singletonList(userId));
        for (SysPost post : posts) {
            if (targetValueStr.contains(Convert.toStr(post.getPostId()))) {
                return true;
            } else if (StringUtils.isNotBlank(post.getPostName()) && targetValueStr.contains(post.getPostName())) {
                return true;
            } else if (StringUtils.isNotBlank(post.getPostName()) && post.getPostName().contains(targetValueStr)) {
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
            log.error("PostFlowableExpression is error, target value is null");
            throw new NullPointerException("PostFlowableExpression is error, target value is null");
        }
        List<String> userIds = new ArrayList<>();
        for (Object object : jsonArray) {
            if (object instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) object;
                if (!jsonObject.containsKey(USER_ID)) {
                    continue;
                }
                userIds.add(jsonObject.getString(USER_ID));
            }
        }
        List<SysPost> posts = postService.listUserPostByUserIds(userIds);
        for (SysPost post : posts) {
            if (targetValueStr.contains(Convert.toStr(post.getPostId()))) {
                return true;
            } else if (StringUtils.isNotBlank(post.getPostName()) && targetValueStr.contains(post.getPostName())) {
                return true;
            } else if (StringUtils.isNotBlank(post.getPostName()) && post.getPostName().contains(targetValueStr)) {
                return true;
            }
        }
        return false;
    }
}
