package com.wangzhixuan.test.excel;

import java.util.Date;

import com.wangzhixuan.commons.report.excel.ExcelCellType;
import com.wangzhixuan.commons.report.excel.ExcelField;

public class MyTest {

	@ExcelField("名称")
	private String name;

	@ExcelField("联系电话")
	private String phone;

	@ExcelField("地址")
	private String address;

	@ExcelField(value = "一级分类ID", type = ExcelCellType.NUMBER)
	private int type;

	@ExcelField(value = "经度", type = ExcelCellType.NUMBER)
	private double lat;

	@ExcelField(value = "时间", type = ExcelCellType.DATE)
	private Date dateTime;
	
	@ExcelField(value = "单价", type = ExcelCellType.NUMBER)
	private double price;
	
	@ExcelField(value = "数量", type = ExcelCellType.NUMBER)
	private Integer size;
	
	@ExcelField(value = "是否xx", type = ExcelCellType.BOOL, format="是:否")
	private boolean xx;

	@ExcelField(value = "总价", type = ExcelCellType.NUMBER, el = "#{price * size}")
	private double totalPrice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public boolean isXx() {
		return xx;
	}

	public void setXx(boolean xx) {
		this.xx = xx;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}