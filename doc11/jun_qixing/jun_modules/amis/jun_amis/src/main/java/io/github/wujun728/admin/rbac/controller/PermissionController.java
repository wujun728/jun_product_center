package io.github.wujun728.admin.rbac.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.common.config.SessionContext;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.page.data.InputField;
import io.github.wujun728.admin.page.service.InputFieldService;
import io.github.wujun728.admin.rbac.data.Permission;
import io.github.wujun728.admin.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/permission")
public class PermissionController {

    @Resource
    private JdbcService jdbcService;

    @Resource
    private InputFieldService inputFieldService;

    @RequestMapping("/form/{id}/{ref}/{refId}")
    public String form(Model model, @PathVariable("id") Long id,@PathVariable("ref") String ref,@PathVariable("refId") Long refId){
        model.addAttribute("js","/admin/permission/js/"+id+"/"+ref+"/"+refId+".js?_rt="+System.currentTimeMillis());
        return "page";
    }

    @RequestMapping("/save/{id}/{ref}/{refId}")
    @ResponseBody
    public Result<Object> save(@PathVariable("id") Long id, @PathVariable("ref") String ref, @PathVariable("refId") Long refId, @RequestBody Map<String,Object> params){
        List<Map<String,Object>> list = new ArrayList<>();
        Object configValue = params.get("configValue");
        List<String> configValues = new ArrayList<>();
        if(configValue != null){
            String[] arr = StringUtil.splitStr(configValue.toString(), ",");
            for(String s:arr){
                if(StringUtils.isNotBlank(s)){
                    configValues.add(s);
                }
            }
        }
        Long enterpriseId = SessionContext.getSession().getEnterpriseId();
        if(ref.equals("position")){
            for(String v:configValues){
                Map<String,Object> item = new HashMap<>();
                item.put("positionId",refId);
                item.put("permissionId",id);
                item.put("enterpriseId",enterpriseId);
                item.put("configValue",v);
                list.add(item);
            }
        }else if(ref.equals("dept")){
            for(String v:configValues){
                Map<String,Object> item = new HashMap<>();
                item.put("deptId",refId);
                item.put("permissionId",id);
                item.put("configValue",v);
                list.add(item);
            }
        }else if(ref.equals("user")){
            for(String v:configValues){
                Map<String,Object> item = new HashMap<>();
                item.put("enterpriseUserId",refId);
                item.put("permissionId",id);
                item.put("configValue",v);
                list.add(item);
            }
        }
        jdbcService.transactionOption(()->{
            if(ref.equals("position")){
                jdbcService.delete("delete from position_permission " +
                                "where position_id = ? and permission_id = ? and enterprise_id = ? "
                        ,
                        refId,
                        id,
                        enterpriseId);
                jdbcService.bathSaveOrUpdate(list,"position_permission");
            }else if(ref.equals("dept")){
                jdbcService.delete("delete from dept_permission " +
                                "where dept_id = ? and permission_id = ? "
                        ,
                        refId,
                        id);
                jdbcService.bathSaveOrUpdate(list,"dept_permission");
            }else if(ref.equals("user")){
                jdbcService.delete("delete from user_permission " +
                                "where enterprise_user_id = ? and permission_id = ? "
                        ,
                        refId,
                        id);
                jdbcService.bathSaveOrUpdate(list,"user_permission");
            }
        });
        return Result.success();
    }

    @RequestMapping(value="/js/{id}/{ref}/{refId}.js",produces = "text/javascript; charset=utf-8")
    @ResponseBody
    public String js(Model model, @PathVariable("id") Long id,@PathVariable("ref") String ref,@PathVariable("refId") Long refId, HttpServletResponse response){
        response.addHeader("Cache-Control","no-store");

        Permission permission = jdbcService.getById(Permission.class, id);
        String permissionType = permission.getPermissionType();


        Map<String,Object> form = new HashMap<>();
        form.put("type","form");
        form.put("mode","horizontal");
        form.put("api",StrUtil.format("post:/admin/permission/save/{}/{}/{}",id,ref,refId));

        List<Map<String,Object>> body = new ArrayList<>();
        form.put("body",body);

        boolean selector = false;
        InputField inputField = new InputField();
        inputField.setMulti(permission.getMuiti());
        inputField.setField("configValue");
        inputField.setComponentType(permission.getComponentType());
        inputField.setWidth(12);
        inputField.setLabel(permission.getName());

        if("position".equals(ref)){
            List<String> list = jdbcService.findForObject("select config_value from position_permission " +
                            "where position_id = ? and permission_id = ? and enterprise_id = ? "
                    , String.class,
                    refId,
                    id,
                    SessionContext.getSession().getEnterpriseId());
            if(!list.isEmpty()){
                inputField.setValue(StringUtil.concatStr(list,","));
            }
        }else if("dept".equals(ref)){
            List<String> list = jdbcService.findForObject("select config_value from dept_permission " +
                            "where dept_id = ? and permission_id = ? "
                    , String.class,
                    refId,
                    id);
            if(!list.isEmpty()){
                inputField.setValue(StringUtil.concatStr(list,","));
            }
        }else if("user".equals(ref)){
            List<String> list = jdbcService.findForObject("select config_value from user_permission " +
                            "where enterprise_user_id = ? and permission_id = ? "
                    , String.class,
                    refId,
                    id);
            if(!list.isEmpty()){
                inputField.setValue(StringUtil.concatStr(list,","));
            }
        }

        if("dic".equals(permissionType)){
            inputField.setType("dic");
            inputField.setFormat(permission.getPermissionValue());
        }else if("page".equals(permissionType)){
            inputField.setType("selector");
            inputField.setFormat(permission.getPermissionValue());
        }else if("sql".equals(permissionType)){
            inputField.setOptionSql(permission.getPermissionValue());
        }
        Map<String, Object> inputConfig = inputFieldService.buildInputField(inputField, selector);

        inputConfig.put("value",inputField.getValue());
        body.add(inputConfig);

        String h = ";" +
                "window.parent.postMessage{" +
                "type:'amis:resize'," +
                "data:{" +
                "height:400" +
                "}}" +
                "";
        return StrUtil.format("AMIS_JSON={}", JSONUtil.toJsonPrettyStr(form));
    }
}
