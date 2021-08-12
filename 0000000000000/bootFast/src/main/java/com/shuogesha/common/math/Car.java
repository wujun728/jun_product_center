package com.shuogesha.common.math;

import java.util.Date;

public class Car {
	public String name;// 车牌号
	public Date in_datetime;// 进入时间
	public Date out_datetime;// 离开时间
	public int in_day=0;// 累计天
	public int in_hour=0;// 累计时
	public int in_min=0;// 累计分
	public int in_second=0;// 累计秒

	public Double price=0.0;// 停车费用
	
	public Car(String name) {
		super();
		this.name = name;
	}

	public Car(String name, Date in_datetime) {
		super();
		this.name = name;
		this.in_datetime = in_datetime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIn_datetime() {
		return in_datetime;
	}

	public void setIn_datetime(Date in_datetime) {
		this.in_datetime = in_datetime;
	}

	public Date getOut_datetime() {
		return out_datetime;
	}

	public void setOut_datetime(Date out_datetime) {
		this.out_datetime = out_datetime;
	}

	public int getIn_day() {
		return in_day;
	}

	public void setIn_day(int in_day) {
		this.in_day = in_day;
	}

	public int getIn_hour() {
		return in_hour;
	}

	public void setIn_hour(int in_hour) {
		this.in_hour = in_hour;
	}

	public int getIn_min() {
		return in_min;
	}

	public void setIn_min(int in_min) {
		this.in_min = in_min;
	}

	public int getIn_second() {
		return in_second;
	}

	public void setIn_second(int in_second) {
		this.in_second = in_second;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
