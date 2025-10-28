package io.github.wujun728.admin.rbac.service.impl;

import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.rbac.service.ApiService;
import io.github.wujun728.admin.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.ssssssss.magicapi.core.service.MagicAPIService;

import javax.annotation.Resource;
import java.util.Map;

@Service("apiService")
@Slf4j
public class ApiServiceImpl implements ApiService {

    @Resource
    private MagicAPIService magicAPIService;

    @Override
    public Result<String> call(String api, Map<String, Object> context) {
        if(StringUtils.isNotBlank(api)){
            String[] apis = StringUtil.splitStr(api, "\n");
            for(String a:apis){
                if(StringUtils.isNotBlank(a)){
                    Map<String,Object> result = magicAPIService.call("post", a, context);
                    if((Integer)result.get("status") != 0){
                        return Result.error((String)result.get("msg"));
                    }
                    String data = (String)result.get("data");
                    if(StringUtils.isNotBlank(data)){
                        return Result.error(data);
                    }

                }
            }
        }
        return Result.success();
    }

    @Override
    public Result call(String method, String api, Map<String, Object> context) {
        Map<String,Object> result = magicAPIService.call(method, api, context);
        return JSONUtil.toBean(JSONUtil.toJsonPrettyStr(result),Result.class);
    }
}
