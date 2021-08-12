package com.sanri.tools.modules.soap.controller;

import com.sanri.tools.modules.soap.dtos.WsdlParam;
import com.sanri.tools.modules.soap.service.WsdlOperation;
import com.sanri.tools.modules.soap.service.WsdlPort;
import com.sanri.tools.modules.soap.service.WsdlService;
import com.sanri.tools.modules.soap.service.WsdlServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/soap")
@Validated
public class SoapController {

    @Autowired
    private WsdlServiceClient wsdlServiceClient;

    /**
     * 目前已经有的 wsdl 解析列表
     * 这个不做存储
     * @return
     */
    @GetMapping("/services")
    public Set<String> services(){
        return wsdlServiceClient.services();
    }

    @GetMapping("/ports")
    public Set<String> ports(@NotNull String wsdl){
        WsdlService wsdlService = wsdlServiceClient.loadWebservice(wsdl);
        Map<String, WsdlPort> wsdlPortMap = wsdlService.getWsdlPortMap();
        return wsdlPortMap.keySet();
    }

    @GetMapping("/{port}/methods")
    public Set<String> methods(@NotNull String wsdl, @PathVariable("port") String port){
        WsdlService wsdlService = wsdlServiceClient.loadWebservice(wsdl);
        if(wsdlService != null){
            WsdlPort wsdlPort = wsdlService.getWsdlPort(port);
            if (wsdlPort != null){
                Map<String, WsdlOperation> wsdlOperationMap = wsdlPort.getWsdlOperationMap();
                return wsdlOperationMap.keySet();
            }
        }
        return null;
    }

    @GetMapping("/{port}/{operation}/input")
    public WsdlParam methodInputParams(@NotNull String wsdl,@PathVariable("port") String port,@PathVariable("operation") String operation){
        WsdlService wsdlService = wsdlServiceClient.loadWebservice(wsdl);
        if(wsdlService != null){
            WsdlPort wsdlPort = wsdlService.getWsdlPort(port);
            if(wsdlPort != null){
                WsdlOperation wsdlOperation = wsdlPort.getWsdlOperation(operation);
                if(wsdlOperation != null){
                    return wsdlOperation.getInput();
                }
            }
        }
        return null;
    }

    @GetMapping("/{port}/{operation}/output")
    public WsdlParam methodOutputParam(@NotNull String wsdl,@PathVariable("port") String port,@PathVariable("operation") String operation){
        WsdlService wsdlService = wsdlServiceClient.loadWebservice(wsdl);
        if(wsdlService != null){
            WsdlPort wsdlPort = wsdlService.getWsdlPort(port);
            if(wsdlPort != null){
                WsdlOperation wsdlOperation = wsdlPort.getWsdlOperation(operation);
                if(wsdlOperation != null){
                    return wsdlOperation.getOutput();
                }
            }
        }
        return null;
    }

    /**
     * 构建 soap 消息模板,后面让用户输入参数后就可以调用了
     * @param wsdl
     * @param port
     * @param operation
     * @return
     */
    @GetMapping("/{port}/{operation}/build")
    public String buildSoapMessageTemplate(@NotNull String wsdl, @PathVariable("port") String port, @PathVariable("operation") String operation){
        WsdlService wsdlService = wsdlServiceClient.loadWebservice(wsdl);
        if(wsdlService != null){
            WsdlPort wsdlPort = wsdlService.getWsdlPort(port);
            if(wsdlPort != null){
                WsdlOperation wsdlOperation = wsdlPort.getWsdlOperation(operation);
                if(wsdlOperation != null){
                    return wsdlOperation.buildRequestTemplate();
                }
            }
        }
        return null;
    }

    @PostMapping("/{port}/{operation}/request")
    public String sendRequest(@NotNull String wsdl, @PathVariable("port") String port, @PathVariable("operation") String operation, @RequestBody String message) throws IOException {
        WsdlService wsdlService = wsdlServiceClient.loadWebservice(wsdl);
        if(wsdlService != null){
            WsdlPort wsdlPort = wsdlService.getWsdlPort(port);
            if(wsdlPort != null){
                WsdlOperation wsdlOperation = wsdlPort.getWsdlOperation(operation);
                if(wsdlOperation != null){
                    return wsdlOperation.invoke(message);
                }
            }
        }
        return null;
    }
}
