package io.github.wujun728.admin.page.inputRender;

import cn.hutool.extra.spring.SpringUtil;
import io.github.wujun728.admin.page.data.InputField;
import io.github.wujun728.admin.page.service.DicCacheService;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputRatingRender  extends InputDefaultRender{
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        if(StringUtils.isNotBlank(field.getFormat())){
            DicCacheService dicCacheService = SpringUtil.getBean(DicCacheService.class);
            List<Map<String, Object>> options = dicCacheService.options(field.getFormat());

            config.put("count",options.size());

            Map<String,String> texts = new HashMap<>();
            for(Map<String,Object> en:options){
                texts.put((String)en.get("value"),(String)en.get("label"));
            }
            config.put("texts",texts);
        }
    }
}
