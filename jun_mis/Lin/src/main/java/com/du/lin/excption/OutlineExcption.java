package com.du.lin.excption;

public class OutlineExcption extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OutlineExcption(){
		super("较长时间未进行操作，需要重新输入密码");
	}

}
