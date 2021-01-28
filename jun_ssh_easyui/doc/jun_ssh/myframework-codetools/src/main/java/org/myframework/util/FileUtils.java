package org.myframework.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileUtils extends org.apache.commons.io.FileUtils{

    private static final String ENCODE_GBK = "GBK";
    private static final String ENCODE_UTF8 = "UTF-8";

    public static void convertGBK2UTF8(File file){
        convertFileEncode(file, ENCODE_GBK, ENCODE_UTF8);
    }

    public static void convertUTF82GBK(File file){
        convertFileEncode(file, ENCODE_UTF8, ENCODE_GBK);
    }

    public static void convertFileEncode(File file, String fromEncode, String toEncode){
        try {
            String str = readFileToString(file, fromEncode);
            writeStringToFile(file, str, toEncode);
        } catch (IOException e) {
            System.out.println("Convert failed. File not exsit?");
            e.printStackTrace();
        }
    }

    public static String readFile(String filePath, String encode){
        String fileContent = "";
        try {
            FileInputStream fis = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(fis, encode);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            for(String line = br.readLine(); line != null; line = br.readLine()){
                sb.append(line);
            }
            fileContent = sb.toString();
            br.close();
            isr.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not exist："+filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    public static String saveFile(String fileContent, String encode, String savePath){
        try {
            FileOutputStream fos = new FileOutputStream(savePath);
            OutputStreamWriter osw = new OutputStreamWriter(fos, encode);
            BufferedWriter bw = new BufferedWriter(osw);

            bw.write(fileContent);
            bw.close();
            osw.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not exist："+savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    public static synchronized void convertDirectory(File dir) throws IOException {
        if (!dir.exists() && !dir.isDirectory()) {
            throw new IOException("[" + dir + "] not exsit or not a Directory");
        }
        convert(dir);
    }

    public static void convert(File dir){
        if (dir.canRead() && dir.canWrite()) {
            if (dir.isDirectory()) {//Directory
                String[] files = dir.list();
                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        convert(new File(dir, files[i]));//递归
                    }
                }
            } else {//File
                convertGBK2UTF8(dir);
            }
        }
    }



    public static void main(String[] args) throws IOException {

    	String chinese = "中文iteye";// java内部编码
		String gbkChinese = new String(chinese.getBytes("GBK"), "ISO-8859-1");// 转换成gbk编码
		System.out.println(gbkChinese);// 乱码
		String unicodeChinese = new String(gbkChinese.getBytes("ISO-8859-1"),
				"GBK");// java内部编码
		System.out.println(unicodeChinese);// 中文
		String utf8Chinese = new String(unicodeChinese.getBytes("UTF-8"),
				"ISO-8859-1");// utf--8编码
		System.out.println(utf8Chinese);// 乱码
		unicodeChinese = new String(utf8Chinese.getBytes("ISO-8859-1"), "UTF-8");// java内部编码
		System.out.println(unicodeChinese);// 中文

        File f = new File("content.txt");
        convertGBK2UTF8(f);

        long t1 = System.currentTimeMillis();
        File src = new File("C:\\myLucene");
//        convertDirectory(src);
        long t2 = System.currentTimeMillis();
        System.out.println("转换完成，用时："+(t2-t1)/1000);
}

}
