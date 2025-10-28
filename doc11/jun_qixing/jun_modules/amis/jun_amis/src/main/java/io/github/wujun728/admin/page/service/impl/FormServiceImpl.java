package io.github.wujun728.admin.page.service.impl;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.BaseData;
import io.github.wujun728.admin.common.config.SessionContext;
import io.github.wujun728.admin.common.service.impl.AbstractCacheService;
import io.github.wujun728.admin.db.data.ColumnMeta;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.page.constants.ComponentType;
import io.github.wujun728.admin.page.constants.DataType;
import io.github.wujun728.admin.page.constants.FormType;
import io.github.wujun728.admin.page.constants.Whether;
import io.github.wujun728.admin.page.data.*;
import io.github.wujun728.admin.page.service.*;
import io.github.wujun728.admin.util.SeqComparator;
import io.github.wujun728.admin.util.SpringUtil;
import io.github.wujun728.admin.util.StringUtil;
import io.github.wujun728.admin.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service("formService")
@Slf4j
public class FormServiceImpl extends AbstractCacheService<Form> implements FormService {
    @Resource
    JdbcService jdbcService;
    @Resource
    PageConfigService pageConfigService;
    @Resource
    InputFieldService inputFieldService;
    @Resource
    @Lazy
    PageService pageService;

    @Override
    public void save(Form form){
       Form oForm = jdbcService.getById(Form.class, form.getId());
        if(oForm!=null && !form.getCode().equals(oForm.getCode())){
            super.invalid(oForm.getCode());
        }
        oForm = this.get(form.getCode());
        if(!form.equals(oForm)){
            jdbcService.saveOrUpdate(form);
        }
        Collections.sort(form.getFormButtons(), SeqComparator.instance);
        Collections.sort(form.getFormFields(), SeqComparator.instance);
        Collections.sort(form.getFormRefs(), SeqComparator.instance);

        this.compareAndUpdate(form, FormField.class,form.getFormFields(),oForm == null ? null :oForm.getFormFields());
        this.compareAndUpdate(form, FormRef.class,form.getFormRefs(),oForm == null ? null :oForm.getFormRefs());
        this.compareAndUpdate(form, FormButton.class,form.getFormButtons(),oForm == null ? null :oForm.getFormButtons());

        super.invalid(form.getCode());
    }

    private void compareAndUpdate(Form form,Class<?> clz,List<?> objects,List<?> oldObjects){
        boolean change = false;
        if(oldObjects == null || objects.size() != oldObjects.size()){
            change = true;
        }else{
            for(int i=0;i<objects.size();i++){
                Object o = objects.get(i);
                ReflectUtil.setFieldValue(o,"seq",i+1);
                ReflectUtil.setFieldValue(o,"formId",form.getId());
                Object oldObj = oldObjects.get(i);
                if(!o.equals(oldObj)){
                    change = true;
                    break;
                }
            }
        }
        if(change){
            jdbcService.delete(StrUtil.format("delete from {} where form_id = ? ", StringUtil.toSqlColumn(clz.getSimpleName())), form.getId());
            for(int i=0;i<objects.size();i++){
                Object o = objects.get(i);
                ReflectUtil.setFieldValue(o,"seq",i+1);
                ReflectUtil.setFieldValue(o,"formId",form.getId());
                ReflectUtil.setFieldValue(o,"id",null);

                jdbcService.saveOrUpdate((BaseData) o);
            }
        }
    }

    @Override
    public Form get(Long id){
        Form form = jdbcService.getById(Form.class, id);
        return get(form);
    }

    private Form get(Form form){
        if(form == null || form.getId() == null){
            return form;
        }
        List<FormField> formFields = jdbcService.find(FormField.class, "formId", form.getId());
        form.setFormFields(formFields);

        List<FormRef> formRefs = jdbcService.find(FormRef.class, "formId", form.getId());
        form.setFormRefs(formRefs);

        List<FormButton> formButtons = jdbcService.find(FormButton.class, "formId", form.getId());
        form.setFormButtons(formButtons);
        return form;
    }

    @Override
    public Form load(String code){
        Form form = jdbcService.findOne(Form.class, "code", code);
        return get(form);
    }

    @Override
    public void del(Form form) {
        if (form == null || form.getId() == null) {
            return;
        }
        jdbcService.delete(form);
        super.invalid(form.getCode());
    }

