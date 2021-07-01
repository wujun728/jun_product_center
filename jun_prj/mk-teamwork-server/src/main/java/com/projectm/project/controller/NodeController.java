package com.projectm.project.controller;

import com.framework.common.AjaxResult;
import com.projectm.project.service.ProjectNodeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version V1.0
 * @program: teamwork
 * @package: com.projectm.project.controller
 * @description: 节点管理控制类
 * @author: lzd
 * @create: 2020-06-28 11:21
 **/
@RestController
@RequestMapping("project")
public class NodeController {

    @Autowired
    private ProjectNodeService projectNodeService;

    @PostMapping("/node/save")
    @ResponseBody
    public AjaxResult save(@RequestParam("list") String jsonList){
        return AjaxResult.success("保存成功", projectNodeService.saveNode(jsonList));
    }
}
