package ${package.Controller};

import com.lu.manage.core.tips.SuccessTip;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};

import com.lu.manage.core.common.exception.SysLogExcepetion;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * @Project ${cfg.project}
 * @Author: ${author}
 * @Date: ${cfg.createTime}
 * @Description: ${table.comment!} 控制器
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/${package.ModuleName}/${entity?uncap_first}")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    private static final String PREFIX = "/modular/${package.ModuleName}/${entity?uncap_first}";

    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};


/*******************************跳转页面相关*******************************/

    /**
    * 首页
    * @return
    */
    @GetMapping("")
    public String index(){
        return PREFIX + "/index.html";
    }

    /**
    * 新增
    * @return
    */
    @GetMapping("/toAdd")
    public String toAdd() {
        return PREFIX + "/add.html";
    }

    /**
    * 修改
    * @return
    */
    @GetMapping("/toUpdate")
    public String toUpdate(String id, Model model) throws SysLogExcepetion {
        ${entity} obj = ${table.serviceName?uncap_first}.getById(id);
        model.addAttribute("obj", obj);
        return PREFIX + "/update.html";
    }

/*******************************操作按钮相关*******************************/

    /**
     * 添加
     */
    @PostMapping("/add")
    @ResponseBody
    @RequiresPermissions(value = "/${package.ModuleName}/${entity?uncap_first}/add")
    public Object add(${entity} ${entity?uncap_first}) throws SysLogExcepetion {
        ${table.serviceName?uncap_first}.addObj(${entity?uncap_first});
        return new SuccessTip();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ResponseBody
    @RequiresPermissions(value = "/${package.ModuleName}/${entity?uncap_first}/update")
    public Object update(${entity} ${entity?uncap_first}) throws SysLogExcepetion {
        ${table.serviceName?uncap_first}.updateObj(${entity?uncap_first});
        return new SuccessTip();
    }

    /**
     * 删除&批量
     */
    @PostMapping("/delete")
    @ResponseBody
    @RequiresPermissions(value = "/${package.ModuleName}/${entity?uncap_first}/delete")
    public Object delete(String data) throws SysLogExcepetion {
        ${table.serviceName?uncap_first}.deleteObj(data);
        return new SuccessTip();
    }


/*******************************查询数据相关*******************************/

   /**
    * 分页查询
    */
    @GetMapping("/listPage")
    @ResponseBody
    public Object listPage(@RequestParam Map<String, Object> map) throws SysLogExcepetion {
        return ${table.serviceName?uncap_first}.listPage(map);
    }

}
</#if>
