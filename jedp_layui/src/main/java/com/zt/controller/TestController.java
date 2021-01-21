package com.zt.controller;

import com.zt.common.JsonData;
import com.zt.exception.PermissionException;
import com.zt.pojo.vo.TestVolidator;
import com.zt.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        //如果没有log对象，则去安装一下lombok插件即可
        log.info("hello");
        return "hello permission";
    }

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData helloJson(){
        throw new RuntimeException("这是自定义异常");
        //return JsonData.success("hello permission");
    }

    @RequestMapping("/validator.json")
    @ResponseBody
    public JsonData validator(TestVolidator vd){
        ValidatorUtil.checkValidator(vd);
        /*try { //如果在验证不通过的时候进行了添加、更新或删除操作的时候，则会抛出javax.validation.ConstraintViolationException异常
            Map<String, String> map = ValidatorUtil.validator(vd);
            for(Map.Entry<String,String> entry : map.entrySet()){
                log.info("{}->{}",entry.getKey(),entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return JsonData.success("hello permission");
    }
}
