package com.jun.plugin.gen.service;

public class GenCodeFile {
	private String name;
	/**
	 * 以"/"开头,以"/"结束
	 */
	private String dir;
	private String content;
	private String canMerge;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCanMerge() {
		return canMerge;
	}

	public void setCanMerge(String canMerge) {
		this.canMerge = canMerge;
	}

}
