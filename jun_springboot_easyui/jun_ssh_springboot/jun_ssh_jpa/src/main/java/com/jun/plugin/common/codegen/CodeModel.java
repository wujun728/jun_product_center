package com.jun.plugin.common.codegen;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

/**
 * template数据模型
 * 
 * @author klguang
 *
 */

public class CodeModel {

	String basePackage = Constant.BASE_PACKAGE;

	String baseDAOPackage = Constant.BASE_DAO_PACKAGE;
	String baseServicePackage = Constant.BASE_SERVICE_PACKAGE;
	String baseServiceImplPackage = Constant.BASE_SERVICE_IMPL_PACKAGE;

	String entityName;
	String entityName_first_lowcase;
	String entityName_all_lowcase;
	String nowTime;
	
	public CodeModel(String entityName) {
		this.entityName = entityName;
		this.entityName_first_lowcase = MyStingUtil.lowerCaseFirstOne(entityName);
		this.entityName_all_lowcase = entityName.toLowerCase();
		
		nowTime = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(new Date());
	}

	public String getNowTime() {
		return nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getBaseDAOPackage() {
		return baseDAOPackage;
	}

	public void setBaseDAOPackage(String baseDAOPackage) {
		this.baseDAOPackage = baseDAOPackage;
	}

	public String getBaseServicePackage() {
		return baseServicePackage;
	}

	public void setBaseServicePackage(String baseServicePackage) {
		this.baseServicePackage = baseServicePackage;
	}

	public String getBaseServiceImplPackage() {
		return baseServiceImplPackage;
	}

	public void setBaseServiceImplPackage(String baseServiceImplPackage) {
		this.baseServiceImplPackage = baseServiceImplPackage;
	}

	public String getEntityName_first_lowcase() {
		return entityName_first_lowcase;
	}

	public void setEntityName_first_lowcase(String entityName_first_lowcase) {
		this.entityName_first_lowcase = entityName_first_lowcase;
	}

	public String getEntityName_all_lowcase() {
		return entityName_all_lowcase;
	}

	public void setEntityName_all_lowcase(String entityName_all_lowcase) {
		this.entityName_all_lowcase = entityName_all_lowcase;
	}
	
	public static void main(String[] args) {
		System.out.println(DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(new Date()));
		
	}
	
}
