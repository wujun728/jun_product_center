package com.lu.web.config.init;

import com.lu.web.core.global.GlobalCache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @program sKnowledge_Blog
 * @description: 项目启动完成执行
 * @author: zhanglu
 * @create: 2019-07-06 21:22:00
 */
@Component
public class AfterServiceStarted implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

        //项目根目录
        String rootPath = System.getProperty("user.dir");
        System.out.println("项目根目录 ---> " + rootPath);
        GlobalCache.baseProperties.setRootPath(rootPath);


    }

}
