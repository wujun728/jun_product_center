package com.lu.modulargenerate.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.lu.core.tips.ErrorTip;
import com.lu.core.tips.SuccessTip;
import com.lu.core.utils.StringUtil;
import com.lu.core.utils.ToolUtil;
import com.lu.core.utils.ZipUtil;
import com.lu.modulargenerate.model.CodeStrategy;
import com.lu.modulargenerate.model.Datasource;
import com.lu.modulargenerate.model.SuperClassConfig;
import com.lu.modulargenerate.service.GeneratorCodeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program sKnowledge_Blog
 * @description: 实现类
 * @author: zhanglu
 * @create: 2019-07-14 16:53:00
 */
@Service
public class GeneratorCodeServiceImpl implements GeneratorCodeService {

    @Override
    public Object check(String driver, String url, String username, String password) throws Exception {
        // 数据源配置
        try {
            DataSourceConfig dbConfig = dataSourceConfig(driver, url, username, password);
            if(dbConfig.getConn() == null){
                return new ErrorTip("连接失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorTip("连接失败！" + e.getMessage());
        }
        return new SuccessTip();
    }

    @Override
    public Object getTableInfo(String driver, String url, String username, String password) throws Exception {
        SuccessTip successTip = new SuccessTip();
        List<TableInfo> tableInfoList = (List<TableInfo>) tableInfo(driver, url, username, password);
        successTip.setCount(Long.valueOf(tableInfoList.size()));
        successTip.setData(tableInfoList);
        return successTip;
    }

    @Override
    public void execute(HttpServletResponse response, String codeStrategyData, String datasourceData) throws Exception {
        CodeStrategy codeStrategy = JSONObject.parseObject(codeStrategyData, CodeStrategy.class);
        Datasource datasource = JSONObject.parseObject(datasourceData, Datasource.class);
        codeTemplate(response, codeStrategy, datasource);
    }

    public DataSourceConfig dataSourceConfig(String driver, String url, String username, String password){
        // 数据源配置
        DataSourceConfig dbConfig = new DataSourceConfig();
        dbConfig.setUrl(url);
        dbConfig.setDriverName(driver);
        dbConfig.setUsername(username);
        dbConfig.setPassword(password);
        return dbConfig;
    }

    /**
     * 获取数据库表信息
     * @param driver
     * @param url
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public Object tableInfo(String driver, String url, String username, String password) throws Exception{
        try {
            // 数据源配置
            DataSourceConfig dbConfig = dataSourceConfig(driver, url, username, password);
            ConfigBuilder configBuilder = new ConfigBuilder(null, dbConfig, null, null, null);
            return configBuilder.getTableInfoList();
        } catch (Exception e) {
            e.printStackTrace();
            return "获取数据库表信息异常：" + e;
        }
    }

    /**
     * 构建模版，生成实体
     * @param codeStrategy
     * @param datasource
     */
    public void codeTemplate(HttpServletResponse response, CodeStrategy codeStrategy, Datasource datasource) throws Exception{

        String packagePath = codeStrategy.getPackagePath();
        String moduleName = codeStrategy.getModule();

        // 自定义需要填充的字段 数据库中的字段
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("update_time", FieldFill.INSERT_UPDATE));

        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        //service名称
        gc.setServiceName("%sService");

        //文件逻辑路径
        String projectPath = ToolUtil.getWebRootPath("");
        //文件生成路径
        String filePath = projectPath;
        String timestamp = System.currentTimeMillis() + "";
        filePath = System.getProperty("user.dir") + "/code/" + timestamp;

        String outPath = filePath + "/src/main/java";

        gc.setOutputDir(filePath + "/src/main/java");
        gc.setFileOverride(codeStrategy.isOverride());
        gc.setOpen(false);
        gc.setEnableCache(false);
        gc.setAuthor(codeStrategy.getAuthor());
        gc.setActiveRecord(false);   //活动记录
        // 对于数据库中的使用，只使用java.util.date包下的Date
        gc.setDateType(DateType.ONLY_DATE);
        // 是否生成基础的映射
        gc.setBaseResultMap(true);
        // 是否生成基础的查询列
        gc.setBaseColumnList(true);

        autoGenerator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dbConfig = new DataSourceConfig();
        dbConfig.setUrl(datasource.getUrl());
        dbConfig.setDriverName(datasource.getDriver());
        dbConfig.setUsername(datasource.getUsername());
        dbConfig.setPassword(datasource.getPassword());
        autoGenerator.setDataSource(dbConfig);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setEntity("model");
        pc.setModuleName(moduleName);
        pc.setParent(packagePath);
        autoGenerator.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> configMap = new HashMap<>(2);
                // 放入代码生成时间
                configMap.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                configMap.put("project", codeStrategy.getProject());
                this.setMap(configMap);
            }
        };

