package com.shuogesha.cms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.alibaba.fastjson.JSONObject;
import com.shuogesha.platform.entity.UnifiedUser;

public class Order implements Serializable {
	public static final String PAY_TUIKUANED="-1";
	public static final String PAY_WEIZHIFU="0";
	public static final String PAY_YIZHIFU="1";
	public static final String PAY_TUIKUANGING="2";
	
	public static final String ORDER_CANCEL="-1";
	public static final String ORDER_JIAOYI="0";
	public static final String ORDER_OK="1";
	
	public static final String PAYTYPE_YUE="yue";
	public static final String PAYTYPE_ALIPAY="alipay";
	public static final String PAYTYPE_WECHAT="wechat";
	public static final String PAYTYPE_WX="wx";
	
	public static final String PRODUCT="product"; 
	public static final String SERVICE="service";
		private Long id;
	private String name=Order.PRODUCT;  
	private String orderNo;
 	private String content;
	private BigDecimal total;//总额
	private BigDecimal price;//支付金额
	private String remark;
   	private String type;//0商城订单
	private String dateline;//下单时间
	private String phone;
	private String address;
	private String shippingStatus="0";//发货状态 0,待发货,1，备货中，2已经发货，3已经收货
	private String comment="0";//评论状态 0,待评价，1，已经评价
	private String reason;//原因
	private Integer num;//个数
	private String status=Order.ORDER_JIAOYI;
	private String pay=Order.PAY_WEIZHIFU;
	private String payType;
	private String platform;//来源平台 
	public UnifiedUser unifiedUser;//下单的人 
	private List<Shipping> shippings;//发货信息
	
	private Date payTime;//支付时间
	private Date shippingTime;//确认收货
	private Date commentTime;//评论时间
	
	@Transient
	private JSONObject productJSON;//订单商品快照信息
	
	@Transient
	private List<OrderNote> orderNotes;//订单操作日志

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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDateline() {
		return dateline;
	}

	public void setDateline(String dateline) {
		this.dateline = dateline;
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

	public String getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public UnifiedUser getUnifiedUser() {
		return unifiedUser;
	}

	public void setUnifiedUser(UnifiedUser unifiedUser) {
		this.unifiedUser = unifiedUser;
	}

	public List<Shipping> getShippings() {
		return shippings;
	}

	public void setShippings(List<Shipping> shippings) {
		this.shippings = shippings;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getShippingTime() {
		return shippingTime;
	}

	public void setShippingTime(Date shippingTime) {
		this.shippingTime = shippingTime;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public JSONObject getProductJSON() {
		return productJSON;
	}

	public void setProductJSON(JSONObject productJSON) {
		this.productJSON = productJSON;
	}

	public List<OrderNote> getOrderNotes() {
		return orderNotes;
	}

	public void setOrderNotes(List<OrderNote> orderNotes) {
		this.orderNotes = orderNotes;
	}

	
	
	
}
