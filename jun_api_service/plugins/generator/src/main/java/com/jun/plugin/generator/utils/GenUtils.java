package com.jun.plugin.generator.utils;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.jun.plugin.common.exception.BusinessException;

import com.jun.plugin.common.utils.DateUtils;
import com.jun.plugin.generator.code.TableInfo;
import com.jun.plugin.generator.entity.ColumnEntity;
import com.jun.plugin.generator.entity.TableEntity;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器 工具类
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
public class GenUtils {

    static Configuration config = getConfig();

    public static List<String> getTemplates() {
        if(false){
            return getTemplates2();
        }else{
            List<String> templates = new ArrayList<>();
            templates.add("vm/Entity.java.vm");
            templates.add("vm/Dao.java.vm");
            templates.add("vm/Dao.xml.vm");
            templates.add("vm/Service.java.vm");
            templates.add("vm/ServiceImpl.java.vm");
            templates.add("vm/Controller.java.vm");
            templates.add("vm/menu.sql.vm");
            templates.add("vm/list.html.vm");
            return templates;
        }
    }

    public static List<String> getTemplates2() {
        List<String> templates = new ArrayList<String>();

        //java代码模板
        templates.add("vm2/model/Entity.java.vm");
        //templates.add("auto_code/model/EntityExample.java.vm");
        templates.add("vm2/mapperxml/EntityMapper.xml.vm");
        templates.add("vm2/service/EntityService.java.vm");
        templates.add("vm2/service/impl/EntityServiceImpl.java.vm");
        templates.add("vm2/mapper/EntityMapper.java.vm");
        templates.add("vm2/controller/EntityController.java.vm");
        //前端模板
        templates.add("vm2/html/list.html.vm");
        templates.add("vm2/html/add.html.vm");
        templates.add("vm2/html/edit.html.vm");
        //sql模板
        templates.add("vm2/sql/menu.sql.vm");
        //templates.add("auto_code/说明.txt.vm");
        return templates;
    }

    /**
     * 生成代码
     */
    public static Map<String, String>  generatorCode(Map<String, String> table, List<Map<String, String>> columns, ZipOutputStream zip,Boolean isView) {
        Map<String, String> velocityMap = new HashMap<String, String>();

        //配置信息
        Configuration config = getConfig();
        boolean hasBigDecimal = false;
        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName"));
        tableEntity.setComments(table.get("tableComment"));
        //表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(), config.getStringArray("tablePrefix"));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));
        tableEntity.setClassNameLower(className.toLowerCase());

        //列信息
        List<ColumnEntity> columsList = new ArrayList<>();
        for (Map<String, String> column : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName"));
            columnEntity.setDataType(column.get("dataType"));
            columnEntity.setComments(column.get("columnComment"));
            columnEntity.setExtra(column.get("extra"));
            columnEntity.setMaxLength(String.valueOf(column.get("maxLength")));
            columnEntity.setIsNull(column.get("isNull"));

            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType(), "unknowType");
            columnEntity.setAttrType(attrType);
            if (!hasBigDecimal && "BigDecimal".equals(attrType)) {
                hasBigDecimal = true;
            }
            //是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null) {
                tableEntity.setPk(columnEntity);
            }

            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);

        //没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        String mainPath = config.getString("mainPath");
        mainPath = StringUtils.isBlank(mainPath) ? "com.jun.plugin" : mainPath;
        //封装模板数据
        Map<String, Object> map = new HashMap<>(15);
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("classNameLower", tableEntity.getClassNameLower());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("mainPath", mainPath);
        map.put("package", config.getString("package"));
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", DateUtils.dateTime());
        map.put("identity", IdWorker.getId());
        map.put("addId", IdWorker.getId());
        map.put("updateId", IdWorker.getId());
        map.put("deleteId", IdWorker.getId());
        map.put("selectId", IdWorker.getId());
        
        
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
                if(!isView){
                    //添加到zip
                    zip.putNextEntry(new ZipEntry(Objects.requireNonNull(getFileName(template, tableEntity.getClassName(), config.getString("package"), config.getString("package")))));
                    IOUtils.write(sw.toString(), zip, "UTF-8");
                    IOUtils.closeQuietly(sw);
                    zip.closeEntry();
                }
            } catch (IOException e) {
                throw new BusinessException("渲染模板失败，表名：" + tableEntity.getTableName());
            }
            velocityMap.put(template.substring(template.lastIndexOf("/") + 1, template.lastIndexOf(".vm")), sw.toString());
        }
        return velocityMap;

    }


    /**
     * 预览方法
     *
     * @param tableInfo 数据库表
     * @return
     * @author fuce
     * @Date 2021年1月18日 上午2:10:55
     */
    public static Map<String, String> viewAuto(TableInfo tableInfo, String tableName , String tableComment) {
        Map<String, String> velocityMap = new HashMap<String, String>();

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class" , "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        Map<String, Object> map = new HashMap<>();
        //数据库表数据
        map.put("tableInfo" , tableInfo);
        //字段集合
        map.put("beanColumns" , tableInfo.getBeanColumns());
        //配置文件
        map.put("SnowflakeIdWorker" , "SnowflakeIdWorker.class");
        //class类路径
        map.put("parentPack" , config.getString("package"));
        //作者
        map.put("author" , config.getString("author"));
        //时间
        map.put("datetime" , new DateTime());
        //sql需要的权限父级pid
        map.put("pid" , "0");

        VelocityContext velocityContext = new VelocityContext(map);
        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            StringWriter sw = new StringWriter();
            tpl.merge(velocityContext, sw);
            System.out.println("输出模板");
            System.out.println(sw);
            System.out.println("输出模板 end");
            velocityMap.put(template.substring(template.lastIndexOf("/") + 1, template.lastIndexOf(".vm")), sw.toString());
        }
        return velocityMap;
    }


    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String field) {
        String[] fields = field.split("_");
        StringBuilder sbuilder = new StringBuilder(fields[0]);
        for (int i = 1; i < fields.length; i++) {
            char[] cs = fields[i].toCharArray();
            cs[0] -= 32;
            sbuilder.append(String.valueOf(cs));
        }
        return sbuilder.toString().substring(0, 1).toUpperCase() + sbuilder.toString().substring(1);
    }


    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String[] tablePrefixArray) {
        tableName = tableName.toLowerCase();
        if (null != tablePrefixArray && tablePrefixArray.length > 0) {
            for (String tablePrefix : tablePrefixArray) {
                tablePrefix = tablePrefix.toLowerCase();
                tableName = tableName.replace(tablePrefix, "");
            }
        }
        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new BusinessException("获取配置文件失败");
        }
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, String className, String packageName, String targetPath) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator;
        }

        if (template.contains("Entity.java.vm")) {
            return packagePath + "entity" + File.separator + className + "Entity.java";
        }

        if (template.contains("Dao.java.vm")) {
            return packagePath + "mapper" + File.separator + className + "Mapper.java";
        }

        if (template.contains("Service.java.vm")) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm")) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm")) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains("Dao.xml.vm")) {
            return "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + className + "Mapper.xml";
        }

        if (template.contains("menu.sql.vm")) {
            return className.toLowerCase() + "_menu.sql";
        }

        if (template.contains("generator.html.vm")) {
            return "main" + File.separator + "resources" + File.separator + "templates2" + File.separator + className.toLowerCase() + File.separator + "list" + ".html";
        }
        return getCoverFileName(template,className,packageName,config.getString("parentPath"));

