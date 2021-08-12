package com.sanri.tools.modules.core.service.classloader;

import com.sanri.tools.modules.core.dtos.ClassStruct;
import com.sanri.tools.modules.core.dtos.PluginDto;
import com.sanri.tools.modules.core.service.file.FileManager;
import com.sanri.tools.modules.core.service.plugin.PluginManager;
import com.sanri.tools.modules.core.utils.MybatisXNode;
import com.sanri.tools.modules.core.utils.MybatisXPathParser;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClassloaderService  {
    // classloaderName ==> ClassLoader
    private Map<String,ExtendClassloader> CACHED_CLASSLOADER = new HashMap<>();

    @Autowired
    private CompileService compileService;
    @Autowired
    private FileManager fileManager;
    @Autowired
    private PluginManager pluginManager;
    @Autowired
    private Environment environment;

    /**
     * 加载指定目录的类,使用指定名称的类加载器
     * @param baseDir
     * @param classloaderName
     * @return
     * @throws MalformedURLException
     */
    public ExtendClassloader loadClasses(File baseDir, String classloaderName) throws MalformedURLException {
        ExtendClassloader extendClassloader = new ExtendClassloader(classloaderName, baseDir.toURI().toURL());
        // 由于自定义的一般比较少的类,所以在初始化的时候就加载所有的类
        URI parent = baseDir.toURI();
        Collection<File> files = FileUtils.listFiles(baseDir, new String[]{"class"}, true);
        for (File file : files) {
            URI path = file.toURI();
            try {
                String packagePath = parent.relativize(path).toString();
                String classPath = packagePath.replaceAll("/", ".");
                String className = FilenameUtils.getBaseName(classPath);
                extendClassloader.loadClass(className);
            }  catch (ClassNotFoundException e) {
                log.error("loadClasses error : {}",e.getMessage(),e);
            }
        }
        CACHED_CLASSLOADER.put(classloaderName,extendClassloader);
        return extendClassloader;
    }

    /**
     * 加载单个 class 文件
     * @param targetClassFile
     * @param title
     */
    public void loadSingleClass(File targetClassFile,String classloaderName) throws MalformedURLException {
        // 使用 asm 工具读取文件包路径
        FileInputStream fileInputStream = null;
        File classFileNoSuffix = null;
        try {
            fileInputStream = new FileInputStream(targetClassFile);
            ClassReader reader = new ClassReader(fileInputStream);
            ClassNode classNode = new ClassNode();//创建ClassNode,读取的信息会封装到这个类里面
            reader.accept(classNode, 0);//开始读取

            // 创建包路径
            classFileNoSuffix = new File(targetClassFile.getParentFile(), classNode.name);
            classFileNoSuffix.getParentFile().mkdirs();
        } catch (IOException e) {
            log.error("读取字节码失败[{}]",e);
        }finally {
            // 关流
            IOUtils.closeQuietly(fileInputStream);
        }

        try {
            // 移动类文件
            FileUtils.copyFile(targetClassFile,new File(classFileNoSuffix.getParentFile(),targetClassFile.getName()));
            // 删除源文件
            FileUtils.deleteQuietly(targetClassFile);
        } catch (IOException e) {
            log.error("这个应该不会失败[{}]",e);
        }

        // 使用正规结构加载类
        loadClasses(targetClassFile.getParentFile(),classloaderName);
    }

    /**
     * 加载平级结构的 class  包
     * @param baseDir
     * @param classloaderName
     */
    public void loadParallalClassesFile(File baseDir,String classloaderName) throws IOException {
        Collection<File> files = FileUtils.listFiles(baseDir, new String[]{"class"}, true);
        for (File file : files) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ClassReader reader = new ClassReader(fileInputStream);
            ClassNode classNode = new ClassNode();//创建ClassNode,读取的信息会封装到这个类里面
            reader.accept(classNode, 0);//开始读取
            fileInputStream.close();

            // 创建包路径
            File classFileNoSuffix = new File(baseDir, classNode.name+".class");
            classFileNoSuffix.getParentFile().mkdirs();

            // 移动文件
            FileUtils.copyFile(file,classFileNoSuffix);
            // 删除原来文件
            FileUtils.deleteQuietly(file);
        }

        // 使用正规结构加载类
        loadClasses(baseDir,classloaderName);
    }

    /**
     * 使用单个  java 文件加载类
     * @param targetJavaFile
     */
    public void loadSingleJavaFile(File targetJavaFile,String classloaderName) throws IOException {
        String content = FileUtils.readFileToString(targetJavaFile);
        FileUtils.deleteQuietly(targetJavaFile);

        SimpleJavaBeanBuilder simpleJavaBeanBuilder = compileService.javaBeanAdapter(content);
        log.info("编译并加载 bean : [{}]",simpleJavaBeanBuilder.getClassName());
        compileService.compile(simpleJavaBeanBuilder,targetJavaFile.getParentFile());
        String baseName = FilenameUtils.getBaseName(targetJavaFile.getName());

        loadClasses(targetJavaFile.getParentFile(),classloaderName);
    }

    public Set<String> classloaders(){
        return CACHED_CLASSLOADER.keySet();
    }
    public void removeClassloader(String name){
        CACHED_CLASSLOADER.remove(name);
    }
    public ClassLoader getClassloader(String classloaderName) {
        ClassLoader extendClassloader = CACHED_CLASSLOADER.get(classloaderName);
        if (extendClassloader == null){
//            log.warn("不支持的类加载器: {}, 将使用系统类加载器",classloaderName);
            extendClassloader = ClassLoader.getSystemClassLoader();
        }
        return extendClassloader;
    }

    /**
     * 查看加载的类
     * @param name
     * @return
     */
    public Set<String> listLoadedClasses(String classloaderName){
        ClassLoader classLoader = getClassloader(classloaderName);
        Field classes = FieldUtils.getField(ClassLoader.class, "classes", true);
        Vector<Class<?>> classVector = (Vector<Class<?>>)  ReflectionUtils.getField(classes, classLoader);
        Set<String> collect = classVector.stream().map(Class::getName).collect(Collectors.toSet());
        return collect;
    }

    public Field[] classFields(String classloaderName, String className) throws ClassNotFoundException {
        ClassLoader classLoader = getClassloader(classloaderName);
        Class<?> clazz = classLoader.loadClass(className);
        Field[] declaredFields = clazz.getDeclaredFields();
        return declaredFields;
    }

    public Method [] classMethods(String classloaderName, String className) throws ClassNotFoundException {
        ClassLoader classLoader = getClassloader(classloaderName);
        Class<?> clazz = classLoader.loadClass(className);
        Method[] declaredMethods = clazz.getDeclaredMethods();
        return declaredMethods;
    }

    /**
     * 初始化加载以前加载过的类加载器
     */
    @PostConstruct
    public void init(){
        // 加载扩展包
        try {
//            loadExtendLibsFromRepository();
            loadExtendLibsFromLocal();
        } catch (Exception e) {
            log.error("扩展包加载失败:{}",e.getMessage(),e);
        }

        // 加载扩展类加载器
        try {
            File classloaderDir = fileManager.mkTmpDir("classloader");
            File[] files = classloaderDir.listFiles();
            for (File file : files) {
                String name = file.getName();
                try {
                    loadClasses(file,name);
                } catch (MalformedURLException e) {
                    log.error("load ext libs error : {}",e.getMessage(),e);
                }
            }
        }catch (Exception e){
            log.warn("之前的类加载器加载失败,考虑是否需要清理以前的类加载器");
        }

        // 注册基础服务
        pluginManager.register(PluginDto.builder().module("core").name("classloader").author("9420").desc("类加载器,支撑模块").build());
    }

    /**
     * 从本地环境加载扩展包
     */
    private void loadExtendLibsFromLocal() throws NoSuchMethodException, FileNotFoundException, InvocationTargetException, IllegalAccessException, MalformedURLException {
        String [] libPaths = environment.getProperty("data.path.libs",String [].class);
        if (ArrayUtils.isNotEmpty(libPaths)) {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

            for (String libPath : libPaths) {
                File dirOrFile = ResourceUtils.getFile(libPath);
                if (!dirOrFile.exists()){
                    log.warn("[{}] 目录不存在,如不需要加载外部包请忽略",dirOrFile);
                    continue;
                }
                if (dirOrFile.isFile() && dirOrFile.getName().endsWith("jar")) {
                    addURL.invoke(systemClassLoader, dirOrFile.toURI().toURL());
                }else{
                    Collection<File> files = FileUtils.listFiles(dirOrFile, new String[]{"jar"}, true);
                    for (File file : files) {
                        addURL.invoke(systemClassLoader, file.toURI().toURL());
                    }
                }
            }
        }
    }

    /**
     * 类加载器可能需要加载一些其它包才能正常加载类
     * 暂时先加载到默认类加载器中,后面想办法扩展到各自的类加载器中
     * 这个速度太慢了, 还是从本地加载吧
     * @throws IOException
     */
    private void loadExtendLibsFromRepository() throws IOException, URISyntaxException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        addURL.setAccessible(true);

        // 解析 pom ,加载需要的 jar 包到默认类加载器
        File libDir = fileManager.mkTmpDir("extend/libs");
        String repository = "https://mirrors.huaweicloud.com/repository/maven/";
        ClassPathResource classPathResource = new ClassPathResource("classloaderdefault.pom");
        MybatisXPathParser mybatisXPathParser = new MybatisXPathParser(classPathResource.getInputStream());
        MybatisXNode mybatisXNode = mybatisXPathParser.evalNode("/project/dependencies");
        List<MybatisXNode> children = mybatisXNode.getChildren();
        if (CollectionUtils.isNotEmpty(children)) {
            for (MybatisXNode child : children) {
                String groupId = child.evalString("groupId");
                String artifactId = child.evalString("artifactId");
                String version = child.evalString("version");

                groupId = StringUtils.replace(groupId,".","/");

                Path resolve = Paths.get(libDir.toURI()).resolve(Paths.get(groupId, artifactId, version));
                File targetDir = resolve.toFile();
                targetDir.mkdirs();

                String fileName = artifactId+"-"+version+".jar";
                URI jarURI = new URL(repository).toURI().resolve(new URI(groupId + "/" + artifactId + "/" + version+"/"+fileName));

                addURL.invoke(systemClassLoader,jarURI.toURL());
            }
        }

    }


    /**
     * 加载出一个 Class
     * @param classloaderName
     * @param className
     * @return
     * @throws ClassNotFoundException
     */
    public Class loadClass(String classloaderName, String className) throws ClassNotFoundException {
        ClassLoader classloader = getClassloader(classloaderName);
        Class<?> aClass = classloader.loadClass(className);
        return aClass;
    }

    /**
     * 查看类加载器加载的类列表 - 加强版本
     * @param classLoaderName
     */
    public List<ClassStruct> classLoaderLoadedClasses(String classLoaderName){
        ExtendClassloader extendClassloader = CACHED_CLASSLOADER.get(classLoaderName);
        Vector<Class<?>> loadClasses = extendClassloader.getLoadClasses();
        Iterator<Class<?>> iterator = loadClasses.iterator();
        List<ClassStruct> classStructs = new ArrayList<>();

        while (iterator.hasNext()){
            Class<?> clazz = iterator.next();
            ClassStruct classStruct = classStruct(clazz);
            classStructs.add(classStruct);
        }

        return classStructs;
    }

    /**
     * 获取类结构
     * @param clazz
     * @return
     */
    public ClassStruct classStruct(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        String packageName = clazz.getPackage().getName();
        ClassStruct classStruct = new ClassStruct(simpleName, packageName);

        Field[] declaredFields = clazz.getDeclaredFields();
        List<ClassStruct.Field> fields = new ArrayList<>();
        classStruct.setFields(fields);
        for (Field declaredField : declaredFields) {
            String fieldName = declaredField.getName();
            String fieldType = declaredField.getType().getSimpleName();
            int modifiers = declaredField.getModifiers();
            ClassStruct.Field field = new ClassStruct.Field(modifiers, fieldName, fieldType);
            fields.add(field);
        }

        Method[] declaredMethods = clazz.getDeclaredMethods();
        List<ClassStruct.Method> methods = new ArrayList<>();
        classStruct.setMethods(methods);
        for (Method declaredMethod : declaredMethods) {
            ClassStruct.Method method = buildMethodDesc(declaredMethod);
            methods.add(method);
        }
        return classStruct;
    }

    ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    /**
     * 构建自定义的方法描述
     * @param declaredMethod
     * @return
     */
    public ClassStruct.Method buildMethodDesc(Method declaredMethod) {
        String methodName = declaredMethod.getName();
        int modifiers = declaredMethod.getModifiers();
        String returnType = declaredMethod.getReturnType().getSimpleName();
        ClassStruct.Method method = new ClassStruct.Method(modifiers, methodName, returnType);

        // 获取方法参数列表
        Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(declaredMethod);
        if (ArrayUtils.isNotEmpty(parameterTypes)) {
            List<ClassStruct.Arg> args = new ArrayList<>();
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> parameterType = parameterTypes[i];
                String argName = "arg"+i;
                if (parameterNames != null){
                    argName = parameterNames[i];
                }
                ClassStruct.Arg arg = new ClassStruct.Arg(parameterType.getSimpleName(),argName );
                args.add(arg);
            }
            method.setArgs(args);
        }
        return method;
    }
}
