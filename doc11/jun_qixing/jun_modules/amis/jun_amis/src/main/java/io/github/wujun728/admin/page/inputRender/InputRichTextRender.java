package io.github.wujun728.admin.page.inputRender;

import io.github.wujun728.admin.page.data.InputField;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class InputRichTextRender  extends InputDefaultRender{
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        config.put("receiver","/admin/upload");
        if(StringUtils.isNotBlank(field.getFormat())){
            config.put("size",field.getFormat());
        }

        //使用绝对路径地址
        Map<String, Object> options = new HashMap<>();
        options.put("relative_urls",false);
        options.put("remove_script_host",true);
        config.put("useChunk",false);

        config.put("options",options);
    }
}

