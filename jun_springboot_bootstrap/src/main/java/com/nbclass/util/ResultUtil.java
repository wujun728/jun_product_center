package com.nbclass.util;

import com.nbclass.vo.base.PageResultVo;
import com.nbclass.vo.base.ResponseVo;

import java.util.List;
/**
 * @version V1.0
 * @date 2018年7月11日
 * @author Wujun
 */
public class ResultUtil{

	public static ResponseVo success(){
		return vo(CoreConst.SUCCESS_CODE,null,null);
	}

	public static ResponseVo success(String msg){
		return vo(CoreConst.SUCCESS_CODE, msg,null);
	}

	public static ResponseVo success(String msg, Object data){
		return vo(CoreConst.SUCCESS_CODE, msg, data);
	}

	public static ResponseVo error(){
		return vo(CoreConst.FAIL_CODE,null,null);
	}

	public static ResponseVo error(String msg){
		return vo(CoreConst.FAIL_CODE, msg,null);
	}

	public static ResponseVo error(String msg, Object data){
		return vo(CoreConst.FAIL_CODE, msg,data);
	}

	public static PageResultVo table( List<?> list, Long total){
		return new PageResultVo(list, total);
	}

	public static ResponseVo vo(Integer status, String message, Object data) {
		return new ResponseVo<>(status, message, data);
	}



}
