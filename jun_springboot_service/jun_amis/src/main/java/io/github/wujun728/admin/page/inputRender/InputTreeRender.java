package io.github.wujun728.admin.page.inputRender;

import cn.hutool.extra.spring.SpringUtil;
import io.github.wujun728.admin.page.data.InputField;
import io.github.wujun728.admin.page.data.Page;
import io.github.wujun728.admin.page.service.PageService;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class InputTreeRender extends InputDefaultRender{
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        if(StringUtils.isNotBlank(field.getFormat())){
            PageService pageService = SpringUtil.getBean(PageService.class);
            Page page = pageService.get(field.getFormat());
            config.put("labelField",page.getLabelField());
            config.put("valueField",page.getValueField());
            config.put("searchable",true);
            config.put("withChildren",true);
            config.put("onlyChildren",true);
            config.put("source","/admin/page/options/"+field.getFormat());
//            Map<String, Object> myConfig = pageService.optionConfig(field.getFormat());
//            config.putAll(myConfig);
            config.put("initiallyOpen",false);
            config.put("type","input-tree");
            config.put("width","100%");
        }
    }
}
