package com.sanri.tools.modules.core.service.classloader;

import com.sanri.tools.modules.core.utils.RegexValidate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.tools.JavaCompiler;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 编译工具,对于给 Java 类的,提取包名类名和字段名信息
 * 然后重新生成 Java 类,进行编译,对于有复杂类型是无法处理的
 * 最后使用类加载器进行加载
 */

@Component
@Slf4j
public class CompileService {
//    static ParserFactory factory = null;
//    static {
//        Context context = new Context();
//        JavacFileManager.preRegister(context);
//        factory = ParserFactory.instance(context);
//    }

    /**
     * 编译 java 类,使用字符串传 java 类过来
     * @param javaCodes
     * @param className
     * @return classfile 位置
     */
    public void compile(String className, String javaCodes, File targetDir) throws  IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null,null,null);
        StringObject stringObject = new StringObject(className, javaCodes);
        List<String> options = Arrays.asList("-d", targetDir.getAbsolutePath());
        JavaCompiler.CompilationTask compilerTask = compiler.getTask(null, fileManager, null, options, null, Arrays.asList(stringObject));
        Boolean call = compilerTask.call();
        fileManager.close();
    }

    static class StringObject extends SimpleJavaFileObject {
        private String contents = null;

        public StringObject(String className, String contents) {
            super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.contents = contents;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors)throws IOException {
            return contents;
        }
    }

    /**
     * 字段扫描器
     */
//    static class FieldScanner extends TreeScanner<List<SimpleJavaBeanBuilder.Property>,List<SimpleJavaBeanBuilder.Property>> {
//        @Override
//        public List<SimpleJavaBeanBuilder.Property> visitVariable(VariableTree node, List<SimpleJavaBeanBuilder.Property> fields) {
//            fields.add(new SimpleJavaBeanBuilder.Property(node.toString()));
//            return fields;
//        }
//    }


//    public SimpleJavaBeanBuilder javaBeanAdapter(String sourceFile){
//        JavacParser javacParser = factory.newParser(sourceFile, false, false, true);
//        JCTree.JCCompilationUnit jcCompilationUnit = javacParser.parseCompilationUnit();
//        JCTree.JCExpression pid = jcCompilationUnit.pid;
//        com.sun.tools.javac.util.Name name = ((JCTree.JCClassDecl) jcCompilationUnit.defs.get(0)).name;
//        System.out.println(pid+"."+name);
//        FieldScanner fieldScanner = new FieldScanner();
//        List<SimpleJavaBeanBuilder.Property> fields = new ArrayList<>();
//        fieldScanner.visitCompilationUnit(jcCompilationUnit, fields);
//
//        SimpleJavaBeanBuilder simpleJavaBeanBuilder = new SimpleJavaBeanBuilder(pid.toString(), name.toString(), fields);
//        return simpleJavaBeanBuilder;
//    }

    /**
     * 从 java 源文件得到一个简单 DTO 的一个适配
     * @param sourceFile
     * @return
     */
    Pattern packageNamePattern =  Pattern.compile("package (.*);");
    Pattern classNamePattern = Pattern.compile("public\\s+class\\s+(.*)\\{");
    Pattern fieldPattern = Pattern.compile("(private\\s\\w+\\s\\w+)");
    public SimpleJavaBeanBuilder javaBeanAdapter(String content){
        String packageName = RegexValidate.match(content, this.packageNamePattern).get(0);
        String className = RegexValidate.match(content, this.classNamePattern).get(0);
        List<String> match = RegexValidate.match(content, fieldPattern);

        List<SimpleJavaBeanBuilder.Property> properties = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(match)){
            for (String fieldDesc : match) {
                SimpleJavaBeanBuilder.Property property = new SimpleJavaBeanBuilder.Property(fieldDesc);
                properties.add(property);
            }
        }

        return new SimpleJavaBeanBuilder(packageName.trim(),className.trim(),properties);
    }

    public void compile(SimpleJavaBeanBuilder simpleJavaBeanBuilder,File targetDir) throws IOException {
        List<String> build = simpleJavaBeanBuilder.build();
        String javaCodes = StringUtils.join(build, '\n');
        compile(simpleJavaBeanBuilder.getPackageName()+"."+simpleJavaBeanBuilder.getClassName(),javaCodes,targetDir);
    }
}
