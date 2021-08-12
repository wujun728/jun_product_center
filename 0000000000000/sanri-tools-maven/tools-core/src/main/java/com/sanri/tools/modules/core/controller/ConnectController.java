package com.sanri.tools.modules.core.controller;

import com.alibaba.fastjson.JSONObject;
import com.sanri.tools.modules.core.dtos.ConnectDto;
import com.sanri.tools.modules.core.dtos.param.*;
import com.sanri.tools.modules.core.service.file.ConnectService;
import com.sanri.tools.modules.core.utils.URLUtil;
import com.sanri.tools.modules.core.validation.custom.EnumStringValue;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/connect")
@Validated
public class ConnectController {
    @Autowired
    private ConnectService connectService;

    /**
     * 模块列表
     * @return
     */
    @GetMapping("/modules")
    public List<String> modules(){
        return connectService.modules();
    }

    /**
     * 创建一个新模块
     * @param name
     */
    @PostMapping("/createModule")
    public void createModule(@NotNull @EnumStringValue({"database","kafka","redis","zookeeper","mongo"}) String name){
        connectService.createModule(name);
    }

    @PostMapping("/deleteModule")
    public void deleteModule(@NotNull String name) throws IOException {connectService.dropModule(name);}

    /**
     * 指定模块下的连接列表
     * @param module
     * @return
     */
    @GetMapping("/{module}/names")
    public List<String> names(@PathVariable("module") String module){
        return connectService.names(module);
    }

    /**
     * 列出所有连接
     * @return
     */
    @GetMapping("/all")
    public List<ConnectDto> connects(){
        return connectService.selectAll();
    }
    /**
     * 获取连接详情
     * @param module
     * @param connName
     * @return
     */
    @GetMapping("/{module}/{connName}")
    public String content(@PathVariable("module") String module,@PathVariable("connName")String connName) throws IOException {
        return connectService.content(module,connName);
    }

    /**
     * 创建连接
     * @param module
     * @param data 动态数据 ; {@link  AbstractConnectParam}
     * @throws IOException
     */
    @PostMapping("/create/{module}")
    public void createConnect(@PathVariable("module") String module, @RequestBody JSONObject data) throws IOException {
        connectService.createConnect(module,data.toJSONString());
    }

    /**
     * 删除连接; 这个删除不会删除真实连接,真实连接会在项目关闭后释放连接
     * @param module
     * @param connName
     */
    @PostMapping("/dropConnect/{module}/{connName}")
    public void dropConnect(@PathVariable("module") String module,@PathVariable("connName")String connName){
        connectService.dropConnect(module,connName);
    }

    /**
     * 获取连接示例
     * @param module
     * @param format
     * @return
     * @throws IOException
     */
    @GetMapping("/{module}/{format}/example")
    public String connectExampleClass(@PathVariable String module,@PathVariable String format) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("examples/"+module + "." + format + ".example");
        if (classPathResource.exists()){
            return IOUtils.toString(classPathResource.getInputStream(), "utf-8");
        }
        return "";
    }
}