//        return null;
    }
    // ****************************************************************************************************************
    // ****************************************************************************************************************
    // ****************************************************************************************************************

    /**
     * @param template
     * @return
     * @author fuce
     * @Date 2021年1月17日 下午6:40:57
     */
    public static String getCoverFileName(String template, String className, String packageName, String targetPath) {

        String separator = File.separator;
        String packagePath = targetPath + separator + "src" + separator + "main" + separator + "java" + separator;
        String resourcesPath = targetPath + separator + "src" + separator + "main" + separator + "resources" + separator;
        ;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace("." , separator) + separator;
        }

        //model.java
        if (template.contains("Entity.java.vm")) {
            return packagePath + "model" + separator + "original" + separator + className + ".java";
        }
//        if(template.contains("EntityExample.java.vm")) {//modelExample.java
//        	return packagePath+"model" +separator+ "auto" + separator + className + "Example.java";
//        }

        //daomapper.java
        if (template.contains("EntityMapper.java.vm")) {
            return packagePath + "Mapper" + separator  + "original" + separator+ className + "Mapper.java";
        }

        //daomapper.xml
        if (template.contains("EntityMapper.xml.vm")) {
            return resourcesPath + "mybatis" + separator  + "auto" + separator+ className + "Mapper.xml";
        }

        if (template.contains("EntityService.java.vm")) {
            return packagePath + "Service" + separator + "original" + separator+ "" + className + "Service.java";
        }

        if (template.contains("EntityServiceImpl.java.vm")) {
            return packagePath + "Service" + separator + "impl" + separator + className + "ServiceImpl.java";
        }

        if (template.contains("EntityController.java.vm")) {
            return packagePath + "Controller" + separator  + "original" + separator+ className + "Controller.java";
        }

        if (template.contains("generator.html.vm")) {
            return resourcesPath + "templates" + separator + "admin" + separator + firstLowerCase(className) + separator + "generator.html";
        }

        if (template.contains("add.html.vm")) {
            return resourcesPath + "templates" + separator + "admin" + separator + firstLowerCase(className) + separator + "add.html";
        }

        if (template.contains("edit.html.vm")) {
            return resourcesPath + "templates" + separator + "admin" + separator + firstLowerCase(className) + separator + "edit.html";
        }

        if (template.contains("menu.sql.vm")) {
            return resourcesPath + "sql" + separator + "menu.sql";
        }
        return "";
    }

    /**
     * 列名转换成Java属性名
     *
     * @param columnName
     * @return
     */