        //自定义mapper.xml输出位置
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                /**
                 * 此处设置生成的mapper.xml放在mapper包下
                 */
                return outPath + "/" + pc.getParent().replaceAll("\\.", "/") + "/mapper/mapping/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        autoGenerator.setCfg(cfg);

        // 模板引擎是 freemarker
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("/templates/backEnd/model.java");
        templateConfig.setMapper("/templates/backEnd/mapper.java");
        templateConfig.setService("/templates/backEnd/service.java");
        templateConfig.setServiceImpl("/templates/backEnd/serviceImpl.java");
        templateConfig.setController("/templates/backEnd/controller.java");

        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //实体类常量
//        strategy.setEntityColumnConstant(true);
        //实体字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        //数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体类的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(false);
        //自动填充设置
        strategy.setTableFillList(tableFillList);
        SuperClassConfig superClassConfig = new SuperClassConfig();
        superClassConfig.setControllerSuperClass("com.lu.manage.core.base.controller.BaseController");
//        superClassConfig.setServiceSuperClass("");
//        superClassConfig.setServiceImplSuperClass("");
//        superClassConfig.setMapperSuperClass("");
//        superClassConfig.setEntitySuperClass("com.baomidou.mybatisplus.extension.activerecord.Model");


//        strategy.setSuperEntityClass(superClassConfig.getEntitySuperClass());
        strategy.setEntityLombokModel(true);
        strategy.setSuperControllerClass(superClassConfig.getControllerSuperClass());
        strategy.setSuperServiceClass(superClassConfig.getServiceSuperClass());
        strategy.setSuperServiceImplClass(superClassConfig.getServiceImplSuperClass());
        strategy.setSuperMapperClass(superClassConfig.getMapperSuperClass());
        // 设置数据库表
        strategy.setInclude(codeStrategy.getTableNames());
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(codeStrategy.getPrefix());
        autoGenerator.setStrategy(strategy);

        // 执行
        autoGenerator.execute();

        //获取table信息,用于前端代码生成
        String frontEndPath = projectPath + "/src/main/resources/templates/frontEnd";
        String webappPath = filePath+ "/src/main/webapp/";
        ///modular/${package.ModuleName}/${entity?uncap_first}/
        List<TableInfo> tableInfoList = autoGenerator.getConfig().getTableInfoList();
        tableInfoList.stream().forEach(o -> {
            FileResourceLoader resourceLoader = new FileResourceLoader(frontEndPath,"utf-8");
            try {
                //首字母小写
                String entityName = StringUtil.acronymToLowerCase(o.getEntityName());

                File f = new File(frontEndPath);
                String[] list = f.list();
                System.out.println(list);
                File[] pages = f.listFiles(pathname -> pathname.getName().endsWith(".html.btl"));
                File[] assets = f.listFiles(pathname -> pathname.getName().endsWith(".js.btl"));

                for(File page : pages){  //生成页面
                    String type = "pages";
                    handle(page, o, resourceLoader, webappPath, type, moduleName, entityName);
                }

                for(File asset : assets){    //生成js
                    String type = "assets";
                    handle(asset, o, resourceLoader, webappPath, type, moduleName, entityName);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 把下载的文件夹压缩后，以流的形式返回给前端，并且删除生成的文件
        String zipFile = filePath + ".zip";
        ZipUtil.toZip(filePath, zipFile, true);
        File file = new File(zipFile);
        response.setContentType("application/octet-stream");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename="+new String("LuBoot.zip".getBytes("utf-8"),"iso8859-1"));
        IOUtils.copy(new FileInputStream(file), response.getOutputStream());
        File oldFile = new File(zipFile);
        oldFile.deleteOnExit();

    }

    /**
     * 渲染生成文件
     * @param f
     * @param o
     * @param resourceLoader
     * @param webappPath
     * @param type
     * @param moduleName
     * @param entityName
     * @throws IOException
     */
    public void handle(File f, TableInfo o, FileResourceLoader resourceLoader,
                       String webappPath, String type,
                       String moduleName, String entityName) throws IOException {
        String path = webappPath + type +"/modular/" + moduleName + File.separator + entityName;
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        Configuration configuration = new Configuration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, configuration);
        Template t = gt.getTemplate(f.getName());
        t.binding("flag", "@");
        t.binding("modular", moduleName);
        t.binding("bizEnName", entityName);
        t.binding("bizEnBigName", o.getEntityName());
        t.binding("bizChName", o.getComment());
        t.binding("table", o);
        t.renderTo(new FileOutputStream(path + File.separator + f.getName().replace(".btl", "")));
    }

}
