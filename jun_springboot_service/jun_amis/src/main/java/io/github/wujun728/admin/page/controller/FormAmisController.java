package io.github.wujun728.admin.page.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.PageData;
import io.github.wujun728.admin.common.PageParam;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.common.data.Obj;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.page.constants.PageKey;
import io.github.wujun728.admin.page.data.BaseButton;
import io.github.wujun728.admin.page.data.Form;
import io.github.wujun728.admin.page.data.FormField;
import io.github.wujun728.admin.page.data.Page;
import io.github.wujun728.admin.page.service.FormService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/form")
public class FormAmisController {
    @Resource
    private FormService formService;
    @Resource
    private JdbcService jdbcService;

    @RequestMapping("/query")
    public Result<PageData<Page>> query(@RequestBody PageParam pageParam){
        String sql = "select * from form where 1=1 ";
        List<Object> values = new ArrayList<>();
        if(StringUtils.isNotBlank(pageParam.getStr("code"))){
            sql += " and code like ? ";
            values.add("%"+pageParam.getStr("code")+"%");
        }
        if(StringUtils.isNotBlank(pageParam.getStr("name"))){
            sql += " and name like ? ";
            values.add("%"+pageParam.getStr("name")+"%");
        }
        return jdbcService.query(pageParam,Page.class,sql,values.toArray());
    }

    /*@RequestMapping("/get")
    public Result<Form> get(Long id){
        if(id == null){
            return Result.success(new Form());
        }
        return Result.success(formService.get(id));
    }*/
    @RequestMapping("/get")
    public Result<Form> getNew(String id){

        if(NumberUtil.isNumber(id)){
            if(id == null){
                return Result.success(new Form());
            }
            return Result.success(formService.get(Long.valueOf(id)));
        }else{
            if(id == null){
                return Result.success(new Form());
            }
            return Result.success(formService.load(id));
        }
    }
    @RequestMapping("/save")
    public Result<String> save(@RequestBody Form form){
        if(jdbcService.isRepeat("select id from form where code = '$code' and id <> $id ", BeanUtil.beanToMap(form))){
            return Result.error("表单编号重复");
        }
        formService.save(form);
        return Result.success();
    }

    @RequestMapping("/getJson")
    public Result getJson(Long id){
        Form form = null;
        if(id == null){
            form = new Form();
        }else{
            form = formService.get(id);
        }
        return Result.success(MapUtil.builder().put("json", JSONUtil.toJsonPrettyStr(form)).build());
    }
    @RequestMapping("/saveJson")
    public Result saveJson(@RequestBody Map<String,Object> map){
        String json = (String) map.get("json");
        Form form = JSONUtil.toBean(json, Form.class);
        Form oldForm = formService.get(form.getCode());
        if(oldForm != null){
            form.setId(oldForm.getId());
        }else{
            form.setId(null);
        }
        formService.save(form);
        return Result.success();
    }

    @RequestMapping("/copyForm")
    public Result<Form> copyForm(Long id){
        if(id == null){
            return Result.success();
        }
        Form copy = formService.get(id);
        copy.setId(null);
        copy.setCode(copy.getCode()+"_copy");
        return Result.success(copy);
    }


    @RequestMapping("/formFields")
    public Result formFields(@RequestBody Form form){
        formService.reload(form);
        return Result.success(form,"已刷新");
    }

    @RequestMapping("/saveFields")
    public Result saveFields(Long id,String fields){
        Form form = formService.get(id);
        form.setFormFields(JSONUtil.toList(fields,FormField.class));
        formService.save(form);
        return Result.success();
    }

    @RequestMapping(value="/js/{code}.js",produces = "text/javascript; charset=utf-8")
    public String js(@PathVariable("code") String code, @RequestParam Map<String,Object> data){
        Form form = formService.get(code);
        BaseButton button = new BaseButton();
        button.setLabel(form.getName());
        Map<String, Object> formJson = formService.getFormJson(form, button);
        Map<String,Object> formBody = (Map<String, Object>) formJson.get("body");
        List<Map<String, Obj>> actions = (List<Map<String, Obj>>) formJson.get("actions");
        formBody.put("name",form.getCode()+"Form");
        if(actions != null && !actions.isEmpty()){
            formBody.put("actions",actions);
        }
        if(formBody.containsKey("data")){
            Map<String,Object> oldData = (Map<String, Object>) formBody.get("data");
            oldData.putAll(data);
            formBody.put("data",oldData);
        }else{
            formBody.put("data",data);
        }
        return StrUtil.format("{}={}", PageKey.AMIS_JSON,JSONUtil.toJsonPrettyStr(formJson));
    }
}
