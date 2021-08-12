package com.sanri.tools.modules.core.controller;

import com.sanri.tools.modules.core.dtos.SpiderDataParam;
import com.sanri.tools.modules.core.service.classloader.ClassloaderService;
import com.sanri.tools.modules.core.service.classloader.ExtendClassloader;
import com.sanri.tools.modules.core.service.data.JsoupSpiderDataService;
import com.sanri.tools.modules.core.service.data.RandomDataService;
import com.sanri.tools.modules.core.service.data.RegexRandomDataService;
import com.sanri.tools.modules.core.service.data.regex.OrdinaryNode;
import com.sanri.tools.modules.core.service.data.regex.exception.RegexpIllegalException;
import com.sanri.tools.modules.core.service.data.regex.exception.TypeNotMatchException;
import com.sanri.tools.modules.core.service.data.regex.exception.UninitializedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
@Validated
public class RandomDataController {
    @Autowired
    private RandomDataService randomDataService;
    @Autowired
    private RegexRandomDataService regexRandomDataService;
    @Autowired
    private JsoupSpiderDataService jsoupSpiderDataService;

    @Autowired
    private ClassloaderService classloaderService;

    /**
     * 随机填充数据
     * @param className
     * @param classloaderName
     * @return
     * @throws ClassNotFoundException
     */
    @GetMapping("/random")
    public Object randomData(@NotNull String className, @NotNull String classloaderName) throws ClassNotFoundException {
        ClassLoader classloader = classloaderService.getClassloader(classloaderName);
        return randomDataService.randomData(className,classloader);
    }

    /**
     * 随机填充列表数据
     * @param className
     * @param classloaderName
     * @return
     * @throws ClassNotFoundException
     */
    @GetMapping("/random/list")
    public List<Object> randomListData(@NotNull String className, @NotNull String classloaderName) throws ClassNotFoundException {
        List<Object> list = new ArrayList<>();
        ClassLoader classloader = classloaderService.getClassloader(classloaderName);
        for (int i = 0; i < 10; i++) {
            Object randomData = randomDataService.randomData(className, classloader);
            list.add(randomData);
        }
        return list;
    }

    /**
     * 使用正则表达式随机填充数据
     * @param className
     * @param classloaderName
     * @return
     */
    @GetMapping("/random/regex")
    public String regexRandomData(@NotNull String regex) throws RegexpIllegalException, TypeNotMatchException, UninitializedException {
        OrdinaryNode ordinaryNode = new OrdinaryNode(regex);
        String random = ordinaryNode.random();
        return random;
    }


    /**
     * 爬取数据
     * 这里提供的类 需要有 @Request 标记
     * @param className
     * @param classloaderName
     * @return
     */
    @PostMapping("/spider")
    public Object spiderData(@RequestBody SpiderDataParam spiderDataParam) throws IOException, ClassNotFoundException {
        String classloaderName = spiderDataParam.getClassloaderName();
        String className = spiderDataParam.getClassName();
        Map<String, String> params = spiderDataParam.getParams();

        ClassLoader classloader = classloaderService.getClassloader(classloaderName);
        return jsoupSpiderDataService.spiderData(className,classloader,params);
    }
}
