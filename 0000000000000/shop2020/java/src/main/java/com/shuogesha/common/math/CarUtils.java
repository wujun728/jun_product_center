package com.shuogesha.common.math;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CarUtils {
	/**
	 * 进场自动记录时间
	 * 
	 * @param name
	 * @return
	 */
	private static Car in(String name) {
		Car bean = new Car(name, new Date());
		return bean;
	}

	/**
	 * 离场
	 * 
	 * @param bean
	 * @return
	 */
	private static Car out(Car bean, Date out_datetime) {
		bean.setOut_datetime(out_datetime);// 离场的时间 
		Long diff = out_datetime.getTime() - bean.getIn_datetime().getTime();// 这样得到的差值是毫秒级别
		Long days = diff / (1000 * 60 * 60 * 24);
		Long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		Long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
		// 记录停车时间
		bean.setIn_day(days.intValue());
		bean.setIn_hour(hours.intValue());
		bean.setIn_second(minutes.intValue());
		//计算价格
		bean.setPrice(countPrice(bean,0));
		return bean;
	}

	/**
	 * 计算价格6个小时内1.5 超过按照2月
	 * 
	 * @param type
	 * @param in_datetime
	 * @param out_datetime
	 * @return
	 */
	private static Double countPrice(Car bean, Integer type) {
		Double price=0.0;
		Double all=0.0;
		if(type==1) {//包月用户
			
		}else {//临时用户 
				//如果需要分时间段
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 				String startTime = format.format(bean.getIn_datetime()); 
 				String endTime = format.format(bean.getIn_datetime()); 
				Integer sDay = Integer.parseInt(startTime.substring(8,10));//入场日期（天）
				Integer sHour = Integer.parseInt(startTime.substring(11, 13));//入场小时
				Integer sMM = Integer.parseInt(startTime.substring(14,16));//入场分钟
				
				Integer eDay = Integer.parseInt(endTime.substring(8,10));//出场日期（天）
				Integer eHour = Integer.parseInt(endTime.substring(11, 13));//出场小时
				Integer eMM = Integer.parseInt(endTime.substring(14,16));//出场分钟
 				//15分钟免费，以后一个小时2元，超过一天按照24元一天 
				if(bean.getIn_day()>=1) {
					all=(double) (24*bean.getIn_day());
				}
				//查看离场时间是多久 
				int remin = bean.getIn_hour()*60+bean.getIn_min()-15;//停车多少分钟
				if(remin>0) {
					//前15分钟免费 以后每30分钟1元
					price = (double) ((remin/30)*1f); 
				}  
				all=all+price;
				
		}
		return all;
	}
	
	public static void main(String[] args) {
		Car bean = new Car("CA121122");
		//入场
		bean.setIn_datetime(new Date(2020, 02, 26,10,22,11));
		//离场
		bean=out(bean,new Date(2020, 02, 26,19,22,11));
		System.out.println(bean.getName()+"费用:"+bean.getPrice());
	}
}
