package com.sanri.tools.modules.core.service.classloader;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 简单实体构建,可用于类加载器
 */
public class SimpleJavaBeanBuilder {

    private String packageName;
    private String className;
    private List<Property> properties;

    public static final String PLACEHOLDER="\t";
    public static final String STATEMENT_END=";";
    public static final String N = "\n";
    public static final String COMMENTS_LINE = "//";
    public static final String COMMENTS_MULTI_LINE_BEGIN = "/**";
    public static final String COMMENTS_MULTI_LINE_END = " */";
    public static final String BODY_BEGIN = "{";
    public static final String BODY_END = "}";

    public SimpleJavaBeanBuilder(String packageName, String className, List<Property> properties) {
        this.packageName = packageName;
        this.className = className;
        this.properties = properties;
    }

    public static class Property{
        private String modifer;
        private String type;
        private String name;

        public Property(String desc) {
            String[] split = StringUtils.split(desc);
            if(split .length == 3){
                modifer = split[0].trim();
                type = split[1].trim();
                name = split[2].trim();
            }
        }

        public Property( String type, String name) {
            this.modifer = "private";
            this.type = type;
            this.name = name;
        }

        @Override
        public String toString() {
            return modifer+" "+type+" "+name+STATEMENT_END;
        }
    }

    public List<String> build(){
        List<String> classCode = new ArrayList<String>();
        Set<String> headList = new LinkedHashSet<String>();
        List<String> body = new ArrayList<String>();

        headList.add("package "+packageName+STATEMENT_END+N);

        buildImports(headList);

        body.add(N+"public class "+className+" implements Serializable"+BODY_BEGIN );

        List<String> fields = buildFields();
        List<String> methods =  buildMethods();

        body.addAll(fields);
        body.add(N);
        body.addAll(methods);
        body.add(BODY_END);
        classCode.addAll(headList);
        classCode.addAll(body);

        return classCode;
    }

    private List<String> buildMethods() {
        List<String> methods = new ArrayList<>();
        if(properties != null){
            for (Property property : properties) {
                //set method
                methods.add(PLACEHOLDER+"public void set"+ StringUtils.capitalize(property.name)+"("+property.type+" "+property.name+"){");
                methods.add(PLACEHOLDER+PLACEHOLDER+"this."+property.name+" = "+property.name+STATEMENT_END);
                methods.add(PLACEHOLDER+"}");
                //get method
                methods.add(PLACEHOLDER+"public "+property.type+" get"+ StringUtils.capitalize(property.name)+"(){");
                methods.add(PLACEHOLDER+PLACEHOLDER+"return this."+property.name+STATEMENT_END);
                methods.add(PLACEHOLDER+"}");
            }
        }
        return methods;
    }

    private List<String> buildFields() {
        List<String> fields = new ArrayList<>();
        if(properties != null){
            for (Property property : properties) {
                fields.add(PLACEHOLDER+property.toString());
            }
        }
        return fields;
    }

    private void buildImports(Set<String> headList) {
        headList.add("import java.io.Serializable;");
        headList.add("import java.math.BigDecimal;");
        headList.add("import java.util.List;");
        headList.add("import java.util.Date;");
        headList.add("import java.util.Set;");
        headList.add("import java.util.Map;");
        headList.add("import java.util.HashMap;");
        headList.add("import java.util.ArrayList;");
        headList.add("import java.util.HashSet;");
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }
}
