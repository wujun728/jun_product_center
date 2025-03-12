package com.jun.plugin.qixing.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.jun.plugin.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jun.plugin.qixing.mapper.BizCommonMapper;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PrimaryKeyService {

    @Resource
    private RedisService redisService;

	@Autowired
	private PrimaryKeyService primaryKeyService;
	

    @Autowired
    private BizCommonMapper bizCommonMapper;

	/**
	 * 
	 * 获取年的后两位加上一年多少天+当前小时数作为前缀
	 * 
	 * @param date
	 * @return
	 * 
	 */

	public static String getOrderIdPrefix(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		//补两位,因为一年最多三位数
		String monthFormat = String.format("%1$02d", month + 1);
		//补两位，因为日最多两位数
		String dayFormat = String.format("%1$02d", day);
		//补两位，因为小时最多两位数
		String hourFormat = String.format("%1$02d", hour);
		return year + monthFormat + dayFormat + hourFormat;
	}
	
	public static String getReportNumberCode() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
		StringTemplateLoader stm = new StringTemplateLoader();
		 cfg.setTemplateLoader(stm);
		 cfg.setDefaultEncoding("UTF-8");
		 StringWriter sw = new StringWriter();
		 
//		1.审计：年报审计 -鄂齐会师审字〔2021 〕第000号，专项审计-鄂齐会师专审字〔2021〕第000号，咨询类--鄂齐会师咨字〔2021 〕第000号，司法鉴定-鄂齐司会鉴字〔2021 〕第000号 
//		2.验资：鄂齐会师验字〔2021 〕第000号
//		3.绩效评价  鄂齐会师绩评字〔2021〕 第000号
//		4.税审：鄂齐会师税审字〔2021 〕第000号   鄂齐会师税咨字〔2021 〕第000号
		 String teamplateContent = "";
//		 审计：年报审计
		 teamplateContent = "鄂齐会师审字〔${year} 〕第${number}号";
//		 专项审计
		 teamplateContent = "鄂齐会师专审字〔${year}〕第${number}号";
//		 咨询类
		 teamplateContent = "鄂齐会师咨字〔${year} 〕第${number}号";
//		 司法鉴定
		 teamplateContent = "鄂齐司会鉴字〔${year} 〕第${number}号";
//		 验资
		 teamplateContent = "鄂齐会师验字〔${year} 〕第${number}号"; // ?????????????
//		 绩效评价
		 teamplateContent = "鄂齐会师绩评字〔${year}〕 第${number}号";
//		 税审
		 teamplateContent = "鄂齐会师税审字〔${year} 〕第${number}号";
		 teamplateContent = "鄂齐会师税咨字〔${year} 〕第${number}号";
		 
		 teamplateContent = "鄂齐会师税咨字〔${year} 〕第${number}号";
		 
//		齐业评估所：
//		1. 评估：一般评估-齐业评报字〔2021 〕第100*号  拆迁评估-齐业评拆字〔2021 〕第100*号，估值-齐业估值字〔2021 〕第100*号  -齐业评咨字〔2021 〕第100*号
//		2.绩效评价：  齐业绩评字〔2021〕 第000号
//		 评估：一般评估
		 teamplateContent = "齐业评报字〔${year} 〕第${number}*号";
//		 拆迁评估-
		 teamplateContent = "齐业评拆字〔${year} 〕第${number}*号";  // ????
//		 估值
		 teamplateContent = "齐业估值字〔${year} 〕第${number}*号";
//		 评估咨询
		 teamplateContent = "齐业评咨字〔${year} 〕第${number}*号";
		 
//		齐兴达税所
//		税审：齐兴达税师鉴字〔2021 〕第000号   齐兴达税师咨字〔2021 〕第000号
//		 税审
		 teamplateContent = "齐兴达税师鉴字〔${year} 〕第${number}号";
//		 咨询
		 teamplateContent = "齐兴达税师咨字〔${year} 〕第${number}号";
		 
		 Map<String, Object> data = new HashMap<>();
	        data.put("year", String.valueOf(year));
	        Long increment =Long.valueOf(1);
			String sortNo =String.format("%1$03d", increment);
	        data.put("number", sortNo);
	        
	     
		 try {
			Template tp = new Template("", teamplateContent, cfg);
			 tp.process(data, sw);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	//获取模板的数据，默认格式
	public static Map<String, Object> getReportNumberData(int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
//		teamplateContent = "鄂齐会师审字〔${year} 〕第${number}号";
		Map<String, Object> data = new HashMap<>();
		data.put("year", String.valueOf(year));
		Long increment =Long.valueOf(num);
		String sortNo =String.format("%1$03d", increment);
		data.put("number", sortNo);
		return data;
	}

	//获取模板的数据，格式1
	public static Map<String, Object> getReportNumberDataC(/* String year, */String num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
//		teamplateContent = "鄂齐会师审字〔${year} 〕第${number}号";
		Map<String, Object> data = new HashMap<>();
		data.put("year", String.valueOf(year));
//		Long increment =Long.valueOf(num);
//		String sortNo =String.format("%1$03d", increment);
		data.put("number", num);
		return data;
	}
	//获取模板的数据，格式2
	public static Map<String, Object> getReportNumberDataC2(String codeStr) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
//		teamplateContent = "鄂齐会师审字〔${year} 〕第${number}号";
		Map<String, Object> data = new HashMap<>();
		data.put("year", String.valueOf(year));
		String num = codeStr.substring(codeStr.length()-4, codeStr.length()-1);
		Long increment =Long.valueOf(num)+1L;
		String sortNo =String.format("%1$03d", increment);
		data.put("number", sortNo);
		return data;
	}
	public static String genFtlContentByFtlData(String teamplateContent,Map<String, Object> data) {
		Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
		StringTemplateLoader stm = new StringTemplateLoader();
		cfg.setTemplateLoader(stm);
		cfg.setDefaultEncoding("UTF-8");
		StringWriter sw = new StringWriter();
		try {
			Template tp = new Template("", teamplateContent, cfg);
			tp.process(data, sw);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	public static String getReportNumberCodeV2() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		Long increment =Long.valueOf(1);
		String sortNo =String.format("%1$03d", increment);
		// 往前补位,0的个数
		String preStr = "鄂齐会师审字";
		String yearStr = "〔"+year+" 〕";
		String sortStr = "第"+sortNo+"号";
		return preStr + yearStr + sortStr;
	}
	
	public static void main(String[] args) {
//		System.out.println(PrimaryKeyService.getOrderIdPrefix(new Date()));
		System.out.println(PrimaryKeyService.getReportNumberCode());
		System.out.println(PrimaryKeyService.getReportNumberCodeV2());
	}
	

	/**
	 * 生成订单
	 * @return
	 */

	public String orderCurrId(String key) {
//		String key = "ORDER_ID_" + prefix;
		String orderId = null;
		try {
			String val = redisService.getRedisTemplate().opsForValue().get(key);
			if(StringUtils.isEmpty(val)) {
				val = "";
				val += redisService.getRedisTemplate().opsForValue().increment(key , 1);
				redisService.getRedisTemplate().expire(key, 60*60*12, TimeUnit.SECONDS);
			}
			Long increment =Long.valueOf(val);
			// 往前补位,0的个数
			orderId = key + String.format("%1$03d", increment);
		} catch (Exception e) {
			System.out.println("生成订单号失败");
			e.printStackTrace();
		}
		return orderId;
	}
	public String orderNewId(String key) {
//		String key = "ORDER_ID_" + prefix;
		String orderId = null;
		try {
			Long increment =redisService.getRedisTemplate().opsForValue().increment(key , 1);
			redisService.getRedisTemplate().expire(key, 60*60*12, TimeUnit.SECONDS);
			// 往前补6位
			orderId = key + String.format("%1$03d", increment);
		} catch (Exception e) {
			System.out.println("生成订单号失败");
			e.printStackTrace();
		}
		return orderId;
	}
	
	public String genCurrID(String prefixCode) {
		long startMillis = System.currentTimeMillis();
		String orderIdPrefix = primaryKeyService.getOrderIdPrefix(new Date());
		String key = prefixCode + orderIdPrefix;
		String aLong = primaryKeyService.orderCurrId(key);
		System.out.println(aLong);
		long endMillis = System.currentTimeMillis();
		System.out.println("生成速度:" + (endMillis - startMillis) + ",单位毫秒");
		return aLong;
	}
	
	public String genNewID(String prefixCode) {
		long startMillis = System.currentTimeMillis();
		String orderIdPrefix = primaryKeyService.getOrderIdPrefix(new Date());
		String key = prefixCode + orderIdPrefix;
		String aLong = primaryKeyService.orderNewId(key);
		System.out.println(aLong);
		long endMillis = System.currentTimeMillis();
		System.out.println("生成速度:" + (endMillis - startMillis) + ",单位毫秒");
		return aLong;
	}
	
	public String genCodeAndCheckExists(String prefixCode) {
		 String curID = this.genCurrID(prefixCode);
		 String tableName = "";
		 String columnName = "";
		 if("CUS".equalsIgnoreCase(prefixCode)) {
			 tableName = "pj_customer";
			 columnName = "customer_code";
		 }else if("PRJ".equalsIgnoreCase(prefixCode)) {
			 tableName = "pj_project";
			 columnName = "project_code";
		 }else if("COST".equalsIgnoreCase(prefixCode)) {
			 tableName = "oa_poms_workmarks_claim_expense";
			 columnName = "cost_code";
		 }else if("CON".equalsIgnoreCase(prefixCode)) {
			 tableName = "pj_contract";
			 columnName = "contract_no";
		 }else {
			 return this.genNewID(prefixCode);
		 }
		 int count= bizCommonMapper.checkRecordExists(tableName, columnName, curID);
		 if(count>0) {
			 curID = this.genNewID(prefixCode);
		 }
		 return curID;
	}

}