    @Override
    public Map<String, Object> getFormJson(String code, BaseButton button) {
        return getFormJson(get(code),button);
    }
    @Override
    public Map<String, Object> getFormJson(Form f, BaseButton button) {

        Map<String,Object> form = new HashMap<>();
        String formType = FormType.Form;
        if(FormType.Wizard.equals(f.getFormType())){
            formType = FormType.Wizard;
        }
//        if(SpringContextUtil.isTest()){
//            form.put("debug",true);
//        }
        form.put("type",formType);
        form.put("title","");
        if(StrUtil.isNotBlank(f.getTableName())){
            form.put("initApi",StrUtil.format("post:/admin/common/{}/get",f.getCode())+"?id=${id}");
            form.put("api",StrUtil.format("post:/admin/common/{}/saveOrUpdate",f.getCode()));
            if(button != null && StringUtils.isNotBlank(button.getLabel()) && button.getLabel().contains("复制")){
                form.put("initApi",StrUtil.format("post:/admin/common/{}/copy",f.getCode())+"?copyId=${id}");
            }
        }
        if(StrUtil.isNotBlank(f.getInitApi())){
            form.put("initApi",f.getInitApi());
        }
        if(StrUtil.isNotBlank(f.getApi())){
            form.put("api",f.getApi());
        }
        boolean formDisabled = Whether.YES.equals(f.getDisabled());


        List<FormField> formFields = f.getFormFields();
        Map<String,List<FormField>> groupFields = new HashMap<>();
        List<String> groupNames = new ArrayList<>();
        groupNames.add("");
        //解决2.0版本以上  富文本编辑器在dom未加载完成时,initApi先请求完成,报错
        boolean hasInputRichText = false;


        for(FormField field:formFields){
            String groupName = field.getGroupName();
            if(StringUtils.isBlank(groupName)){
                groupName = "";
            }
            if(!groupNames.contains(groupName)){
                groupNames.add(groupName);
            }
            if(!groupFields.containsKey(groupName)){
                groupFields.put(groupName,new ArrayList<>());
            }
            groupFields.get(groupName).add(field);
            if(ComponentType.InputRichText.equals(field.getComponentType())){
                hasInputRichText = true;
            }
        }
        if(hasInputRichText && form.get("initApi") != null){
            String initApi = (String) form.get("initApi");
            if(StringUtils.isNotBlank(initApi)){
                if(initApi.contains("?")){
                    initApi += "&";
                }else{
                    initApi += "?";
                }
                initApi += "_delay=200";
                form.put("initApi",initApi);
            }
        }

        List<Map<String,Object>> body = new ArrayList<>();

        for(String groupName:groupNames){
            List<FormField> fields = groupFields.get(groupName);
            if(fields == null || fields.isEmpty()){
               continue;
            }
            List<Map<String,Object>> items = new ArrayList<>();
            for(FormField field:fields){
                Map<String, Object> config = this.buildFormField(f, field);
                if(Whether.YES.equals(field.getHidden())){
                    body.add(config);
                }else{
                    items.add(config);
                }
            }
            Map<String,Object> grid = new HashMap<>();
            grid.put("type","grid");
            grid.put("columns",items);

            if(StringUtils.isBlank(groupName)){
                body.add(grid);
            }else{
                Map<String,Object> fieldSet = new HashMap<>();
                if(!FormType.Wizard.equals(formType)){
                    fieldSet.put("type","fieldSet");
                }else{
                    fieldSet.put("mode","horizontal");
                }
                fieldSet.put("title",groupName);
                fieldSet.put("collapsable",true);
                fieldSet.put("body",grid);
                body.add(fieldSet);
            }
        }
        if(FormType.Wizard.equals(formType)){
            form.put("steps",body);
        }else{
            form.put("body",body);
        }
        form.put("labelWidth",120);
        form.put("mode","horizontal");
//        if(formDisabled){
//            form.put("static",true);
//        }

        Map<String,Object> dialog = new HashMap<>();
        dialog.put("title",button.getLabel());
        if(SpringUtil.isTest()){
            dialog.put("title", new String[]{button.getLabel()+"-"+ Util.getFormTitle(f)});
        }
        dialog.put("size",f.getSize());
        if("default".equals(f.getSize())){
            dialog.remove("size");
        }

        PageButtonService pageButtonService = cn.hutool.extra.spring.SpringUtil.getBean(PageButtonService.class);
        List<Map<String,Object>> formButtons = new ArrayList<>();
        f.getFormButtons().forEach(b->{
            if(!SessionContext.hasButtonPermission(b.getCode())){
                return;
            }
            Map<String, Object> config = pageButtonService.getButton(b);
            if(Whether.YES.equals(b.getClose())){
                config.put("close",true);
            }
            config.put("reload","mainTable");
            formButtons.add(config);
        });

        if(!f.getFormRefs().isEmpty()){

            List<Object> dialogButtons = new ArrayList<>();
            dialogButtons.add("<span style='line-height:30px;'>基本信息</span>");
            Map<String,Object> saveBtn = new HashMap<>();
            if(!formDisabled && formButtons.isEmpty()){
                Map<String,Object> resetBtn = new HashMap<>();

                saveBtn.put("label","保存");
                saveBtn.put("type","button");
                saveBtn.put("actionType","submit");
                saveBtn.put("primary",true);
                saveBtn.put("close",false);
                saveBtn.put("className","m-l float-right relative jqp-top-btn");

                resetBtn.put("label","重置");
                resetBtn.put("type","button");
                resetBtn.put("actionType","reset");
                resetBtn.put("close",true);
                resetBtn.put("level","warning");
                resetBtn.put("className","m-l float-right relative jqp-top-btn");



                dialogButtons.add(resetBtn);
                dialogButtons.add(saveBtn);
            }

            if(!formButtons.isEmpty()){
                dialogButtons.addAll(formButtons);
                formButtons.clear();
            }

//            dialog.put("actions",dialogButtons);
            dialog.put("actions",new ArrayList<>());



//            form.remove("body");

            List<Map<String,Object>> tabs = new ArrayList<>();
//            tabs.add(grid);

            List<String> targets = new ArrayList<>();
            targets.add("mainTable");
            f.getFormRefs().forEach(ref->{
                Map<String,Object> data = new HashMap<>();
                data.put("id","");

                String[] arr = StringUtil.splitStr(ref.getRefField(), "&");
                for(String p:arr){
                    String[] kv = StringUtil.splitStr(p, "=");
                    data.put(kv[0],kv[1]);
                }

                Map<String, Object> curdJson = pageConfigService.getCurdJson(ref.getRefPageCode());
                curdJson.put("data",data);

                Object title = curdJson.remove("title");

                List<Map<String,Object>> tabContent = new ArrayList<>();
                tabContent.add(curdJson);

                Page page = pageService.get(ref.getRefPageCode());

                Map<String,Object> tab = new HashMap<>();
                tab.put("title",page.getName());
                tab.put("body",tabContent);

                tabs.add(tab);
                targets.add(ref.getRefPageCode()+"Table?"+ref.getRefField());
            });

//            form.put("tabs",tabs);

            List<Map<String,Object>> formBodys = new ArrayList<>();


            Map<String,Object> panel = new HashMap<>();
            panel.put("title","基本信息");
            panel.put("body",body);
            panel.put("type","panel");
            panel.put("header",dialogButtons);

            formBodys.add(panel);

            form.remove("body");

            Map<String,Object> tab = new HashMap<>();
            tab.put("type","tabs");
            tab.put("tabs",tabs);
            formBodys.add(tab);

            form.put("body",formBodys);

            saveBtn.put("reload", StringUtil.concatStr(targets,","));
            log.info("reload::::"+StringUtil.concatStr(targets,","));
        }

        if(formDisabled){
            List<Map<String,Object>> dialogButtons = new ArrayList<>();

            dialog.put("actions",dialogButtons);
        }

        if(!formButtons.isEmpty()){
            List<Map<String,Object>> dialogButtons = new ArrayList<>();
            dialogButtons.addAll(formButtons);

            dialog.put("actions",dialogButtons);
        }
        if(StringUtils.isNotBlank(f.getCustomForm())){
            CustomPage customPage = jdbcService.findOne(CustomPage.class, CustomPage.Fields.code, f.getCustomForm());
            if(customPage != null){
                form = JSONUtil.parseObj(customPage.getContent());
            }
        }

        if(StringUtils.isNotBlank(f.getExtraJson())){
            try{
                JSONObject json = JSONUtil.parseObj(f.getExtraJson());
                form.putAll(json);
            }catch (Exception e){
                throw new RuntimeException(StrUtil.format("表单["+f.getName()+"]扩展json配置错误"));
            }
        }

        dialog.put("body",form);
        return dialog;
    }

