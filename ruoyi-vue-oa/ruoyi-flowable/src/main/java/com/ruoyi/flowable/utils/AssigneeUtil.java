package com.ruoyi.flowable.utils;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AssigneeUtil {

    /**
     * 填充任务办理人信息
     *
     * @param assignees          办理人列表
     * @param setAssignee        设置办理人ID的函数
     * @param setAssigneeName    设置办理人名称的函数
     * @param setAssigneeDeptName 设置办理人部门名称的函数
     */
    public static void fillAssigneeInfo(
            List<SysUser> assignees,
            Consumer<String> setAssignee,
            Consumer<String> setAssigneeName,
            Consumer<String> setAssigneeDeptName) {
        if (CollectionUtils.isEmpty(assignees)) {
            return;
        }
        setAssignee.accept(assignees.stream().map(SysUser::getUserId).map(String::valueOf).collect(Collectors.joining(Constants.COMMA)));
        setAssigneeName.accept(assignees.stream().map(SysUser::getNickName).collect(Collectors.joining(Constants.COMMA)));
        setAssigneeDeptName.accept(assignees.stream().map(sysUser -> Objects.nonNull(sysUser.getDept()) ? sysUser.getDept().getDeptName() : StringUtils.EMPTY).collect(Collectors.joining(Constants.COMMA)));
    }
}
