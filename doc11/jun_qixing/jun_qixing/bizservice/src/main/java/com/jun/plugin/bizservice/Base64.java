package com.jun.plugin.bizservice;


import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Base64 implements Serializable {
    public static void main(String[] args) throws IOException {

        File file = new File("C:\\Users\\whbj_wujun\\Desktop\\base64.txt");
        String content = FileUtils.readFileToString(file,StandardCharsets.UTF_8);
        //String content = "" + "";
        String compressStr = Base64.compressBase64(content,StandardCharsets.UTF_8.toString());

        File base64= new File("C:\\Users\\whbj_wujun\\Desktop\\base64-v2.txt");
        FileUtils.writeByteArrayToFile(base64,compressStr.getBytes(StandardCharsets.UTF_8));
        System.out.println(compressStr);
//        System.out.println("11111111111111111111111111111111111111111111111111111L");
//        System.out.println(Base64.decodeGzipBase64(compressStr));
    }

    public static String compressBase64(String str, String charset) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(charset));
            if (gzip != null) {
                gzip.close();
            }
            String zipStr = cn.hutool.core.codec.Base64.encode(out.toByteArray());
            return zipStr;
        } catch (Exception e) {
            throw new IOException(e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static String decodeGzipBase64(String str){
        ByteArrayOutputStream out =new ByteArrayOutputStream();
        try {
            byte [] bytes=cn.hutool.core.codec.Base64.decode(str);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            GZIPInputStream gzip = new GZIPInputStream(byteArrayInputStream);
            byte [] b =new byte[128];
            int n;
            while ((n=gzip.read(b))>=0){
                out.write(b,0,n);
            }
        }catch (Exception e){
            return null;
        }
        return out.toString();
    }


}

