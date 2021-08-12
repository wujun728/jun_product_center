package com.shuogesha.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.alibaba.fastjson.JSON;

/**
 * 红包生成工具类
 * 
 * @author zhaohaiyuan
 *
 */
public class HB {

	/*
	 * 普通红包
	 * 
	 * @param totalMoney 总金额,单位是"分"。总金额为方便计算,已经转换为整数,单位为分。*
	 * 
	 * @param count 红包个数*@return ArrayList<Integer>
	 * 元素为各个红包的金额值,所有元素的值累和等于总金额.**请将totalMoney,分成count分,保存到ArrayList<Integer>中,
	 * 返回即可.
	 */

	public static ArrayList<Integer> avg(int totalMoney, int count) {
		// 创建保存各个红包金额的集合
		ArrayList<Integer> list = new ArrayList<>();
		// 计算平均金额
		int avgMoney = totalMoney / count;
		// 设置前面count‐1个人都是平均的金额
		for (int i = 0; i < count - 1; i++) {
			list.add(avgMoney);
			// 从总金额中减去已分配的金额,就是最后一个人剩余的金额
			totalMoney -= avgMoney;
		}
		// 将剩余的金额设置给最后一个人
		list.add(totalMoney);
		return list;
	}

	/**
	 * 手气红包
	 * 
	 * @param totalMoney
	 * @param count
	 * @return
	 */
	public static ArrayList<Integer> lucky(int totalMoney, int count) {
		// 创建保存各个红包金额的集合
		ArrayList<Integer> list = new ArrayList<>();
		// 定义循环次数,总个数‐1次
		int time = count - 1;
		// 创建随机数对象
		Random random = new Random();
		// 循环分配
		for (int i = 0; i < time; i++) {
			/*
			 * 每次重新计算,生成随机金额 随机范围: totalMoney / count * 2,totalMoney不断的减少,
			 * count也不断的减少,所以这是一个可变化的范围.
			 */
			int money = random.nextInt(totalMoney / count * 2) + 1;
			// 金额添加到集合
			list.add(money);
			// 总金额扣除已分配金额
			totalMoney -= money;
			// 红包个数‐1
			count--;
		}
		// 剩余的金额,为最后一个红包
		list.add(totalMoney);
		return list;
	}

	public static ArrayList<Integer> lucky(int totalMoney, int count, int max) {
		if (count < 1 || totalMoney < count) {
			return null;// 人数比钱的数量多直接返回错误
		}
		// 创建保存各个红包金额的集合
		ArrayList<Integer> list = new ArrayList<>();
		// 定义循环次数,总个数‐1次
		int time = count - 1;
		// 创建随机数对象
		Random random = new Random();
		// 循环分配
		for (int i = 0; i < time; i++) {
			/*
			 * 每次重新计算,生成随机金额 随机范围: totalMoney / count * 2,totalMoney不断的减少,
			 * count也不断的减少,所以这是一个可变化的范围.
			 */
			int money = random.nextInt(totalMoney / count * 2) + 1;
			// 金额添加到集合
			list.add(money);
			// 总金额扣除已分配金额
			totalMoney -= money;
			// 红包个数‐1
			count--;
		}
		// 剩余的金额,为最后一个红包
		list.add(totalMoney);
		return list;
	}
	
	public static ArrayList<BigDecimal> lucky(BigDecimal totalMoney, int count) {
		if (count < 1 || totalMoney.doubleValue() < count* 0.01) {
			return null;// 人数比钱的数量多直接返回错误
		}
		
		int allMoney = totalMoney.multiply(BigDecimal.valueOf(100)).intValue();

		// 创建保存各个红包金额的集合
		ArrayList<BigDecimal> list = new ArrayList<>();
		// 定义循环次数,总个数‐1次
		int time = count - 1;
		// 创建随机数对象
		Random random = new Random();
		// 循环分配
		for (int i = 0; i < time; i++) {
			/*
			 * 每次重新计算,生成随机金额 随机范围: totalMoney / count * 2,totalMoney不断的减少,
			 * count也不断的减少,所以这是一个可变化的范围.
			 */
			int money = random.nextInt(allMoney / count * 2) + 1;
			// 金额添加到集合
			list.add(new BigDecimal(money).divide(new BigDecimal(100)));
			// 总金额扣除已分配金额
			allMoney -= money;
			// 红包个数‐1
			count--;
		}
		// 剩余的金额,为最后一个红包
		list.add(new BigDecimal(allMoney).divide(new BigDecimal(100)));
		return list;
	}

//	public static List<BigDecimal> math(BigDecimal mmm, int number) {
//		if (mmm.doubleValue() < number * 0.01) {
//			return null;
//		}
//		Random random = new Random();
//		// 金钱，按分计算 10块等于 1000分
//		int money = mmm.multiply(BigDecimal.valueOf(100)).intValue();
//		// 随机数总额
//		double count = 0;
//		// 每人获得随机点数
//		double[] arrRandom = new double[number];
//		// 每人获得钱数
//		List<BigDecimal> arrMoney = new ArrayList<BigDecimal>(number);
//		// 循环人数 随机点
//		for (int i = 0; i < arrRandom.length; i++) {
//			int r = random.nextInt((number) * 99) + 1;
//			count += r;
//			arrRandom[i] = r;
//		}
//		// 计算每人拆红包获得金额
//		int c = 0;
//		for (int i = 0; i < arrRandom.length; i++) {
//			// 每人获得随机数相加 计算每人占百分比
//			Double x = new Double(arrRandom[i] / count);
//			// 每人通过百分比获得金额
//			int m = (int) Math.floor(x * money);
//			// 如果获得 0 金额，则设置最小值 1分钱
//			if (m == 0) {
//				m = 1;
//			}
//			// 计算获得总额
//			c += m;
//			// 如果不是最后一个人则正常计算
//			if (i < arrRandom.length - 1) {
//				arrMoney.add(new BigDecimal(m).divide(new BigDecimal(100)));
//			} else {
//				// 如果是最后一个人，则把剩余的钱数给最后一个人
//				arrMoney.add(new BigDecimal(money - c + m).divide(new BigDecimal(100)));
//			}
//		}
//		// 随机打乱每人获得金额
//		Collections.shuffle(arrMoney);
//		return arrMoney; 
//
//	}

	public static void main(String[] args) {
		// 普通红包
		System.out.println(JSON.toJSONString(avg(123, 2)));
		System.out.println(JSON.toJSONString(avg(12323, 2)));
		System.out.println(JSON.toJSONString(lucky(12323, 2)));
		System.out.println(JSON.toJSONString(lucky(12323, 3)));
		long s= System.currentTimeMillis();
		System.out.println(JSON.toJSONString(lucky(1232300, 200)));
		System.out.println(System.currentTimeMillis()-s);
		System.out.println(JSON.toJSONString(lucky(123, 2)));
//		long b= System.currentTimeMillis();
//		System.out.println(JSON.toJSONString(math(new BigDecimal(1232300), 200)));
//		System.out.println(System.currentTimeMillis()-b);
		long c= System.currentTimeMillis();
		System.out.println(JSON.toJSONString(lucky(new BigDecimal(1232300), 200)));
		System.out.println(System.currentTimeMillis()-c);
	}
}
