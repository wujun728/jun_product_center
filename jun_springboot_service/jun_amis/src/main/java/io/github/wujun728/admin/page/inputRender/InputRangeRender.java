package io.github.wujun728.admin.page.inputRender;

import io.github.wujun728.admin.page.data.InputField;
import io.github.wujun728.admin.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class InputRangeRender  extends InputDefaultRender{
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        Double[] values = new Double[]{0D,100D,1D};
        if(StringUtils.isNotBlank(field.getFormat())){
            String[] arr = StringUtil.splitStr(field.getFormat(), ",");
            for(int i=0;i<arr.length;i++){
                values[i] = Double.valueOf(arr[i]);
            }
        }
        config.put("min",values[0]);
        config.put("max",values[1]);
        config.put("step",values[2]);
    }
}
