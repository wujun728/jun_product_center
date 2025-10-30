package io.github.wujun728.admin.page.inputRender;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.page.constants.ComponentType;
import io.github.wujun728.admin.page.constants.DataType;
import io.github.wujun728.admin.page.constants.Whether;
import io.github.wujun728.admin.page.data.InputField;
import io.github.wujun728.admin.page.data.Page;
import io.github.wujun728.admin.page.service.DicCacheService;
import io.github.wujun728.admin.page.service.FormService;
import io.github.wujun728.admin.page.service.PageConfigService;
import io.github.wujun728.admin.page.service.PageService;
import io.github.wujun728.admin.util.SpringUtil;
import io.github.wujun728.admin.util.StringUtil;
import io.github.wujun728.admin.util.Util;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputDefaultRender implements InputRender{
    @Override
    public Map<String, Object> render(InputField field) {
        PageConfigService pageConfigService = cn.hutool.extra.spring.SpringUtil.getBean(PageConfigService.class);

        Map<String,Object> config = new HashMap<>();
        if(field.getField().contains("_")){
            config.put("name", StringUtil.toFieldColumn(field.getField()));
        }else{
            config.put("name", field.getField());
        }

        config.put("label",field.getLabel());
        config.put("xs",12);
        config.put("sm",6);
        config.put("md",4);
        config.put("lg",3);
        config.put("columnClassName","mb-1");
        config.put("clearable",true);
        config.put("value","");
        config.put("placeholder",field.getLabel());

        if(field.getWidth() != null){
            config.put("xs",field.getWidth());
            config.put("sm",field.getWidth());
            config.put("md",field.getWidth());
            config.put("lg",field.getWidth());
        }
        if(StringUtils.isNotBlank(field.getLabelRemark())){
//            Map<String,Object> labelRemark = new HashMap<>();
//            labelRemark.put("type","remark");
//            labelRemark.put("title","提示");
//            labelRemark.put("content",StrUtil.format("<pre>{}</pre>",field.getLabelRemark()));
            config.put("description",field.getLabelRemark());
        }

        boolean isMulti = Whether.YES.equals(field.getMulti());
//        if(StrUtil.isNotBlank(field.getValue())){
//            config.put("value", SessionContext.getTemplateValue(field.getValue()));
//        }
        if(Whether.YES.equals(field.getMust())){
            config.put("required",true);
        }
        if(isMulti){
            config.put("multiple",true);
        }

        if(Whether.YES.equals(field.getHidden())){
            config.put("xs",12);
            config.put("sm",12);
            config.put("md",12);
            config.put("lg",12);
            config.put("label","");
            config.put("type","hidden");
        }else if(DataType.isDate(field.getType())){
            if(ObjectUtil.isNotEmpty(field.getFormat())){
                config.put("format",field.getFormat().replace("yyyy-MM-dd","YYYY-MM-DD"));
                if("yyyy-MM-dd".equals(field.getFormat())){
                    config.put("type","input-date");
                }else if("yyyy-MM-dd HH:mm:ss".equals(field.getFormat())){
                    config.put("type","input-datetime");
                }
            }else{
                config.put("type","input-datetime");
            }
        }else if(DataType.DIC.equals(field.getType())){
            config.put("type","select");
            DicCacheService dicCacheService = cn.hutool.extra.spring.SpringUtil.getBean(DicCacheService.class);
            List<Map<String, Object>> options = dicCacheService.options(field.getFormat());
            if(options.size()>10){
                config.put("searchable",true);
            }
//            config.put("source",StrUtil.format("/options/{}",field.getFormat()));
            config.put("options",options);
        }else if(DataType.Selector.equals(field.getType()) || DataType.SelectorPop.equals(field.getType())){
            Map<String, Object> selectorConfig = pageConfigService.getSelectorConfig(field.getFormat(),field.getField());
            config.putAll(selectorConfig);
        }else if(DataType.isNumber(field.getType())){
            config.put("type","input-number");
        }else{
            config.put("type","input-text");
        }
        if(StringUtils.isNotBlank(field.getComponentType())){
            config.put("type",field.getComponentType());
        }

        if(StringUtils.isNotBlank(field.getOptionSql())){
            JdbcService jdbcService = cn.hutool.extra.spring.SpringUtil.getBean(JdbcService.class);
            List<Map<String, Object>> options = jdbcService.find(field.getOptionSql());
            config.put("options",options);
            config.remove("source");
        }

        if(SpringUtil.isTest()) {
            if (DataType.Selector.equals(field.getType()) || DataType.SelectorPop.equals(field.getType())){
                PageService pageService = SpringUtil.getBean(PageService.class);
                Page page = pageService.get(field.getFormat());
                if(page != null){
                    config.put("labelRemark",field.getField()+"-"+ Util.getPageTitle(page));
                }
            }else if(DataType.DIC.equals(field.getType())){
                config.put("labelRemark",field.getField()+"-"+ Util.getDic(field.getFormat()));
            }else if(ComponentType.InputTable.equals(field.getComponentType())){
                String format = field.getFormat();
                if(!org.apache.commons.lang.StringUtils.isBlank(format)){
                    String[] arr = format.split(",");
                    //表单编号
                    String formCode = arr[0];
                    //关联字段,暂时忽略
                    String refField = arr[1];
                    FormService formService = SpringUtil.getBean(FormService.class);
                    config.put("labelRemark",field.getField()+"-"+ Util.getFormTitle(formService.get(formCode)));
                }
            }else{
                config.put("labelRemark",field.getField()+"-"+ StringUtil.getStr(field.getFormat()));
            }
        }

        this.extra(config,field);
        if(StringUtils.isNotBlank(field.getExtraJson())){
            try{
                JSONObject json = JSONUtil.parseObj(field.getExtraJson());
                config.putAll(json);
            }catch (Exception e){
                throw new RuntimeException(StrUtil.format("字段["+field.getLabel()+"]扩展json配置错误"));
            }
        }
        return config;
    }

    protected void extra(Map<String,Object> config,InputField field){}
}