//    public String columnToJava(String columnName) {
//        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_" , "");
//    }

    /**
     * 表名转换成Java类名
     *
     * @param tableName
     * @return
     */
    public String tableToJava(String tableName) {
        String tablePrefix = Objects.requireNonNull(GenUtils.getConfig().getString("tablePrefix"));
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replaceFirst(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    public static String firstLowerCase(String name) {
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;

    }


    /**
     * 创建单表
     *
     * @author fuce
     * @Date 2019年8月24日 下午11:44:54
     */
    public static void autoCodeOneModel(TableInfo tableInfo, String tableName ,String tableComment) {
        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class" , "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        Map<String, Object> map = new HashMap<>();
        //数据库表数据
        map.put("tableInfo" , tableInfo);
        //字段集合
        map.put("beanColumns" , tableInfo.getBeanColumns());
        //配置文件
        map.put("SnowflakeIdWorker" , "SnowflakeIdWorker.class");
        //class类路径
        map.put("parentPack" , config.getString("package"));
        //作者
        map.put("author" , config.getString("author"));
        //时间
        map.put("datetime" , new DateTime());
        //sql需要的权限父级pid
        map.put("pid" , "0");

        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        /*
         * if(vhtml!=true) { templates.remove("auto_code/html/generator.html.vm");
         * templates.remove("auto_code/html/add.html.vm");
         * templates.remove("auto_code/html/edit.html.vm"); } if (vController!=true) {
         * templates.remove("auto_code/controller/EntityController.java.vm"); } if
         * (vService!=true) {
         * templates.remove("auto_code/service/EntityService.java.vm"); } if
         * (vMapperORdao!=true) { templates.remove("auto_code/model/Entity.java.vm");
         * templates.remove("auto_code/model/EntityExample.java.vm");
         * templates.remove("auto_code/mapperxml/EntityMapper.xml.vm");
         * templates.remove("auto_code/mapper/EntityMapper.java.vm"); }
         */
        for (String template : templates) {
            try {
                String targetPath = config.getString("parentPath");
                String filepath = getCoverFileName(template, tableInfo.getJavaTableName(), config.getString("package"), targetPath);
                //            读取模板
                Template tpl = Velocity.getTemplate(template, "UTF-8");
                File file = new File(filepath);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                if (!file.exists()) {
                    file.createNewFile();
                }
                try (FileOutputStream outStream = new FileOutputStream(file);
                     OutputStreamWriter writer = new OutputStreamWriter(outStream, "UTF-8");
                     BufferedWriter sw = new BufferedWriter(writer)) {
                    tpl.merge(context, sw);//进行模板渲染
                    sw.flush();
                    System.out.println("成功生成Java文件:" + filepath);
                }
            } catch (IOException e) {
                try {
                    throw new Exception("渲染模板失败，表名：" + "c" + "\n" + e.getMessage());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }



    /**
     * 自动生成压缩文件方法
     *
     * @param tableInfo
     * @param zip
     * @author fuce
     * @Date 2021年1月17日 下午7:37:50
     */
    public static void autoCodeOneModel(TableInfo tableInfo, String tableName ,String tableComment, ZipOutputStream zip) {
        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class" , "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        Map<String, Object> map = new HashMap<>();
        //数据库表数据
        map.put("tableInfo" , tableInfo);
        //字段集合
        map.put("beanColumns" , tableInfo.getBeanColumns());
        //配置文件
        map.put("SnowflakeIdWorker" , "SnowflakeIdWorker.class");
        //class类路径
        map.put("parentPack" , config.getString("package"));
        //作者
        map.put("author" ,  config.getString("author"));
        //时间
        map.put("datetime" , new DateTime());
        //sql需要的权限父级pid
        map.put("pid" , "0");
        VelocityContext velocityContext = new VelocityContext(map);
        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            try {
                String filepath = getCoverFileName(template, tableInfo.getJavaTableName(), config.getString("package"), "");
                Template tpl = Velocity.getTemplate(template, "UTF-8");
                StringWriter sw = new StringWriter();
                tpl.merge(velocityContext, sw);
                zip.putNextEntry(new ZipEntry(filepath));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                try {
                    throw new Exception("渲染模板失败，表名：" + "c" + "\n" + e.getMessage());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


}
