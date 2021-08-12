package com.pearadmin.generate.tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.hutool.core.date.DateUtil;
import com.pearadmin.common.constant.GenerateConstant;

import com.pearadmin.common.tools.sequence.SequenceUtil;
import com.pearadmin.common.tools.string.StringUtil;
import com.pearadmin.generate.config.GenConfig;
import com.pearadmin.generate.domain.GenTable;
import com.pearadmin.generate.domain.GenTableColumn;
import org.apache.velocity.VelocityContext;
import com.alibaba.fastjson.JSONObject;

/**
 * Describe: 模板引擎工具
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public class VelocityUtils
{
    /** 项目空间路径 */
    private static final String PROJECT_PATH = "main/java";

    /** html空间路径 */
    private static final String TEMPLATES_PATH = "main/resources/templates";

    /**
     * 设置模板变量信息
     */
    public static VelocityContext prepareContext(GenTable genTable)
    {
        String moduleName = genTable.getModuleName();
        String businessName = genTable.getBusinessName();
        String packageName = genTable.getPackageName();
        String tplCategory = genTable.getTplCategory();
        String functionName = genTable.getFunctionName();
        List<String> ids = SequenceUtil.makeStringIds(10);
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tplCategory", genTable.getTplCategory());
        velocityContext.put("tableName", genTable.getTableName());
        velocityContext.put("functionName", StringUtil.isNotEmpty(functionName) ? functionName : "【请填写功能名称】");
        velocityContext.put("ClassName", genTable.getClassName());
        velocityContext.put("className", StringUtil.uncapitalize(genTable.getClassName()));
        velocityContext.put("moduleName", genTable.getModuleName());
        velocityContext.put("businessName", genTable.getBusinessName());
        velocityContext.put("basePackage", getPackagePrefix(packageName));
        velocityContext.put("packageName", packageName);
        velocityContext.put("author", genTable.getFunctionAuthor());
        velocityContext.put("datetime", DateUtil.today());
        velocityContext.put("pkColumn", genTable.getPkColumn());
        velocityContext.put("importList", getImportList(genTable));
        velocityContext.put("permissionPrefix", getPermissionPrefix(moduleName, businessName));
        velocityContext.put("columns", genTable.getColumns());
        velocityContext.put("table", genTable);
        velocityContext.put("ids", ids);
        setMenuVelocityContext(velocityContext, genTable);
        if (GenerateConstant.TPL_TREE.equals(tplCategory))
        {
            setTreeVelocityContext(velocityContext, genTable);
        }
        return velocityContext;
    }

    public static void setMenuVelocityContext(VelocityContext context, GenTable genTable)
    {
        String parentMenuId = genTable.getParentMenuId();
        context.put("parentMenuId", parentMenuId);
    }

    public static void setTreeVelocityContext(VelocityContext context, GenTable genTable)
    {
        context.put("treeCode", genTable.getTreeCode());
        context.put("treeParentCode", genTable.getTreeParentCode());
    }

    /**
     * 获取模板信息
     *
     * @return 模板列表
     */
    public static List<String> getTemplateList(String tplCategory)
    {
        List<String> templates = new ArrayList<String>();
        templates.add("vm/java/domain.java.vm");
        templates.add("vm/java/mapper.java.vm");
        templates.add("vm/java/service.java.vm");
        templates.add("vm/java/serviceImpl.java.vm");
        templates.add("vm/java/controller.java.vm");
        templates.add("vm/xml/mapper.xml.vm");
        if (GenerateConstant.TPL_CRUD.equals(tplCategory))
        {
            templates.add("vm/html/list.html.vm");
        }
        else if (GenerateConstant.TPL_TREE.equals(tplCategory))
        {
            templates.add("vm/html/tree.html.vm");
        }
        templates.add("vm/html/add.html.vm");
        templates.add("vm/html/edit.html.vm");
        templates.add("vm/sql/sql.vm");
        return templates;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, GenTable genTable)
    {
        String fileName = "";
        String packageName = genTable.getPackageName();
        String moduleName = genTable.getModuleName();
        String className = genTable.getClassName();
        String businessName = genTable.getBusinessName();

        String javaPath = PROJECT_PATH + "/" + StringUtil.replace(packageName, ".", "/");
        String htmlPath = TEMPLATES_PATH + "/" + moduleName + "/" + businessName;

        if (template.contains("domain.java.vm"))
        {
            fileName = StringUtil.format("{}/domain/{}.java", javaPath, className);
        }
        if (template.contains("sub-domain.java.vm") && StringUtil.equals(GenerateConstant.TPL_SUB, genTable.getTplCategory()))
        {
            fileName = StringUtil.format("{}/domain/{}.java", javaPath, genTable.getSubTable().getClassName());
        }
        else if (template.contains("mapper.java.vm"))
        {
            fileName = StringUtil.format("{}/mapper/{}Mapper.java", javaPath, className);
        }
        else if (template.contains("service.java.vm"))
        {
            fileName = StringUtil.format("{}/service/I{}Service.java", javaPath, className);
        }
        else if (template.contains("serviceImpl.java.vm"))
        {
            fileName = StringUtil.format("{}/service/impl/{}ServiceImpl.java", javaPath, className);
        }
        else if (template.contains("controller.java.vm"))
        {
            fileName = StringUtil.format("{}/controller/{}Controller.java", javaPath, className);
        }
        else if (template.contains("mapper.xml.vm"))
        {
            fileName = StringUtil.format("{}/mapper/xml/{}Mapper.xml", javaPath, className);
        }
        else if (template.contains("list.html.vm"))
        {
            fileName = StringUtil.format("{}/main.html", htmlPath, businessName);
        }
        else if (template.contains("tree.html.vm"))
        {
            fileName = StringUtil.format("{}/main.html", htmlPath);
        }
        else if (template.contains("add.html.vm"))
        {
            fileName = StringUtil.format("{}/add.html", htmlPath);
        }
        else if (template.contains("edit.html.vm"))
        {
            fileName = StringUtil.format("{}/edit.html", htmlPath);
        }
        else if (template.contains("sql.vm"))
        {
            fileName = businessName + "Menu.sql";
        }
        return fileName;
    }

    /**
     * 获取项目文件路径
     *
     * @return 路径
     */
    public static String getProjectPath()
    {
        String packageName = GenConfig.getPackageName();
        return "main/java/" +
                packageName.replace(".", "/") +
                "/";
    }

    /**
     * 获取包前缀
     *
     * @param packageName 包名称
     * @return 包前缀名称
     */
    public static String getPackagePrefix(String packageName)
    {
        int lastIndex = packageName.lastIndexOf(".");
        return StringUtil.substring(packageName, 0, lastIndex);
    }

    /**
     * 根据列类型获取导入包
     *
     * @param genTable 业务表对象
     * @return 返回需要导入的包列表
     */
    public static HashSet<String> getImportList(GenTable genTable)
    {
        List<GenTableColumn> columns = genTable.getColumns();
        GenTable subGenTable = genTable.getSubTable();
        HashSet<String> importList = new HashSet<String>();
        if (StringUtil.isNotNull(subGenTable))
        {
            importList.add("java.util.List");
        }
        for (GenTableColumn column : columns)
        {
            if (!column.isSuperColumn() && GenerateConstant.TYPE_DATE.equals(column.getJavaType()))
            {
                importList.add("java.util.Date");
            }
            else if (!column.isSuperColumn() && GenerateConstant.TYPE_BIGDECIMAL.equals(column.getJavaType()))
            {
                importList.add("java.math.BigDecimal");
            }
        }
        return importList;
    }

    /**
     * 获取权限前缀
     *
     * @param moduleName 模块名称
     * @param businessName 业务名称
     * @return 返回权限前缀
     */
    public static String getPermissionPrefix(String moduleName, String businessName)
    {
        return StringUtil.format("{}:{}", moduleName, businessName);
    }
}
