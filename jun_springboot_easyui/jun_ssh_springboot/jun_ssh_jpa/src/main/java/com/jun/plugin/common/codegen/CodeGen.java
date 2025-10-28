package com.jun.plugin.common.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 生成一个entity相应的dao、service、controller
 * 
 * @author klguang
 *
 */

public class CodeGen {
	String entityName;
	CodeModel codeModel;

	public CodeGen(String entityName) {
		super();
		this.entityName = entityName;
		codeModel=new CodeModel(entityName);
	}
	
	public void gen(){
		createDAO();
		createDAOImpl();
		createService();
		createServiceImpl();
		createController();
	}
	
	public void createDAO(){
		String fileName=entityName+"DAO.java";
		writeToFile("dao.ftl", codeModel, Constant.DAO_DIR, fileName);
	}
	public void createDAOImpl(){
		String fileName=entityName+"DAOImpl.java";
		writeToFile("daoimpl.ftl", codeModel, Constant.DAO_IMPL_DIR, fileName);
	}
	public void createService(){
		String fileName=entityName+"Service.java";
		writeToFile("service.ftl", codeModel, Constant.SERVICE_DIR, fileName);
	}
	public void createServiceImpl(){
		String fileName=entityName+"ServiceImpl.java";
		writeToFile("serviceimpl.ftl", codeModel, Constant.SERVICE_IMPL_DIR, fileName);
	}
	
	public void createController(){
		String fileName=entityName+"Controller.java";
		writeToFile("controller.ftl", codeModel, Constant.CONTROLLER_DIR, fileName);
	}
    private static void writeToFile(String name,CodeModel codeModel,String dir,String fileName){
    	
    	Template template=FreemarkUtil.getTemplate(name);
    	File dirFile=new File(dir);
    	if(!dirFile.exists()){
    		dirFile.mkdirs();
    	}
    	
    	FileWriter out = null;
    	try {
        	out = new FileWriter(dir+"/"+fileName);
			template.process(codeModel, out);
			
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out != null )
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
    }
}
