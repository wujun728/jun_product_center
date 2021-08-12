package com.sanri.tools.modules.dubbo.dtos;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sanri.tools.modules.core.dtos.ClassStruct;
import com.sanri.tools.modules.dubbo.DubboProviderDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DubboInvokeParam  {
    @NotNull
    private String connName;
    @NotNull
    private String serviceName;
    private String classloaderName;
    private String methodName;
    private JSONArray args;
    @NotNull
    private String providerURL;
}
