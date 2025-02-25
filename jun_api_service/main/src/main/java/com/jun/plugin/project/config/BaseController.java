package com.jun.plugin.project.config;

import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.jun.plugin.common.Result;
//import com.jun.plugin.common.util.SpringContextUtil;
import com.jun.plugin.common.utils.spring.SpringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * 客户信息
 *
 * @author wujun
 * @email wujun728@mail.com
 * @date 2022-02-28 16:28:58
 */
@RequestMapping("/")
@RestController
public class BaseController {

    @ApiOperation("404请求")
    @GetMapping("404")
    public String e404() {
        System.out.println("404............");
        return "这真的是一个404页面，你看看";
    }

    @GetMapping("/")
    public Result root() throws UnknownHostException {
        Environment env = SpringUtil.getApplicationContext().getEnvironment();
        InetAddress inetAddress = Inet4Address.getLocalHost();
        String hostAddress = inetAddress.getHostAddress();
        String serverPort = env.getProperty("server.port");
        String serverPath = env.getProperty("spring.application.name");
        String url = env.getProperty("spring.datasource.url");
        Result result = Result.success("项目启动成功！");
        result.put("local",String.format("本机地址: http://localhost:%s", serverPort));
        result.put("ipadress",String.format("访问地址: http://%s:%s/%s", hostAddress, serverPort, serverPath));
        return result;
    }


//    public static void main(String[] args) {
////        System.out.println(IdUtil.getSnowflake(10,8).nextId());
//    }

}
