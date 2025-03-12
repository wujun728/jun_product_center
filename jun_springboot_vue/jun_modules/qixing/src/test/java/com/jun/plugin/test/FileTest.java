package com.jun.plugin.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private void testCommonIO() throws IOException {
    	//读取文件
        String s = FileUtils.readFileToString(new File("output.txt") , "GBK");
        System.out.println(s);
        System.out.println("-----------------");
        byte[] datas = FileUtils.readFileToByteArray(new File("output.txt"));
        System.out.println(datas.length);
        System.out.println("-----------------");
        //逐行读取
        List<String> list = FileUtils.readLines(new File("output.txt"),"GBK");
        for(String temp:list) {
            System.out.println(temp);
        }
        System.out.println("-----------------");
        //写文件  追加
        FileUtils.write(new File("output.txt"), "锄禾日当午\r\n","GBK");
        FileUtils.write(new File("output.txt"), "汗滴禾下土","GBK",true);

	}

}