    @Override
    public Map<String, Object> buildFormField(Form f, FormField field) {
        boolean formDisabled = Whether.YES.equals(f.getDisabled());
        Map<String,Object> fieldConfig = inputFieldService.buildInputField(field,false);
        if(Whether.YES.equals(field.getHidden())){
            fieldConfig.put("columnClassName","mb-0");
        }else{
            fieldConfig.put("columnClassName","mb-3");
        }
        if(f.getFieldWidth() != null
                && field.getWidth() == null
                && !"input-table".equals(field.getComponentType())
                && !Whether.YES.equals(field.getHidden())){
            fieldConfig.put("xs",f.getFieldWidth());
            fieldConfig.put("sm",f.getFieldWidth());
            fieldConfig.put("md",f.getFieldWidth());
            fieldConfig.put("lg",f.getFieldWidth());
        }
        if(Whether.YES.equals(field.getDisabled()) || formDisabled){
            fieldConfig.put("disabled",true);
        }
        if(StringUtils.isNotBlank(field.getValidations())){
            fieldConfig.put("validations",field.getValidations());
        }
        return fieldConfig;
    }

    @Override
    public Map<String, Object> getPageJson(String code, BaseButton button) {
        //支持两种格式  页面编码,关联id
        //页面编码?字段名=字段名&字段名=字段名
        String pageCode = null;
        Map<String,Object> data = new HashMap<>();
        if(code.contains(",")){
            String[] arr = StringUtil.splitStr(code,",");
            pageCode = arr[0];
            String refField = arr[1];
            data.put("id","");
            data.put(refField,"${id}");
        }else{
            pageCode = code.substring(0,code.indexOf("?"));
            String[] arr = code.substring(code.indexOf("?") + 1).split("&");
            data.put("id","");
            for(String s:arr){
                String[] split = s.split("=");
                data.put(split[0],split[1]);
            }
        }

        Map<String,Object> dialog = new HashMap<>();
        dialog.put("title",button.getLabel());
        dialog.put("size","xl");
        List<Map<String,Object>> dialogButtons = new ArrayList<>();

        dialog.put("actions",dialogButtons);
        Map<String, Object> curdJson = pageConfigService.getCurdJson(pageCode,true);
        curdJson.put("data",data);
        dialog.put("body",curdJson);
        return dialog;
    }

