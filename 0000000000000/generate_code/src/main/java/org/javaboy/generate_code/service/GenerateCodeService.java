package org.javaboy.generate_code.service;

import com.google.common.base.CaseFormat;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.javaboy.generate_code.model.ColumnClass;
import org.javaboy.generate_code.model.RespBean;
import org.javaboy.generate_code.model.TableClass;
import org.javaboy.generate_code.utils.DBUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Service
public class GenerateCodeService {

    Configuration cfg = null;

    {
        cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setTemplateLoader(new ClassTemplateLoader(GenerateCodeService.class, "/templates"));
        cfg.setDefaultEncoding("UTF-8");
    }

    public RespBean generateCode(List<TableClass> tableClassList, String realPath) {
        try {
            Template modelTemplate = cfg.getTemplate("Model.java.ftl");
            Template mapperJavaTemplate = cfg.getTemplate("Mapper.java.ftl");
            Template mapperXmlTemplate = cfg.getTemplate("Mapper.xml.ftl");
            Template serviceTemplate = cfg.getTemplate("Service.java.ftl");
            Template controllerTemplate = cfg.getTemplate("Controller.java.ftl");
            Connection connection = DBUtils.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            for (TableClass tableClass : tableClassList) {
                ResultSet columns = metaData.getColumns(connection.getCatalog(), null, tableClass.getTableName(), null);
                ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), null, tableClass.getTableName());
                List<ColumnClass> columnClassList = new ArrayList<>();
                while (columns.next()) {
                    String column_name = columns.getString("COLUMN_NAME");
                    String type_name = columns.getString("TYPE_NAME");
                    String remarks = columns.getString("REMARKS");
                    ColumnClass columnClass = new ColumnClass();
                    columnClass.setRemark(remarks);
                    columnClass.setColumnName(column_name);
                    columnClass.setType(type_name);
                    columnClass.setPropertyName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, column_name));
                    primaryKeys.first();
                    while (primaryKeys.next()) {
                        String pkName = primaryKeys.getString("COLUMN_NAME");
                        if (column_name.equals(pkName)) {
                            columnClass.setPrimary(true);
                        }
                    }
                    columnClassList.add(columnClass);
                }
                tableClass.setColumns(columnClassList);
                String path = realPath + "/" + tableClass.getPackageName().replace(".", "/");
                generate(modelTemplate, tableClass, path + "/model/");
                generate(mapperJavaTemplate, tableClass, path + "/mapper/");
                generate(mapperXmlTemplate, tableClass, path + "/mapper/");
                generate(serviceTemplate, tableClass, path + "/service/");
                generate(controllerTemplate, tableClass, path + "/controller/");
            }
            return RespBean.ok("代码已生成", realPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("代码生成失败");
    }

    private void generate(Template template, TableClass tableClass, String path) throws IOException, TemplateException {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName = path + "/" + tableClass.getModelName() + template.getName().replace(".ftl", "").replace("Model", "");
        FileOutputStream fos = new FileOutputStream(fileName);
        OutputStreamWriter out = new OutputStreamWriter(fos);
        template.process(tableClass,out);
        fos.close();
        out.close();
    }
}
