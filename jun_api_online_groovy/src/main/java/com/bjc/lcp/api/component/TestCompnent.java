package com.bjc.lcp.api.component;

import cn.hutool.core.lang.Console;
import io.github.wujun728.common.base.interfaces.IExecutor;

import java.util.Map;

public class TestCompnent implements IExecutor<Integer, Map<String,Object>> {

	@Override
	public Integer execute(Map<String, Object> params) {
		// TODO Auto-generated method stub
		Console.print(" run TestCompnent execute method 111   ");
		return 0;
	}



}
