package com.sanri.tools.modules.core.controller;

import com.sanri.tools.modules.core.dtos.ClassStruct;
import com.sanri.tools.modules.core.service.classloader.ClassloaderService;
import com.sanri.tools.modules.core.service.data.RandomDataService;
import com.sanri.tools.modules.core.service.file.FileManager;
import com.sanri.tools.modules.core.utils.ZipUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classloader")
@Validated
public class ClassloaderController {
    @Autowired
    private ClassloaderService classloaderService;
    @Autowired
    private FileManager fileManager;
    @Autowired
    private RandomDataService randomDataService;

    /**
     * 上传 zip 文件,创建类加载器,类文件需要严格的目录结构
     * @param file
     */
    @PostMapping("/uploadClassesZip")
    public void uploadClassesZip(MultipartFile file,@NotNull String classloaderName) throws IOException {
        File dir = unzipFile(file, classloaderName);
        classloaderService.loadClasses(dir,classloaderName);
    }

    /**
     * 上传 zip 文件,创建类加载器,类文件不需要严格的目录结构
     * @param file
     */
    @PostMapping("/uploadClassesZipSimple")
    public void uploadClassesZipSimple(MultipartFile file,@NotNull String classloaderName) throws IOException {
        File dir = unzipFile(file, classloaderName);
        classloaderService.loadParallalClassesFile(dir,classloaderName);
    }

    /**
     * 上传单个 class 文件到指定类加载器
     * @param file
     * @param classloaderName
     */
    @PostMapping("/uploadSingleClass")
    public void uploadSingleClass(MultipartFile file,@NotNull String classloaderName) throws IOException {
        File dir = fileManager.mkTmpDir("classloader/"+classloaderName);
        File classFile = new File(dir, file.getOriginalFilename());
        file.transferTo(classFile);
        classloaderService.loadSingleClass(classFile,classloaderName);
    }

    /**
     * 上传单个 java 文件,将自动编译成 class 加进类加载器
     * @param file
     * @param classloaderName
     */
    @PostMapping("/uploadSingleJavaFile")
    public void uploadSingleJavaFile(MultipartFile file,@NotNull String classloaderName) throws IOException {
        File dir = fileManager.mkTmpDir("classloader/"+classloaderName);
        File javaFile = new File(dir, file.getOriginalFilename());
        file.transferTo(javaFile);
        classloaderService.loadSingleJavaFile(javaFile,classloaderName);
    }

    /**
     * 类加载器列表
     * @return
     */
    @GetMapping("/classloaders")
    public Set<String> classloaders(){
        return classloaderService.classloaders();
    }

    /**
     * 列出当前类加载器加载的类
     * @param classloaderName
     * @return
     */
    @GetMapping("/listLoadedClasses")
    public Set<String> listLoadedClasses(@NotNull String classloaderName){
        return classloaderService.listLoadedClasses(classloaderName);
    }

    /**
     * 当前类加载器加载的类的加强版本
     * @param classloaderName
     * @return
     */
    @GetMapping("/classLoaderLoadedClasses")
    public List<ClassStruct> classLoaderLoadedClasses(@NotNull String classloaderName){
        return classloaderService.classLoaderLoadedClasses(classloaderName);
    }

    /**
     * 获取某个类的所有方法名
     * @param classloaderName
     * @param className
     * @return
     */
    @GetMapping("/{classloaderName}/{className}/methodNames")
    public List<String> methodNames(@PathVariable("classloaderName") String classloaderName, @PathVariable("className") String className) throws ClassNotFoundException {
        Class clazz = classloaderService.loadClass(classloaderName,className);
        Method[] declaredMethods = clazz.getDeclaredMethods();
        List<String> collect = Arrays.stream(declaredMethods).map(Method::getName).collect(Collectors.toList());
        return collect;
    }

    /**
     * 获取类结构
     * @param classloaderName
     * @param className
     * @return
     */
    @GetMapping("/{classloaderName}/{className}/classStruct")
    public ClassStruct classStruct(@PathVariable("classloaderName") String classloaderName, @PathVariable("className") String className) throws ClassNotFoundException {
        Class clazz = classloaderService.loadClass(classloaderName,className);
        ClassStruct classStruct = classloaderService.classStruct(clazz);
        return classStruct;
    }

    /**
     * 获取一个类所有的静态方法
     * @param className
     * @param classloaderName
     * @return
     * @throws ClassNotFoundException
     */
    @GetMapping("/{classloaderName}/{className}/staticMethods")
    public List<ClassStruct.Method> staticMethods(String className,String classloaderName) throws ClassNotFoundException {
        Method[] methods = classloaderService.classMethods(classloaderName, className);
        List<ClassStruct.Method> methodDescs = new ArrayList<>();
        for (Method method : methods) {
            int modifiers = method.getModifiers();
            boolean isStatic = Modifier.isStatic(modifiers);
            if (isStatic){
                ClassStruct.Method methodDesc = classloaderService.buildMethodDesc(method);
                methodDescs.add(methodDesc);
            }
        }
        return methodDescs;
    }


    /**
     * 构建方法参数,需要保证方法名是唯一的,如果有重载方法,将只取第一个重载方法的参数
     * @param classloaderName
     * @param className
     * @param methodName
     * @return
     */
    @GetMapping("/{classloaderName}/{className}/{methodName}/buildParams")
    public List<Object> buildParams(@PathVariable("classloaderName") String classloaderName, @PathVariable("className") String className,@PathVariable("methodName") String methodName) throws ClassNotFoundException {
        Class clazz = classloaderService.loadClass(classloaderName,className);
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Method method = null;
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().equals(methodName)){
                method = declaredMethod;
            }
        }
        List<Object> paramValues = new ArrayList<>();
        if (method != null){
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                ClassLoader classLoader = parameterType.getClassLoader();
                if (classLoader == null){
                    Object populateData = randomDataService.populateData(parameterType);
                    paramValues.add(populateData);
                }else {
                    Object randomData = randomDataService.randomData(parameterType.getName(), classLoader);
                    paramValues.add(randomData);
                }
            }
        }
        return paramValues;
    }

    /**
     * 解压文件 ,直接把压缩包里的东西拿出来到 classloader/classloaderName 路径
     * @param file
     * @param classloaderName
     * @return
     * @throws IOException
     */
    private File unzipFile(MultipartFile file, String classloaderName) throws IOException {
        File dir = fileManager.mkTmpDir("classloader");
        File zipFile = new File(dir, file.getOriginalFilename());
//        file.transferTo(zipFile);
        InputStream inputStream = file.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
        IOUtils.copy(inputStream,fileOutputStream);
        inputStream.close();
        fileOutputStream.close();

        File uncompressDir = new File(dir, classloaderName);uncompressDir.mkdir();
        ZipUtil.unzip(zipFile,uncompressDir.getAbsolutePath());
        FileUtils.deleteQuietly(zipFile);
        return uncompressDir;
    }

}
