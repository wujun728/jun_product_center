package org.myframework.support.csv.impl;

public class ImportResult {

	private int totalCnt ;

	private int successCnt ;
	
	private String filename ;

	public ImportResult() {

	}

	public ImportResult(int totalCnt, int successCnt,String filename) {
		super();
		this.totalCnt = totalCnt;
		this.successCnt = successCnt;
		this.filename = filename;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getSuccessCnt() {
		return successCnt;
	}

	public void setSuccessCnt(int successCnt) {
		this.successCnt = successCnt;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