    @Override
    public <T extends BaseData> T getObj(T obj, String formCode) {
        if(obj.getId() == null){
            return obj;
        }
        T dbObj = (T)jdbcService.getById(obj.getClass(), obj.getId());
        if(dbObj == null){
            return obj;
        }
        Form form = get(formCode);
        List<FormField> formFields = form.getFormFields();
        for(FormField formField:formFields){
            Object fieldValue = ReflectUtil.getFieldValue(obj, formField.getField());
            ReflectUtil.setFieldValue(dbObj,formField.getField(),fieldValue);
        }
        return dbObj;
    }

    @Override
    public void reload(Form form) {
        Map<String, FormField> fieldMap = form.getFormFields().stream().collect(Collectors.toMap(FormField::getField, f -> f));

        form.getFormFields().clear();
        List<ColumnMeta> columnMetas = jdbcService.columnMeta(StrUtil.format("select * from {} ",form.getTableName()));
        for(ColumnMeta columnMeta:columnMetas){
            String name = StringUtil.toFieldColumn(columnMeta.getColumnLabel());
            if(fieldMap.containsKey(name)){
                form.getFormFields().add(fieldMap.get(name));
                continue;
            }
            FormField field = new FormField();
            field.setField(name);
            field.setHidden(Whether.NO);
            field.setDisabled(Whether.NO);
            field.setLabel(columnMeta.getColumnComment());
            if(columnMeta.getColumnName() != null && columnMeta.getColumnName().toLowerCase().contains("id")){
                field.setHidden("YES");
            }

            if(columnMeta.getColumnClassName().equalsIgnoreCase(String.class.getCanonicalName())){
                //字符串类型
                if(columnMeta.getColumnType().toLowerCase().contains("longtext")){
                    if(columnMeta.getColumnName() != null && columnMeta.getColumnName().toLowerCase().contains("sql")){
                        field.setType(DataType.SQL);
                    }else if(columnMeta.getColumnName() != null && columnMeta.getColumnName().toLowerCase().contains("js")){
                        field.setType(DataType.JS);
                    }else if(columnMeta.getColumnName() != null && columnMeta.getColumnName().toLowerCase().contains("article")){
                        field.setType(DataType.ARTICLE);
                    }else{
                        field.setType(DataType.LONG_TEXT);
                    }

                }else{
                    field.setType(DataType.STRING);
                }
            }else if(columnMeta.getColumnClassName().toLowerCase().contains("date")){
                field.setType(DataType.DATE);
                field.setFormat("yyyy-MM-dd");
            }else if(columnMeta.getColumnClassName().equalsIgnoreCase(Integer.class.getCanonicalName())){
                field.setType(DataType.INT);
            }else if(columnMeta.getColumnClassName().equalsIgnoreCase(Long.class.getCanonicalName())){
                field.setType(DataType.LONG);
            }else if(columnMeta.getColumnClassName().equalsIgnoreCase(Float.class.getCanonicalName())
                    || columnMeta.getColumnClassName().equalsIgnoreCase(Double.class.getCanonicalName())){
                field.setType(DataType.DOUBLE);
                //两位小数
                field.setExtraJson("{\"precision\":2}");
            }
            form.getFormFields().add(field);
        }

        FormField prev = null;
        for (FormField formField : form.getFormFields()) {
            if(formField.getSeq() == 0){
                if(prev != null){
                    formField.setSeq(prev.getSeq()+10);
                }else{
                    formField.setSeq(10);
                }
            }
            prev = formField;
        }
        Collections.sort( form.getFormFields(), SeqComparator.instance);
    }
}
