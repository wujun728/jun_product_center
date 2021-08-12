package com.shuogesha.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.shuogesha.common.util.UtilDate;
import com.shuogesha.platform.web.util.RequestUtils;

public class Paybill implements Serializable {
	 
	private static final long serialVersionUID = -3198086449897375811L;
	
	public static final String CHONGZHI="CHONGZHI";
	public static final String ORDER="ORDER";
	public static final String VIP="VIP";
	public static final String SERVICE="SERVICE";
	
	public static final String PAYTYPE_YUE="yue";
	public static final String PAYTYPE_ALIPAY="alipay";
	public static final String PAYTYPE_WECHAT="wechat";
	public static final String PAYTYPE_WX="wx";
	
	public static final Integer fail=2;

	private String id; 
	private String name;//CHONGZHI、ORDER
	private String orderNo;
	private String subject;
	private String body;
	private String payType;// //1.余额2.网银3.支付宝
 	private String createTime;
	private String payTime;
	private Integer state = 0;//-1退款 状态0.待支付1-成功2处理失败
	private Integer status = 0;//-1退款 状态0.待支付1-成功
	private BigDecimal tranAmount;// 交易金额

	private Long payeerId;
	private String payeerUsername;
	
	
	
	
	public Paybill() {
		super();
	}
	public Paybill(String name, String orderNo, String subject, String body, String payType, BigDecimal tranAmount,
			Long payeerId, String payeerUsername) {
		super();
		this.id = UtilDate.getOrderNum();
		this.name = name;
		this.orderNo = orderNo;
		this.subject = subject;
		this.body = body;
		this.payType = payType;
		this.tranAmount = tranAmount;
		this.payeerId = payeerId;
		this.payeerUsername = payeerUsername;
		this.createTime=RequestUtils.getNow();
 	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigDecimal getTranAmount() {
		return tranAmount;
	}
	public void setTranAmount(BigDecimal tranAmount) {
		this.tranAmount = tranAmount;
	}
	public Long getPayeerId() {
		return payeerId;
	}
	public void setPayeerId(Long payeerId) {
		this.payeerId = payeerId;
	}
	public String getPayeerUsername() {
		return payeerUsername;
	}
	public void setPayeerUsername(String payeerUsername) {
		this.payeerUsername = payeerUsername;
	} 

}
