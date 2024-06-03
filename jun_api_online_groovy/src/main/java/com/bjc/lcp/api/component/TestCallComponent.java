package com.bjc.lcp.api.component;

import cn.hutool.core.lang.Console;
import com.jun.plugin.common.Result;
import com.jun.plugin.common.base.interfaces.IExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ServerProperties;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 组件TestCallComponent
 * 描 述：执行指定的SQL语句（insert/update/delete/select语句） 
 * 版本历史：1.0.0版 参数说明： 
 * 参 数1：要执行的SQL语句 
 * 参 数2：sql参数 
 * 参 数3：执行SQL语句处理记录数存放标签
 * 
 * 说明：需要把该代码放进DB，api_groovy，测试Jfinal-CURD-保存在庫裡面
 */
@Slf4j
public class TestCallComponent implements IExecutor<Result, Map<String,Object>> {
	
	@Resource
	ServerProperties config;

	 
	@Override
	public Result execute(Map<String, Object> params) throws Exception{
		//校验组件参数+校验上下文参数，TODO
		// TODO Auto-generated method stub
		Console.print(" run TestCallComponent execute method have bean call 111   ");
		if(config!=null) {
			Console.print("ServerProperties is not null execute method have bean call 111   ");
		}
		return Result.success();
	}

}
