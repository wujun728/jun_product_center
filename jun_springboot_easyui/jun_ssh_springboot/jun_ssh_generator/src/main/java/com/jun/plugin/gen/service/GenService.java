package com.jun.plugin.gen.service;

import java.io.IOException;
import java.util.List;

public interface GenService {


	/**
	 * 实体名
	 */
	
	static final String ENP = "[ENP]";
	/**
	 * 实体名 ，首字母小写
	 */
	static final String LFENP = "[LENP]";
	/**
	 * 模块包路径
	 */
	static final String MOD_PKG_PATH = "[MOD_PKG_PATH]";
	/**
	 * 模块名
	 */
	static final String MOD_NAME = "[MOD_NAME]";
	
	static final String[] JAVA_LANG_TYPES = {"Long","String","Integer"}; 
	
	
	/**
	 * 生成代码
	 * @param tablemetaId
	 */
	public List<GenCodeFile> genCodeFiles(Long tablemetaId);
	
	/**
	 * 
	 * 生成代码，并打包zip
	 * 
	 * @param tablemetaIds
	 * @return zip 数据
	 * @throws IOException 
	 */
	public byte[] genCodeByZip(List<Long> tablemetaIds) throws IOException ;
}
