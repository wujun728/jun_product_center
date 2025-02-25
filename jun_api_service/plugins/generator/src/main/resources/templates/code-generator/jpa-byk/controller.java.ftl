<#if isWithPackage?exists && isWithPackage==true>package ${packageName}.controller;</#if>
<#if isAutoImport?exists && isAutoImport==true>
import ${packageName}.vo.${classInfo.className}Vo;
import ${packageName}.dto.${classInfo.className}Dto;
import ${packageName}.entity.${classInfo.className}Entity;
import ${packageName}.service.${classInfo.className}Service;
//import com.bjc.lcp.common.cnt.enums.CntTableNameEnum;
//import com.bjc.lcp.common.cnt.service.CntService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
<#--import org.apache.shiro.authz.annotation.RequiresPermissions;-->
import org.springframework.web.servlet.ModelAndView;
import com.jun.plugin.common.Result;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
</#if>
/**
* @Version666
* @description ${classInfo.classComment}
* @author ${authorName}
* @date ${.now?string('yyyy-MM-dd')}
*/
@Api(tags = "${classInfo.classComment}-管理")
@Slf4j
@RestController
@RequestMapping("/${classInfo.className?uncap_first}")
public class ${classInfo.className}Controller {

    @Resource
    private ${classInfo.className}Service ${classInfo.className?uncap_first}Service;
    
    @ApiOperation(value = "${classInfo.classComment}-新增")
    @PostMapping("/add")
    //@RequiresPermissions("${classInfo.className?uncap_first}:add")
    public Result add(@Validated(${classInfo.className}Vo.Create.class) @RequestBody ${classInfo.className}Vo vo)  throws Exception {
    	${classInfo.className}Dto dto = new ${classInfo.className}Dto();
    	BeanUtils.copyProperties(vo, dto);
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
<#list classInfo.fieldList as fieldItem >
<#if fieldItem.nullable==true>
        if (ObjectUtils.isEmpty(dto.get${fieldItem.fieldName?cap_first}())) {
            return Result.fail("参数[${fieldItem.fieldName}]不能为空");
        }
</#if>
</#list>
</#if>
        ${classInfo.className}Entity entity = new ${classInfo.className}Entity();
        BeanUtils.copyProperties(dto, entity);
        List<${classInfo.className}Entity> list = ${classInfo.className?uncap_first}Service.list(entity);
        if (list.size() > 0) {
            return Result.fail("数据已存在");
        }
        return Result.success(${classInfo.className?uncap_first}Service.save(entity));
    }
    
    @ApiOperation(value = "${classInfo.classComment}-删除")
    @DeleteMapping("/remove")
    //@RequiresPermissions("${classInfo.className?uncap_first}:remove")
    public Result delete(@Validated(${classInfo.className}Vo.Delete.class) @RequestBody ${classInfo.className}Vo vo)  throws Exception  {
    	${classInfo.className}Dto dto = new ${classInfo.className}Dto();
    	BeanUtils.copyProperties(vo, dto);
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
<#list classInfo.fieldList as fieldItem >
<#if fieldItem.isPrimaryKey==true>
         if (ObjectUtils.isEmpty(dto.get${fieldItem.fieldName?cap_first}())) {
              return Result.fail("参数[${fieldItem.fieldName}]不能为空");
         }
</#if>
</#list>
</#if>
        return Result.success(${classInfo.className?uncap_first}Service.deleteById(dto.getId()));
    }

    @ApiOperation(value = "${classInfo.classComment}-删除")
    @DeleteMapping("/delete")
    public Result delete(@RequestBody @ApiParam(value = "id集合") List<String> ids) {
        ids.stream().forEach(item->{
            try {
                ${classInfo.className?uncap_first}Service.deleteById(Long.valueOf(item));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return Result.success();
    }


    @ApiOperation(value = "${classInfo.classComment}-更新")
    @PutMapping("/update")
    //@RequiresPermissions("${classInfo.className?uncap_first}:update")
    public Result update(@Validated(${classInfo.className}Vo.Update.class) @RequestBody ${classInfo.className}Vo vo) throws Exception  {
    	${classInfo.className}Dto dto = new ${classInfo.className}Dto();
    	BeanUtils.copyProperties(vo, dto);
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
<#list classInfo.fieldList as fieldItem >
<#if fieldItem.isPrimaryKey==true>
         if (ObjectUtils.isEmpty(dto.get${fieldItem.fieldName?cap_first}())) {
              return Result.fail("参数[${fieldItem.fieldName}]不能为空");
         }
</#if>
</#list>
</#if>
        ${classInfo.className}Entity entity = ${classInfo.className?uncap_first}Service.findById(vo.getId());
        if (entity == null) {
            return Result.fail("数据不存在");
            //entity = new AdminEntity();
        }
        BeanUtils.copyProperties(dto, entity);
        return Result.success(${classInfo.className?uncap_first}Service.update(entity));
    }
    


    @ApiOperation(value = "${classInfo.classComment}-查询单条")
    @RequestMapping(value = "/getOne",method = {RequestMethod.GET,RequestMethod.POST})
    //@RequiresPermissions("${classInfo.className?uncap_first}:getOne")
    public Result getOne(@RequestBody ${classInfo.className}Vo vo)  throws Exception  {
    	${classInfo.className}Dto dto = new ${classInfo.className}Dto();
    	BeanUtils.copyProperties(vo, dto);
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
<#list classInfo.fieldList as fieldItem >
<#if fieldItem.isPrimaryKey==true>
         if (ObjectUtils.isEmpty(dto.get${fieldItem.fieldName?cap_first}())) {
              return Result.fail("参数[${fieldItem.fieldName}]不能为空");
         }
</#if>
</#list>
</#if>
        ${classInfo.className}Entity entity = ${classInfo.className?uncap_first}Service.findById(dto.getId());
        return Result.success(entity);
    }
    

    @ApiOperation(value = "${classInfo.classComment}-查询列表分页数据")
    @RequestMapping(value = "/listByPage",method = {RequestMethod.GET,RequestMethod.POST})
    public Result listByPage(@RequestBody ${classInfo.className}Vo vo) throws Exception  {
        ${classInfo.className}Entity entity = new ${classInfo.className}Entity();
        BeanUtils.copyProperties(vo, entity);
        List<${classInfo.className}Entity> list = ${classInfo.className?uncap_first}Service.list(entity);
        return Result.success(list);
    }
    
    @ApiOperation(value = "${classInfo.classComment}-查询全部列表数据")
    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    //@RequiresPermissions("${classInfo.className?uncap_first}:list")
    public Result findListByPage(@RequestBody ${classInfo.className}Vo vo) throws Exception {
        ${classInfo.className}Entity entity = new ${classInfo.className}Entity();
        BeanUtils.copyProperties(vo, entity);
        List<${classInfo.className}Entity> list = ${classInfo.className?uncap_first}Service.list(entity);
        return Result.success(list);
    }


}

