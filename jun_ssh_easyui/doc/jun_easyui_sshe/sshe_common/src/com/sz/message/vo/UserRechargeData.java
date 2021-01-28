package com.sz.message.vo;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sz.intf.IEntityDataVO;

/**
 * 用户充值基本信息
 * 
 * 
 * <b>项目名称: CommonVO </b><br/>
 * <b>类描述: </b><br/>
 * <b>创 建 人: </b> zhouxj <br/>
 * <b>创建时间: </b> 2013-6-21 下午3:44:23 <br/>
 * <b>修 改 人: </b><br/>
 * <b>修改时间: </b><br/>
 * <b>修改备注: </b><br/>
 * <b>JDK 版本: </b> JDK1.6</br/>
 * 
 * @version 1.0.0<br/>
 */
public class UserRechargeData implements IEntityDataVO {

	/**
	 * UserId 编号
	 */
	private int userId;
	/**
	 * Gold 金币
	 */
	private int gold;
	/**
	 * Score 积分
	 * 
	 */
	private int score;
	/**
	 * DouDou 豆豆
	 * 
	 */
	private int doudou;
	/**
	 * Exp 经验值
	 * 
	 */
	private int exp;
	/**
	 * Hornor 荣誉值
	 * 
	 */
	private int hornor;
	
	
	
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getGold() {
		return gold;
	}


	public void setGold(int gold) {
		this.gold = gold;
	}


	public int getScore() {
		return score;
	}



	public void setScore(int score) {
		this.score = score;
	}


	public int getDoudou() {
		return doudou;
	}

	public void setDoudou(int doudou) {
		this.doudou = doudou;
	}


	public int getExp() {
		return exp;
	}


	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getHornor() {
		return hornor;
	}

	public void setHornor(int hornor) {
		this.hornor = hornor;
	}



	@JsonIgnore
	public String getName() {
		return "玩家充值数据";
	}

}
