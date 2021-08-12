package com.pearadmin.generate.tools;

import java.util.Properties;

import com.pearadmin.common.constant.SystemConstant;
import org.apache.velocity.app.Velocity;

/**
 * Describe: 模板引擎工厂
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public class VelocityInitializer
{
    /**
     * 初 始 化 模 板 引 擎
     * */
    public static void initVelocity()
    {
        Properties p = new Properties();
        try
        {
            p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            p.setProperty(Velocity.ENCODING_DEFAULT, SystemConstant.UTF8);
            p.setProperty(Velocity.OUTPUT_ENCODING, SystemConstant.UTF8);
            Velocity.init(p);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
