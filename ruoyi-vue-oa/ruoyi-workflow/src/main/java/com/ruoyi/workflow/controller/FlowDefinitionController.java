package com.ruoyi.workflow.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.flowable.domain.FlowableExpression;
import com.ruoyi.flowable.domain.qo.FlowSaveXmlQo;
import com.ruoyi.flowable.domain.qo.FlowUpdateRouteXmlQo;
import com.ruoyi.flowable.service.IFlowDefinitionService;
import com.ruoyi.flowable.service.IFlowableExpressionService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工作流程定义
 * </p>
 *
 * @author wocurr.com
 */
@Slf4j
@RestController
@RequestMapping("/flowable/definition")
public class FlowDefinitionController extends BaseController {

    @Autowired
    private IFlowDefinitionService flowDefinitionService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private IFlowableExpressionService flowableExpressionService;

    /**
     * 流程定义列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping(value = "/list")
    public TableDataInfo list(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(required = false) String name) {
        startPage();
        return getDataTable(flowDefinitionService.list(name, pageNum, pageSize));
    }

    /**
     * 导入流程文件
     * @param name
     * @param category
     * @param file
     * @return
     */
    @PostMapping("/import")
    public AjaxResult importFile(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String category,
                                 MultipartFile file) {
        flowDefinitionService.importFile(name, category, file);
        return AjaxResult.success("导入成功");
    }


    /**
     * 读取xml文件
     * @param deployId
     * @return
     */
    @GetMapping("/readXml/{deployId}")
    public AjaxResult readXml(@PathVariable(value = "deployId") String deployId) {
        String result = flowDefinitionService.readXml(deployId);
        return AjaxResult.success("", result);
    }

    /**
     * 读取图片文件
     * @param deployId
     * @param response
     */
    @GetMapping("/readImage/{deployId}")
    public void readImage(@PathVariable(value = "deployId") String deployId, HttpServletResponse response) {
        flowDefinitionService.readImage(deployId, response);
    }


    /**
     * 保存流程设计器内的xml文件
     * @param qo
     * @return
     */
    @Log(title = "流程定义", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult save(@RequestBody FlowSaveXmlQo qo) {
        flowDefinitionService.saveFile(qo.getName(), qo.getCategory(), qo.getXml());
        return AjaxResult.success("保存成功");
    }


    /**
     * 发起流程
     * @param procDefId
     * @param variables
     * @return
     */
    @Log(title = "发起流程", businessType = BusinessType.INSERT)
    @PostMapping("/start/{procDefId}")
    public AjaxResult start(@PathVariable(value = "procDefId") String procDefId, @RequestBody Map<String, Object> variables) {
        flowDefinitionService.startProcessInstanceById(procDefId, variables);
        return AjaxResult.success("流程启动成功");
    }

    /**
     * 激活或挂起流程定义
     * @param state
     * @param deployId
     * @return
     */
    @Log(title = "激活/挂起流程", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/updateState")
    public AjaxResult updateState(@RequestParam Integer state, @RequestParam String deployId) {
        flowDefinitionService.updateState(state, deployId);
        return AjaxResult.success();
    }

    /**
     * 删除流程
     *
     * @param procDefKey 流程定义key
     * @return
     */
    @PreAuthorize("@ss.hasPermi('flowable:definition:remove')")
    @Log(title = "删除流程", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/{procDefKey}")
    public AjaxResult delete(@PathVariable String procDefKey) {
        flowDefinitionService.delete(procDefKey);
        return AjaxResult.success();
    }

    /**
     * 指定流程办理人员列表
     * @param user
     * @return
     */
    @GetMapping("/userList")
    public AjaxResult userList(SysUser user) {
        List<SysUser> list = userService.selectUserList(user);
        return AjaxResult.success(list);
    }

    /**
     * 指定流程办理组列表
     * @param role
     * @return
     */
    @GetMapping("/roleList")
    public AjaxResult roleList(SysRole role) {
        List<SysRole> list = sysRoleService.selectRoleList(role);
        return AjaxResult.success(list);
    }

    /**
     * 指定流程达式列表
     * @param flowableExpression
     * @return
     */
    @GetMapping("/expList")
    public AjaxResult expList(FlowableExpression flowableExpression) {
        List<FlowableExpression> list = flowableExpressionService.listFlowableExpression(flowableExpression);
        return AjaxResult.success(list);
    }

    /**
     * 查询已发布的流程定义
     * @return
     */
    @GetMapping("/optionSelect")
    public AjaxResult optionSelect() {
        return AjaxResult.success(flowDefinitionService.getOptionSelect());
    }

    /**
     * 更新流程定义的在途流程
     *
     * @param qo
     * @return
     */
    @PreAuthorize("@ss.hasPermi('flowable:definition:updateRouteXml')")
    @PostMapping("/updateRouteXml")
    public AjaxResult updateRouteXml(@RequestBody FlowUpdateRouteXmlQo qo) {
        flowDefinitionService.migrateByProcessDefinitionKey(qo);
        return AjaxResult.success("更新成功");
    }
}
