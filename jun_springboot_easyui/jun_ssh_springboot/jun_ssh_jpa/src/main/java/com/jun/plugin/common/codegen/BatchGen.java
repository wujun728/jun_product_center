package com.jun.plugin.common.codegen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BatchGen {
	public BatchGen() {
	}
	
	public static List<String> getEntityNames(){
		List<String> entityNames=new ArrayList<String>();
		File file=new File(Constant.ENTITY_DIR);
		String[] fileNames=file.list();
		for(String fileName:fileNames){
			if(!fileName.endsWith("_.java")&&!fileName.toLowerCase().contains("base")){
				entityNames.add(fileName.substring(0,fileName.indexOf("java")-1));
			}
		}
		return entityNames;
	}
	
	public static void run(){
		List<String> entityNames=getEntityNames();
		gen(entityNames.toArray(new String[entityNames.size()]));
	}
	
	public static void gen(String... entityNames){
		for(String entityName:entityNames){
			System.out.println("codegen -> "+entityName);
			CodeGen codeGen=new CodeGen(entityName);
			codeGen.gen();
		}
	}
}
