package com.jun.plugin.config;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jun.plugin.common.utils.JacksonUtil;
import com.jun.plugin.sys.dict.DictReader;

@Aspect
@Component
public class DictManagerAop {

	private static final Logger logger = LoggerFactory.getLogger(DictManagerAop.class);

	@Autowired
	WebRes webRes;

	@Autowired
	DictReader dictReader;
	


	// 会执行两次?
	@PostConstruct
	public void onStartup() throws IOException{
		dictReader.rebuild();
		buildDictWebFront();
	}
	
	
	/**
	 * 字典改动，重新load
	 * 
	 * @param joinPoint
	 */
	@After("execution(* org.coderfun.sys.dict.dao.*.*(..))")
	public void reloadDict(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		if (methodName.startsWith("save") || methodName.startsWith("update") || methodName.startsWith("delete")) {
			// 重新从数据库加载字典
			try {
				dictReader.rebuild();
				buildDictWebFront();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * 通过写JSON文件的方式构建前端dict reader
	 * 
	 * @throws IOException
	 */
	private void buildDictWebFront() throws IOException {
		Map<String, List> classfiedItems = DictReader.classifyAllItems();

		// 将原有的数据字典的js文件覆盖
		String dictJson = JacksonUtil.toJSONString(classfiedItems);
		dictJson = "var dictJson=" + dictJson;

		String dictFile = webRes.getAbsolutePath() + "/js/dictData.js";
		FileUtils.writeStringToFile(new File(dictFile), dictJson, "utf-8");
		logger.info("reload dictData.js:{}", dictFile);

	}



}
