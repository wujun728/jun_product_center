package io.github.wujun728.admin;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.commons.util.StringUtils;

import java.io.*;
import java.util.List;

@Slf4j
public class ExportCode {
    public static void main(String[] args) throws Exception {
        File file = new File("src");
        File sourceFile = new File(file.getParent(),"jqp.txt");
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(sourceFile),"UTF-8"));
        log.info("file exists:{}",file.exists());

        print(file,printWriter);
        printWriter.flush();
        printWriter.close();
    }

    public static void print(File file,PrintWriter printWriter){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(files != null){
                for (File child : files) {
                    print(child,printWriter);
                }
            }
        }else if(file.getName().endsWith(".java")){
            List<String> lines = FileUtil.readUtf8Lines(file);
            for(String line:lines){
                String trim = line.trim();
                if(StringUtils.isBlank(trim)
                    || trim.contains("//")
                    || trim.contains("*")
                    || trim.startsWith("import")
                    || trim.startsWith("package")
                ){
                    continue;
                }
                printWriter.println(line);
            }
        }
    }
}
