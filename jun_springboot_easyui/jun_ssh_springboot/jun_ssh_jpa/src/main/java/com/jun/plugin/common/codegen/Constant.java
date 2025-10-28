package com.jun.plugin.common.codegen;
/**
 * 使用说明:<br>
 * 不要直接在工程目录下使用代码生成!<br>
 * 请使用临时目录用于代码生成，然后拷贝到工程中。<br>
 * 
 * @author klguang
 *
 */ 
public class Constant {
	//放在本工程src下
	static final String TEMPLATE_DIR ="/codegen-template";
	//工程中的实体目录
	static final String ENTITY_DIR="D:/workspace/eclipse/tianti/fieldmeta-core/src/main/java/org/coderfun/workspace/entity";	
	//模块所在包
	static final String BASE_PACKAGE="org.coderfun.workspace";
	
	//----------------代码生成的路径---------------
	static final String GEN_DIR="C:/Users/kevin/Desktop/codegen/coderfun-workspace";
	
	static final String DAO_DIR=GEN_DIR+"/dao";
	static final String DAO_IMPL_DIR=GEN_DIR+"/dao";
	static final String SERVICE_DIR=GEN_DIR+"/service";
	static final String SERVICE_IMPL_DIR=GEN_DIR+"/service";
	static final String CONTROLLER_DIR=GEN_DIR+"/controller/admin";
	
	//---------------代码中import package------------
	static final String BASE_DAO_PACKAGE="klg.common.dataaccess.BaseRepository";
	static final String BASE_SERVICE_PACKAGE="klg.common.dataaccess.BaseService";
	static final String BASE_SERVICE_IMPL_PACKAGE="klg.common.dataaccess.BaseServiceImpl";
}
