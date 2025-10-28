package com.ruoyi.flowable.controller.definition;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.CommonStatusEnum;
import com.ruoyi.common.mybatis.pojo.PageResult;
import com.ruoyi.flowable.convert.definition.BpmUserGroupConvert;
import com.ruoyi.flowable.domain.entity.definition.BpmUserGroupDO;
import com.ruoyi.flowable.domain.vo.group.BpmUserGroupCreateReqVO;
import com.ruoyi.flowable.domain.vo.group.BpmUserGroupPageReqVO;
import com.ruoyi.flowable.domain.vo.group.BpmUserGroupUpdateReqVO;
import com.ruoyi.flowable.service.definition.BpmUserGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


@Api(tags = "管理后台 - 用户组")
@RestController
@RequestMapping("/bpm/user-group")
@Validated
public class BpmUserGroupController {

    @Resource
    private BpmUserGroupService userGroupService;

    @PostMapping("/create")
    @ApiOperation("创建用户组")
    @PreAuthorize("@ss.hasPermi('bpm:user-group:create')")
    public AjaxResult createUserGroup(@Valid @RequestBody BpmUserGroupCreateReqVO createReqVO) {
        return AjaxResult.success(userGroupService.createUserGroup(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新用户组")
    @PreAuthorize("@ss.hasPermi('bpm:user-group:update')")
    public AjaxResult updateUserGroup(@Valid @RequestBody BpmUserGroupUpdateReqVO updateReqVO) {
        userGroupService.updateUserGroup(updateReqVO);
        return AjaxResult.success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除用户组")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('bpm:user-group:delete')")
    public AjaxResult deleteUserGroup(@RequestParam("id") Long id) {
        userGroupService.deleteUserGroup(id);
        return AjaxResult.success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得用户组")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('bpm:user-group:query')")
    public AjaxResult getUserGroup(@RequestParam("id") Long id) {
        BpmUserGroupDO userGroup = userGroupService.getUserGroup(id);
        return AjaxResult.success(BpmUserGroupConvert.INSTANCE.convert(userGroup));
    }

    @GetMapping("/page")
    @ApiOperation("获得用户组分页")
    @PreAuthorize("@ss.hasPermi('bpm:user-group:query')")
    public AjaxResult getUserGroupPage(@Valid BpmUserGroupPageReqVO pageVO) {
        PageResult<BpmUserGroupDO> pageResult = userGroupService.getUserGroupPage(pageVO);
        return AjaxResult.success(BpmUserGroupConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/list-all-simple")
    @ApiOperation(value = "获取用户组精简信息列表", notes = "只包含被开启的用户组，主要用于前端的下拉选项")
    public AjaxResult getSimpleUserGroups() {
        // 获用户门列表，只要开启状态的
        List<BpmUserGroupDO> list = userGroupService.getUserGroupListByStatus(CommonStatusEnum.ENABLE.getStatus());
        // 排序后，返回给前端
        return AjaxResult.success(BpmUserGroupConvert.INSTANCE.convertList2(list));
    }

}
