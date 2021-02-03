package com.lu.core.tips;

/**
 * 返回给前台的成功提示
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:05:22
 */
public class SuccessTip extends Tip {

	private Object data;
	private Long count;

	public SuccessTip(){
		super.code = 0;
		super.message = "操作成功";
	}

	public SuccessTip(Object data){
		super.code = 0;
		super.message = "操作成功";
		this.data=data;
	}

	public SuccessTip(Object data,Long count){
		super.code = 0;
		super.message = "操作成功";
		this.data=data;
		this.count=count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}
