package io.github.wujun728.admin.page.inputRender;

import io.github.wujun728.admin.page.constants.Whether;
import io.github.wujun728.admin.page.data.Form;
import io.github.wujun728.admin.page.data.FormField;
import io.github.wujun728.admin.page.data.InputField;
import io.github.wujun728.admin.page.service.FormService;
import io.github.wujun728.admin.util.SpringUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InputTableRender extends InputDefaultRender{
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        String format = field.getFormat();
        if(StringUtils.isBlank(format)){
            return;
        }
        String[] arr = format.split(",");
        //表单编号
        String formCode = arr[0];
        //关联字段,暂时忽略
        String refField = arr[1];

        config.put("type","input-table");
        FormField srcFormField = (FormField) field;
        if(!Whether.YES.equals(srcFormField.getDisabled())){
            config.put("addable",true);
            config.put("removable",true);
            config.put("draggable",true);
        }
        config.put("needConfirm",false);
        config.put("headerToolbar",new String[]{"filter-toggler"});

        List<Map<String,Object>> columns = new ArrayList<>();
        FormService formService = SpringUtil.getBean(FormService.class);
        Form form = formService.get(formCode);
        List<FormField> formFields = form.getFormFields();

        for(FormField formField:formFields){
            //序号字段也隐藏
            if(Whether.YES.equals(formField.getHidden()) || "seq".equals(formField.getField())){
                continue;
            }
            Map<String, Object> fieldConfig = formService.buildFormField(form, formField);
            columns.add(fieldConfig);
            if(Whether.YES.equals(srcFormField.getDisabled())){
                fieldConfig.put("disabled",true);
            }
            if(formField.getWidth() != null){
                fieldConfig.put("width",formField.getWidth());
            }
            if(SpringUtil.isTest()){
                fieldConfig.put("remark",fieldConfig.remove("labelRemark"));
            }
        }
        config.put("columns",columns);
    }
}
