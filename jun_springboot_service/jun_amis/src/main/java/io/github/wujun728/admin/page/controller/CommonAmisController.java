package io.github.wujun728.admin.page.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.common.config.SessionContext;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.page.constants.CheckRepeatType;
import io.github.wujun728.admin.page.constants.ComponentType;
import io.github.wujun728.admin.page.constants.DataType;
import io.github.wujun728.admin.page.constants.Whether;
import io.github.wujun728.admin.page.data.Form;
import io.github.wujun728.admin.page.data.FormField;
import io.github.wujun728.admin.page.data.InputTableData;
import io.github.wujun728.admin.page.data.Page;
import io.github.wujun728.admin.page.service.FormEvent;
import io.github.wujun728.admin.page.service.FormService;
import io.github.wujun728.admin.page.service.PageService;
import io.github.wujun728.admin.rbac.service.ApiService;
import io.github.wujun728.admin.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonAmisController {

    @Resource
    private JdbcService jdbcService;

    @Resource
    private PageService pageService;

    @Resource
    private FormService formService;

    @Resource
    private ApiService apiService;
    @PostMapping("/{formCode}/saveJson")
    public Result saveJson(@RequestBody Map<String, Object> obj, @PathVariable("formCode") String formCode) {
        String json = (String) obj.get("json");
        Map<String,Object> map = JSONUtil.toBean(json, Map.class);
        return this.saveOrUpdate(map,formCode);
    }
    @PostMapping("/{formCode}/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Map<String, Object> obj, @PathVariable("formCode") String formCode) {
        Form form = formService.get(formCode);
        String tableName = form.getTableName();
        Object id = obj.get("id");
        Long enterpriseId = SessionContext.getSession().getEnterpriseId();

        if (id != null && StringUtils.isNotBlank(id.toString()) && !jdbcService.ownerEnterprise(tableName, ((Integer)id).longValue(), enterpriseId)) {
            return Result.error("没有数据权限.");
        }
        List<FormField> formFields = form.getFormFields();
        if(id != null && StringUtils.isNotBlank(id.toString())){
            Map<String, Object> dbObj = jdbcService.getById(form.getTableName(), Long.parseLong(id.toString()));
            if(dbObj != null){
                dbObj.putAll(obj);
                obj = dbObj;
            }
        }
        //过滤掉表单不存在的field.,二次保存空指针
//        List<String>        collect  = formFields.stream().map(InputField::getField).collect(Collectors.toList());
//        obj = obj.entrySet().stream().filter(dbObjA-> collect.contains(dbObjA.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        //inputTable关联数据
        List<InputTableData> inputTableDataList = new ArrayList<>();

        for(FormField formField:formFields){
            String type = formField.getType();
            Object value = obj.get(formField.getField());
            if(value == null){
                continue;
            }
            if(ComponentType.InputTable.equals(formField.getComponentType())){
                //InputTable
                String format = formField.getFormat();
                if(StringUtils.isBlank(format)){
                    continue;
                }
                String[] arr = format.split(",");
                if(arr.length != 2){
                    continue;
                }
                String refFormCode = arr[0];
                String refField = arr[1];
                List<Map<String,Object>> itemDatas = (List<Map<String, Object>>) value;
                InputTableData inputTableData = new InputTableData();
                Form refForm = formService.get(refFormCode);
                if(refForm == null || StringUtils.isBlank(refForm.getTableName())){
                    continue;
                }
                inputTableData.setTableName(refForm.getTableName());
                inputTableData.setRefField(refField);
                for(Map<String,Object> itemObj:itemDatas){
                    Object itemIdStr = itemObj.get("id");
                    if(itemIdStr!= null && StringUtils.isNotBlank(itemIdStr.toString())){
                        Long itemId = Long.parseLong(itemIdStr.toString());
                        Map<String, Object> itemDbObj = jdbcService.getById(refForm.getTableName(), itemId);
                        if(itemDbObj != null){
                            itemDbObj.putAll(itemObj);
                            itemObj = itemDbObj;
                        }
                    }
                    for(FormField itemFormField:refForm.getFormFields()){
                        Object itemFieldValue = itemObj.get(itemFormField.getField());
                        if(itemFieldValue == null){
                            continue;
                        }
                        if(!Whether.YES.equals(itemFormField.getMulti())){
                            Object realValue = DataType.getValue(itemFormField.getType(), itemFieldValue.toString(), itemFormField.getFormat());
                            itemObj.put(itemFormField.getField(),realValue);
                        }
                    }
                    inputTableData.getItems().add(itemObj);
                }
                inputTableDataList.add(inputTableData);

                continue;
            }

            if(!Whether.YES.equals(formField.getMulti())){
                if(value instanceof Map || value instanceof List){
                    value = JSONUtil.toJsonStr(value);
                }else{
                    value = value.toString();
                }
                Object realValue = DataType.getValue(type, value.toString(), formField.getFormat());
                obj.put(formField.getField(),realValue);
            }
        }

        //校验唯一性
        for(FormField formField:formFields){
            String checkSql = null;
            if(CheckRepeatType.Global.equals(formField.getCheckRepeatType())){
                checkSql = StrUtil.format("select id from {} where {} = '${}' and id <> $id",
                        form.getTableName(),
                        StringUtil.toSqlColumn(formField.getField()),
                        formField.getField());
            }else if(CheckRepeatType.Enterprise.equals(formField.getCheckRepeatType())){
                checkSql = StrUtil.format("select id from {} " +
                                "where {} = '${}' and id <> $id " +
                                "and enterprise_id = $enterpriseId ",
                        form.getTableName(),
                        StringUtil.toSqlColumn(formField.getField()),
                        formField.getField());
            }else if(CheckRepeatType.Fields.equals(formField.getCheckRepeatType())){
                checkSql = StrUtil.format("select id from {} " +
                                "where id <> $id " ,
                        form.getTableName());
                if(StringUtils.isBlank(formField.getCheckRepeatConfig())){
                    continue;
                }
                String[] fields = StringUtil.splitStr(formField.getCheckRepeatConfig(), ",");
                for(String field:fields){
                    checkSql += StrUtil.format(" and {}='${}' ",
                            StringUtil.toSqlColumn(field),
                            field
                            );
                }
            }else if(CheckRepeatType.Sql.equals(formField.getCheckRepeatType())){
                if(StringUtils.isBlank(formField.getCheckRepeatConfig())){
                    continue;
                }
                checkSql = formField.getCheckRepeatConfig();
            }else{
                continue;
            }

            Map<String,Object> checkParams = new HashMap<>();
            checkParams.putAll(obj);
            SessionContext.putUserSessionParams(checkParams);
            if(jdbcService.isRepeat(checkSql,checkParams)){
                String checkTip = formField.getLabel()+"不能重复";
                if(StringUtils.isNotBlank(formField.getCheckRepeatTip())){
                    checkTip = formField.getCheckRepeatTip();
                }
                return Result.error(checkTip);
            }
        }

        Map<String,Object> context = new HashMap<>();
        context.put("obj",obj);
        context.put("tableName",tableName);
        context.put("form",form);
        Result<String> call = apiService.call(form.getBeforeApi(), context);
        if(!call.isSuccess()){
            return call;
        }
        if(StringUtils.isNotBlank(form.getFormEvent())){
            FormEvent formEvent = SpringUtil.getBean(form.getFormEvent());
            Result result = formEvent.beforeSave(obj, tableName, form);
            if(result!= null && !result.isSuccess()){
                return result;
            }
        }

        final Map<String,Object> dbObj = obj;
        try{
            jdbcService.transactionOption(()->{
                jdbcService.saveOrUpdate(dbObj,tableName);
                for(InputTableData inputTableData:inputTableDataList){
                    List<Long> ids = new ArrayList<>();
                    ids.add(-1L);
                    int i = 0;
                    for(Map<String,Object> itemObj:inputTableData.getItems()){
                        itemObj.put("seq",++i);
                        itemObj.put(inputTableData.getRefField(),dbObj.get("id"));
                        jdbcService.saveOrUpdate(itemObj,inputTableData.getTableName());
                        ids.add((Long) itemObj.get("id"));
                    }
                    //删除其他数据
                    jdbcService.update(StrUtil.format("delete from {} where {} = ? and id not in({})",
                            inputTableData.getTableName(),
                            StringUtil.toSqlColumn(inputTableData.getRefField()),
                            StringUtil.concatStr(ids,",")
                            ),dbObj.get("id"));
                }
                Result<String> r = apiService.call(form.getAfterApi(), context);
                if(!r.isSuccess()){
                    throw new RuntimeException(r.getMsg());
                }
                if(StringUtils.isNotBlank(form.getFormEvent())){
                    FormEvent formEvent = SpringUtil.getBean(form.getFormEvent());
                    Result result = formEvent.afterSave(dbObj, tableName, form);
                    if(result!= null && !result.isSuccess()){
                        throw new RuntimeException(result.getMsg());
                    }
                }
            });
        }catch (Exception e){
            log.error("保存异常",e);
            return Result.error(e.getMessage());
        }
        return get((Long)obj.get("id"),formCode);
        //return Result.success(obj);
    }

    @RequestMapping("/{model}/delete/{id}")
    public Result delete(@PathVariable("id") Long id, @PathVariable("model") String model) {
        String tableName = StringUtil.toSqlColumn(model);
        Long enterpriseId = SessionContext.getSession().getEnterpriseId();

        if (id != null && StringUtils.isNotBlank(id.toString()) && !jdbcService.ownerEnterprise(tableName, id, enterpriseId)) {
            return Result.error("没有数据权限.");
        }

        if ("page".equals(model.toLowerCase())) {
            Page page = pageService.get(id);
            pageService.del(page);
        }else if("form".equals(model.toLowerCase())){
            Form form = formService.get(id);
            formService.del(form);
        }else{
            jdbcService.delete(id,tableName);
        }
        return Result.success();
    }
    @RequestMapping("/{model}/bathDelete/{ids}")
    public Result bathDelete(@PathVariable("ids") String ids, @PathVariable("model") String model) {
        String tableName = StringUtil.toSqlColumn(model);
        Long enterpriseId = SessionContext.getSession().getEnterpriseId();

//        if (id != null && StringUtils.isNotBlank(id.toString()) && !jdbcService.ownerEnterprise(tableName, id, enterpriseId)) {
//            return Result.error("没有数据权限.");
//        }
        String[] arr = StringUtil.splitStr(ids,",");
        for(String id:arr){
            jdbcService.delete(Long.parseLong(id),tableName);
        }
        return Result.success();
    }

    //查询管理表id
    @RequestMapping("/{model}/getRelationIds/{mainField}/{relationField}/{id}")
    public Result getRelationIds(@PathVariable("model") String model,
                              @PathVariable("mainField") String mainField,
                              @PathVariable("relationField") String relationField,
                              @PathVariable("id") Long id) {
        String tableName = StringUtil.toSqlColumn(model);
        String sql = StrUtil.format("select {} from {} where {}={} ",
                StringUtil.toSqlColumn(relationField),
                tableName,
                StringUtil.toSqlColumn(mainField),
                id
        );
        List<Map<String, Object>> maps = jdbcService.find(sql);
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < maps.size(); i++) {
            Object o = maps.get(i).get(relationField);
            Long _id = Long.parseLong(o.toString());
            ids.add(_id);
        }
        Map<String,Object> data = new HashMap<>();
        data.put(relationField,StringUtil.concatStr(ids,","));
        data.put("id",id);
        data.put(mainField,id);
        return Result.success(data);
    }

    //查询管理表id--增加企业id条件
    @RequestMapping("/{model}/getRelationIdsForEnterprise/{mainField}/{relationField}/{id}")
    public Result getRelationIdsForEnterprise(@PathVariable("model") String model,
                                 @PathVariable("mainField") String mainField,
                                 @PathVariable("relationField") String relationField,
                                 @PathVariable("id") Long id) {
        Long enterpriseId = SessionContext.getSession().getEnterpriseId();
        String tableName = StringUtil.toSqlColumn(model);
        String sql = StrUtil.format("select {} from {} where {}={} and enterprise_id = {} ",
                StringUtil.toSqlColumn(relationField),
                tableName,
                StringUtil.toSqlColumn(mainField),
                id,
                enterpriseId
        );
        List<Map<String, Object>> maps = jdbcService.find(sql);
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < maps.size(); i++) {
            Object o = maps.get(i).get(relationField);
            Long _id = Long.parseLong(o.toString());
            ids.add(_id);
        }
        Map<String,Object> data = new HashMap<>();
        data.put(relationField,StringUtil.concatStr(ids,","));
        data.put("id",id);
        data.put(mainField,id);
        return Result.success(data);
    }

    //重新保存关联表数据
    @RequestMapping("/{model}/reSaveRelation/{mainField}/{relationField}")
    public Result reSaveRelation(@PathVariable("model") String model,
                              @PathVariable("mainField") String mainField,
                              @PathVariable("relationField") String relationField,
                              @RequestBody Map<String,Object> params) {
        String tableName = StringUtil.toSqlColumn(model);
        Long mainId = Long.parseLong(params.get(mainField).toString());
        String[] relationIds = params.get(relationField).toString().split(",");
        List<Long> relationIdList = new ArrayList<>();
        List<Long> oldIds = jdbcService.findForObject(StrUtil.format("select {} from {} where {} = {}",
                StringUtil.toSqlColumn(relationField),
                tableName,
                StringUtil.toSqlColumn(mainField),
                mainId
        ),Long.class);
        for(String s:relationIds){
            if(StringUtils.isNotBlank(s)){
                Long id = Long.parseLong(s);
                relationIdList.add(id);
            }
        }

        //新-旧=需要新增的
        //旧-新=需要删除的
        List<Long> addIds = CollectionUtil.remove(relationIdList,oldIds);
        List<Long> delIds = CollectionUtil.remove(oldIds,relationIdList);
        List<Map<String,Object>> list = new ArrayList<>();
        for(Long relationId:addIds){
            Map<String,Object> obj = new HashMap<>();
            obj.put(mainField,mainId);
            obj.put(relationField,relationId);
            list.add(obj);
        }

        jdbcService.transactionOption(() -> {
            if(!delIds.isEmpty()){
                jdbcService.delete(StrUtil.format("delete from {} where {} = {} and {} in ({})",
                        tableName,
                        StringUtil.toSqlColumn(mainField),
                        mainId,
                        StringUtil.toSqlColumn(relationField),
                        StringUtil.concatStr(delIds,",")
                ));
            }
            if(!addIds.isEmpty()){
                jdbcService.bathSaveOrUpdate(list,tableName);
            }
        });
        return Result.success("操作成功");
    }

    //重新保存关联表数据---增加企业id条件
    @RequestMapping("/{model}/reSaveRelationForEnterprise/{mainField}/{relationField}")
    public Result reSaveRelationForEnterprise(@PathVariable("model") String model,
                                 @PathVariable("mainField") String mainField,
                                 @PathVariable("relationField") String relationField,
                                 @RequestBody Map<String,Object> params) {
        Long enterpriseId = SessionContext.getSession().getEnterpriseId();
        String tableName = StringUtil.toSqlColumn(model);
        Long mainId = Long.parseLong(params.get(mainField).toString());
        String[] relationIds = params.get(relationField).toString().split(",");
        List<Long> relationIdList = new ArrayList<>();
        List<Long> oldIds = jdbcService.findForObject(StrUtil.format("select {} from {} where {} = {} and enterprise_id = {} ",
                StringUtil.toSqlColumn(relationField),
                tableName,
                StringUtil.toSqlColumn(mainField),
                mainId,
                SessionContext.getSession().getEnterpriseId()
        ),Long.class);
        for(String s:relationIds){
            if(StringUtils.isNotBlank(s)){
                relationIdList.add(Long.parseLong(s));
            }
        }
        //新-旧=需要新增的
        //旧-新=需要删除的
        List<Long> addIds = CollectionUtil.remove(relationIdList,oldIds);
        List<Long> delIds = CollectionUtil.remove(oldIds,relationIdList);
        List<Map<String,Object>> list = new ArrayList<>();
        for(Long relationId:addIds){
            Map<String,Object> obj = new HashMap<>();
            obj.put(mainField,mainId);
            obj.put(relationField,relationId);
            obj.put("enterpriseId",enterpriseId);
            list.add(obj);
        }

        jdbcService.transactionOption(() -> {
            if(!delIds.isEmpty()){
                jdbcService.delete(StrUtil.format("delete from {} where {} = {} and enterprise_id = {} and {} in ({})",
                    tableName,
                    StringUtil.toSqlColumn(mainField),
                    mainId,
                    enterpriseId,
                    StringUtil.toSqlColumn(relationField),
                    StringUtil.concatStr(delIds,",")
                ));
            }
            if(!addIds.isEmpty()){
                jdbcService.bathSaveOrUpdate(list,tableName);
            }
        });
        return Result.success("操作成功");
    }

    //增加关联表数据
    @RequestMapping("/{model}/addRelation/{mainField}/{relationField}")
    public Result addRelation(@PathVariable("model") String model,
                              @PathVariable("mainField") String mainField,
                              @PathVariable("relationField") String relationField,
                              @RequestBody Map<String,Object> params) {
        String tableName = StringUtil.toSqlColumn(model);
        Long mainId = Long.parseLong(params.get(mainField).toString());
        String[] relationIds = params.get(relationField).toString().split(",");
        List<Long> relationIdList = new ArrayList<>();
        for(String s:relationIds){
            if(StringUtils.isNotBlank(s)){
                relationIdList.add(Long.parseLong(s));
            }
        }
        if(relationIdList.isEmpty()){
            return Result.success();
        }

        String existsSql = StrUtil.format("select {} from {} where {}={} ",
                StringUtil.toSqlColumn(relationField),
                tableName,
                StringUtil.toSqlColumn(mainField),
                mainId
                );
        boolean repeated = false;
        List<Map<String, Object>> maps = jdbcService.find(existsSql);
        for (int i = 0; i < maps.size(); i++) {
            Object o = maps.get(i).get(relationField);
            Long _id = Long.parseLong(o.toString());
            if(relationIdList.contains(_id)){
                repeated = true;
                relationIdList.remove(_id);
            }
        }
        List<Map<String,Object>> list = new ArrayList<>();
        for(Long relationId:relationIdList){
            Map<String,Object> obj = new HashMap<>();
            obj.put(mainField,mainId);
            obj.put(relationField,relationId);
            list.add(obj);
        }
        jdbcService.bathSaveOrUpdate(list,tableName);
        if(repeated){
            return Result.success("操作成功,重复添加数据已排除");
        }else{
            return Result.success();
        }
    }
    @RequestMapping("/{formCode}/getJson")
    public Result getJson(Long id, @PathVariable("formCode") String formCode) {
        Result<Map<String,Object>> result = this.get(id, formCode);
        Map<String, Object> data = result.getData();
        return Result.success(MapUtil.builder().put("json", JSONUtil.toJsonPrettyStr(data)).build());
    }
    @RequestMapping("/{formCode}/get")
    public Result<Map<String,Object>> get(Long id, @PathVariable("formCode") String formCode) {
        Map<String,Object> data = new HashMap<>();
        Form form = formService.get(formCode);
        if(id == null){
            List<FormField> formFields = form.getFormFields();
            for(FormField formField:formFields){
                if(StringUtils.isNotBlank(formField.getValue())){
                    data.put(formField.getField(),SessionContext.getTemplateValue(formField.getValue()));
                }
                if(ComponentType.InputTable.equals(formField.getComponentType())){
                    //输入表格
                    data.put(formField.getField(),new ArrayList<>());
                }
            }
        }else{
            if(StringUtils.isNotBlank(form.getTableName())){
                String tableName = StringUtil.toSqlColumn(form.getTableName());
                Map<String, Object> obj = jdbcService.getById(tableName, id);

                List<FormField> formFields = form.getFormFields();

                for(FormField formField:formFields){
                    Object value = obj.get(formField.getField());

                    if(ComponentType.InputTable.equals(formField.getComponentType())){
                        //输入表格
                        String format = formField.getFormat();

                        if(StringUtils.isBlank(format)){
                            continue;

                        }
                        String[] arr = format.split(",");
                        if(arr.length != 2){
                            continue;
                        }
                        //表单编号
                        String fCode = arr[0];
                        //关联字段
                        String refField = arr[1];
                        Form refForm = formService.get(fCode);
                        List<Map<String,Object>> items = jdbcService.find(
                                StrUtil.format(
                                        "select * from {} where {} = ? order by seq asc ",
                                        StringUtil.toSqlColumn(refForm.getTableName()),
                                        StringUtil.toSqlColumn(refField)
                                ),
                                id
                        );
                        List<Map<String,Object>> itemDatas = new ArrayList<>();
                        for(Map<String,Object> itemObj:items){
                            Map<String,Object> itemData = new HashMap<>();
                            for(FormField itemField:refForm.getFormFields()){
                                Object itemFieldValue = itemObj.get(itemField.getField());
                                if(itemFieldValue == null){
                                    continue;
                                }
                                if(itemFieldValue instanceof LocalDateTime){
                                    String itemFormat = StrUtil.isBlank(itemField.getFormat()) ? "yyyy-MM-dd":formField.getFormat();
                                    String itemRealValue = DateUtil.format((LocalDateTime) itemFieldValue, itemFormat);
                                    itemObj.put(itemField.getField(),itemRealValue);
                                }
                                itemData.put(itemField.getField(),itemObj.get(itemField.getField()));
                            }
                            itemDatas.add(itemData);
                        }
                        data.put(formField.getField(),itemDatas);
                        continue;
                    }

                    if(value == null){
                        continue;
                    }
                    if(value.getClass().getSimpleName().toLowerCase().contains("date")){
                        String format = StrUtil.isBlank(formField.getFormat()) ? "yyyy-MM-dd":formField.getFormat();
                        String realValue = Util.dateFormat(value, format);
                        obj.put(formField.getField(),realValue);
                    }
                    data.put(formField.getField(),obj.get(formField.getField()));
                }
            }
        }

        if(StringUtils.isNotBlank(form.getInitSql())){
            Map<String,Object> params = new HashMap<>();
            params.put("id", id==null? "null": id);
            SessionContext.putUserSessionParams(params);
            String initSql = TemplateUtil.getValue(form.getInitSql(),params);
            String[] sqls = StringUtil.splitStr(initSql, ";");
            for(String sql:sqls){
                List<Map<String, Object>> list = jdbcService.find(sql);
                if(!list.isEmpty()){
                    Map<String, Object> obj = list.get(0);
                    data.putAll(obj);
                }
            }
        }
        if(StringUtils.isNotBlank(form.getFormEvent())){
            FormEvent formEvent = SpringUtil.getBean(form.getFormEvent());
            formEvent.init(data,form);
        }
        return Result.success(data);
    }
    @RequestMapping("/{formCode}/copy")
    public Result copy(Long copyId, @PathVariable("formCode") String formCode) {
        if(copyId == null){
            return Result.success();
        }
        Result result = this.get(copyId, formCode);
        Map<String,Object> data = (Map<String, Object>) result.getData();
        data.put("id","");
        data.put("copyId",copyId);
        return result;
    }
}
