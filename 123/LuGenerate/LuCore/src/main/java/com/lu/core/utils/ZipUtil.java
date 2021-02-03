package com.lu.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @program LuBoot
 * @description:
 * @author: zhanglu
 * @create: 2019-11-12 21:21:00
 */
public class ZipUtil {

    private static final int BUFFER_SIZE = 2 * 1024;
    /**
     * 是否保留原来的目录结构
     * true:  保留目录结构;
     * false: 所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     */
    private static final boolean KeepDirStructure = true;
    private static final Logger log = LoggerFactory.getLogger(ZipUtil.class);

    public static void main(String[] args) {
        try {
            toZip("/Users/zhanglu/Desktop/lu/workspace/dev_workspace/idea/框架/LuBoot/code/682olf", "/Users/zhanglu/Desktop/lu/workspace/dev_workspace/idea/框架/LuBoot/code/1.zip",true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩成ZIP
     * @param srcDir         压缩 文件/文件夹 路径
     * @param outPathFile    压缩 文件/文件夹 输出路径+文件名 D:/xx.zip
     * @param isDelSrcFile   是否删除原文件: 压缩前文件
     */
    public static void toZip(String srcDir, String outPathFile,boolean isDelSrcFile) throws Exception {
        long start = System.currentTimeMillis();
        FileOutputStream out = null;
        ZipOutputStream zos = null;
        try {
            out = new FileOutputStream(new File(outPathFile));
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            if(!sourceFile.exists()){
                throw new Exception("需压缩文件或者文件夹不存在");
            }
            compress(sourceFile, zos, sourceFile.getName());
            if(isDelSrcFile){
                delDir(srcDir);
            }
            log.info("原文件:{}. 压缩到:{}完成. 是否删除原文件:{}. 耗时:{}ms. ",srcDir,outPathFile,isDelSrcFile,System.currentTimeMillis()-start);
        } catch (Exception e) {
            log.error("zip error from ZipUtils: {}. ",e.getMessage());
            throw new Exception("zip error from ZipUtils");
        } finally {
            try {
                if (zos != null) {zos.close();}
                if (out != null) {out.close();}
            } catch (Exception e) {}
        }
    }

    /**
     * 递归压缩方法
     * @param sourceFile 源文件
     * @param zos zip输出流
     * @param name 压缩后的名称
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name)
            throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            zos.putNextEntry(new ZipEntry(name));
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                if (KeepDirStructure) {
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    zos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    if (KeepDirStructure) {
                        compress(file, zos, name + "/" + file.getName());
                    } else {
                        compress(file, zos, file.getName());
                    }
                }
            }
        }
    }

    /**
     * 解压文件到指定目录
     */
    @SuppressWarnings({ "rawtypes", "resource" })
    public static void unZipFiles(String zipPath, String descDir) throws IOException {
        log.info("文件:{}. 解压路径:{}. 解压开始.",zipPath,descDir);
        long start = System.currentTimeMillis();
        try{
            File zipFile = new File(zipPath);
            System.err.println(zipFile.getName());
            if(!zipFile.exists()){
                throw new IOException("需解压文件不存在.");
            }
            File pathFile = new File(descDir);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
            for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                System.err.println(zipEntryName);
                InputStream in = zip.getInputStream(entry);
                String outPath = (descDir + File.separator + zipEntryName).replaceAll("\\*", "/");
                System.err.println(outPath);
                // 判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                // 输出文件路径信息
                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
            }
            log.info("文件:{}. 解压路径:{}. 解压完成. 耗时:{}ms. ",zipPath,descDir,System.currentTimeMillis()-start);
        }catch(Exception e){
            log.info("文件:{}. 解压路径:{}. 解压异常:{}. 耗时:{}ms. ",zipPath,descDir,e,System.currentTimeMillis()-start);
            throw new IOException(e);
        }
    }

    // 删除文件或文件夹以及文件夹下所有文件
    public static void delDir(String dirPath) throws IOException {
        log.info("删除文件开始:{}.",dirPath);
        long start = System.currentTimeMillis();
        try{
            File dirFile = new File(dirPath);
            if (!dirFile.exists()) {
                return;
            }
            if (dirFile.isFile()) {
                dirFile.delete();
                return;
            }
            File[] files = dirFile.listFiles();
            if(files==null){
                return;
            }
            for (int i = 0; i < files.length; i++) {
                delDir(files[i].toString());
            }
            dirFile.delete();
            log.info("删除文件:{}. 耗时:{}ms. ",dirPath,System.currentTimeMillis()-start);
        }catch(Exception e){
            log.info("删除文件:{}. 异常:{}. 耗时:{}ms. ",dirPath,e,System.currentTimeMillis()-start);
            throw new IOException("删除文件异常.");
        }
    }

}


