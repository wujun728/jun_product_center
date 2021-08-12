package jdbctest;

import org.junit.Test;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.NullProgressCallback;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MybatisGenerator {

    @Test
    public void testMan() throws SQLException, InterruptedException {
        Context context = new Context(ModelType.HIERARCHICAL);
        context.setId("testTable");
        context.setTargetRuntime("MyBatis3");

        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL("jdbc:mysql://localhost:3306/test");
        jdbcConnectionConfiguration.setDriverClass("com.mysql.jdbc.Driver");
        jdbcConnectionConfiguration.setPassword("h123");
        jdbcConnectionConfiguration.setUserId("root");
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetPackage("com.sanri.app.mapper");
        sqlMapGeneratorConfiguration.setTargetProject("src/main/java");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetPackage("com.sanri.app.mapper.entity");
        javaModelGeneratorConfiguration.setTargetProject("src/main/java");
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetPackage("com.sanri.app.mapper");
        javaClientGeneratorConfiguration.setTargetProject("src/main/java");
        javaClientGeneratorConfiguration.setImplementationPackage("com.sanri.app.mapper.impl");
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setCatalog("test");
        tableConfiguration.setTableName("%");
        context.addTableConfiguration(tableConfiguration);

        List<GeneratedJavaFile> generatedJavaFiles = new ArrayList<>();

        List<GeneratedXmlFile> generatedXmlFiles = new ArrayList<>();

        ProgressCallback callback = new NullProgressCallback();
        List<String> warnings = new ArrayList<>();
        Set<String> fullyQualifiedTableNames = new HashSet<>();
        context.introspectTables(callback, warnings,fullyQualifiedTableNames);
        context.generateFiles(callback, generatedJavaFiles, generatedXmlFiles, warnings);

        System.out.println(generatedJavaFiles);
        System.out.println(generatedXmlFiles);
    }
}
