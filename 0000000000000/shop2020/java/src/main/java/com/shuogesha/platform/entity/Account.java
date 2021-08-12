package com.shuogesha.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;

 
public class Account implements Serializable {
	
 	private Long id;
	private String name;
	private BigDecimal account=BigDecimal.ZERO;//总金额
	private BigDecimal cashAmount=BigDecimal.ZERO;//可提现(支付宝)
	private BigDecimal uncashAmount=BigDecimal.ZERO;//不可提现（网银）
	private BigDecimal freezeCashAmount=BigDecimal.ZERO;//可提现冻结金额
	private BigDecimal freezeUncashAmount=BigDecimal.ZERO;//不可提现冻结金额
	
	private int score;

	private String md5;//安全控制码

	private String safeAnswer;//安全问题	
	private String safeQuestion;//安全回答
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getAccount() {
		return account;
	}
	public void setAccount(BigDecimal account) {
		this.account = account;
	}
	public BigDecimal getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}
	public BigDecimal getUncashAmount() {
		return uncashAmount;
	}
	public void setUncashAmount(BigDecimal uncashAmount) {
		this.uncashAmount = uncashAmount;
	}
	public BigDecimal getFreezeCashAmount() {
		return freezeCashAmount;
	}
	public void setFreezeCashAmount(BigDecimal freezeCashAmount) {
		this.freezeCashAmount = freezeCashAmount;
	}
	public BigDecimal getFreezeUncashAmount() {
		return freezeUncashAmount;
	}
	public void setFreezeUncashAmount(BigDecimal freezeUncashAmount) {
		this.freezeUncashAmount = freezeUncashAmount;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getSafeAnswer() {
		return safeAnswer;
	}
	public void setSafeAnswer(String safeAnswer) {
		this.safeAnswer = safeAnswer;
	}
	public String getSafeQuestion() {
		return safeQuestion;
	}
	public void setSafeQuestion(String safeQuestion) {
		this.safeQuestion = safeQuestion;
	}
	 
	
	
}
