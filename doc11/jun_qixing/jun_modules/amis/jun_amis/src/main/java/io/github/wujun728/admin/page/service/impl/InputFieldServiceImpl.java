package io.github.wujun728.admin.page.service.impl;

import io.github.wujun728.admin.common.constants.Constants;
import io.github.wujun728.admin.page.constants.Whether;
import io.github.wujun728.admin.page.data.InputField;
import io.github.wujun728.admin.page.data.PageQueryField;
import io.github.wujun728.admin.page.inputRender.*;
import io.github.wujun728.admin.page.service.InputFieldService;
import io.github.wujun728.admin.page.inputRender.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("inputFieldService")
public class InputFieldServiceImpl implements InputFieldService {
    @Override
    public Map<String, Object> buildInputField(InputField field,boolean selector) {
        InputRender inputRender = null;
        if(StringUtils.isNotBlank(field.getComponentType())){
            inputRender = inputRenderMap.get(field.getComponentType());
        }
        if(inputRender == null){
            inputRender = inputDefaultRender;
        }
        Map<String, Object> config = inputRender.render(field);
        String name = (String) config.get("name");
        if(selector && field instanceof PageQueryField){
            if(!Whether.YES.equals(((PageQueryField) field).getRef()) && (name == null || !name.toLowerCase().contains("id"))){
                config.put("name", Constants.QUERY_KEY_START+name);
            }
        }
        return config;
    }
    private static InputDefaultRender inputDefaultRender = new InputDefaultRender();
    private static Map<String, InputRender> inputRenderMap = new HashMap<>();
    static {
        inputRenderMap.put("input-text",new InputTextRender());
        inputRenderMap.put("textarea",new TextAreaRender());
        inputRenderMap.put("select",new SelectRender());
        inputRenderMap.put("checkboxes",new CheckBoxRender());
        inputRenderMap.put("input-number",new InputNumberRender());
        inputRenderMap.put("radios",new RadioRender());
        inputRenderMap.put("static",new StaticRender());
        inputRenderMap.put("input-date",new InputDateRender());
        inputRenderMap.put("selector",new SelectorRender());
        inputRenderMap.put("selector-pop",new SelectorPopRender());
        inputRenderMap.put("input-datetime",new InputDateTimeRender());
        inputRenderMap.put("location-picker",new LocationPickerRender());
        inputRenderMap.put("input-password",new InputPasswordRender());
        inputRenderMap.put("input-rich-text",new InputRichTextRender());
        inputRenderMap.put("switch",new SwitchRender());
        inputRenderMap.put("input-date-range",new InputDateRangeRender());
        inputRenderMap.put("input-province",new InputProvinceRender());
        inputRenderMap.put("input-province-city",new InputProvinceCityRender());
        inputRenderMap.put("input-province-city-county",new InputProvinceCityCountyRender());
        inputRenderMap.put("input-excel",new InputExcelRender());
        inputRenderMap.put("input-datetime-range",new InputDateTimeRangeRender());
        inputRenderMap.put("input-color",new InputColorRender());
        inputRenderMap.put("input-month",new InputMonthRender());
        inputRenderMap.put("input-month-range",new InputMonthRangeRender());
        inputRenderMap.put("input-time",new InputTimeRender());
        inputRenderMap.put("input-time-range",new InputTimeRangeRender());
        inputRenderMap.put("input-year",new InputYearRender());
        inputRenderMap.put("input-year-range",new InputYearRangeRender());
        inputRenderMap.put("input-quarter",new InputQuarterRender());
        inputRenderMap.put("input-quarter-range",new InputQuarterRangeRender());
        inputRenderMap.put("input-image",new InputImageRender());
        inputRenderMap.put("input-file",new InputFileRender());
        inputRenderMap.put("input-rating",new InputRatingRender());
        inputRenderMap.put("input-range",new InputRangeRender());
        inputRenderMap.put("input-repeat",new InputRepeatRender());
        inputRenderMap.put("input-tag",new InputTagRender());
        inputRenderMap.put("button-group-select",new InputButtonGroupSelectRender());
        inputRenderMap.put("formula",new FormulaRender());
        inputRenderMap.put("editor",new EditorRender());
        inputRenderMap.put("input-formula",new InputFormulaRender());
        inputRenderMap.put("input-tree",new InputTreeRender());
        inputRenderMap.put("tree-select",new TreeSelectRender());
        inputRenderMap.put("input-table",new InputTableRender());
    }
}
