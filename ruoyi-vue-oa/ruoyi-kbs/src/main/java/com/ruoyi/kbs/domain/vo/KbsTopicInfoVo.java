package com.ruoyi.kbs.domain.vo;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.kbs.domain.KbsTopicAuthUser;
import com.ruoyi.kbs.domain.KbsTopicInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识库主题对象
 *
 * @author wocurr.com
 */
@Data
public class KbsTopicInfoVo extends KbsTopicInfo {

    /**
     * 主题类别名称
     */
    private String categoryName;

    /**
     * 是否可管理
     */
    private Boolean manageFlag;

    /**
     * 可见范围权限用户列表
     */
    private List<KbsTopicAuthUser> visualScopeAuthUsers;

    /**
     * 可见范围系统用户列表
     */
    private List<SysUserVo> visualScopeSysUsers = new ArrayList<>();
    /**
     * 操作类型权限用户列表
     */
    private List<KbsTopicAuthUser> operateTypeAuthUsers;

    /**
     * 操作类型系统用户列表
     */
    private List<SysUserVo> operateTypeSysUsers = new ArrayList<>();
}
