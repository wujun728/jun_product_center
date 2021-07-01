package com.du.lin.constant.state;

/**
 * 通知类型
 */
public enum NoticeType {
	System("系统通知"),
	Conmon("普通通知")
	;
	
	private String name;
	
	
	private NoticeType(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
