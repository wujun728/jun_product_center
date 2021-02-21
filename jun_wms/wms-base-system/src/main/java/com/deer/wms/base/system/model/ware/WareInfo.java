package com.deer.wms.base.system.model.ware;

import javax.persistence.*;

/**
 * 仓库设置表 ware_info
 * 
 * @author deer
 * @date 2019-05-08
 */

@Table(name = "ware_info")
public class WareInfo
{
	/** 仓库ID */
	@Id
	@Column(name = "ware_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer wareId;
	/** 仓库编码 */
	@Column(name = "ware_code")
	private String wareCode;
	/** 仓库名 */
	@Column(name = "ware_name")
	private String wareName;
	/** 添加人 */
	@Column(name = "create_user_id")
	private Integer createUserId;
	/** 添加 */
	@Column(name = "create_user_name")
	private String createUserName;
	@Column(name = "create_time")
	private String createTime;
	/**  */
	@Column(name = "memo")
	private String memo;

	@Column(name = "expected_waring")
	private Double expectedWaring;

	@Column(name = "alarm")
	private Double alarm;

	@Column(name = "stock_waring")
	private Integer stockWaring;

	/** 物料数量占整框比重*/
	@Column(name="box_param")
	private Double boxParam;

	/** 单箱可存储高度*/
	@Column(name="box_height")
	private Integer boxHeight;

	/** 不合格物料存储天数*/
	@Column(name="unqualified_storage_day")
	private Integer unqualifiedStorageDay;

	public Integer getUnqualifiedStorageDay() {
		return unqualifiedStorageDay;
	}

	public void setUnqualifiedStorageDay(Integer unqualifiedStorageDay) {
		this.unqualifiedStorageDay = unqualifiedStorageDay;
	}

	public Integer getBoxHeight() {
		return boxHeight;
	}

	public void setBoxHeight(Integer boxHeight) {
		this.boxHeight = boxHeight;
	}

	public Double getBoxParam() {
		return boxParam;
	}

	public void setBoxParam(Double boxParam) {
		this.boxParam = boxParam;
	}

	public Double getExpectedWaring() {
		return expectedWaring;
	}

	public void setExpectedWaring(Double expectedWaring) {
		this.expectedWaring = expectedWaring;
	}

	public Double getAlarm() {
		return alarm;
	}

	public void setAlarm(Double alarm) {
		this.alarm = alarm;
	}

	public Integer getStockWaring() {
		return stockWaring;
	}

	public void setStockWaring(Integer stockWaring) {
		this.stockWaring = stockWaring;
	}

	public void setWareId(Integer wareId)
	{
		this.wareId = wareId;
	}

	public Integer getWareId() 
	{
		return wareId;
	}
	public void setWareCode(String wareCode) 
	{
		this.wareCode = wareCode;
	}

	public String getWareCode() 
	{
		return wareCode;
	}
	public void setWareName(String wareName) 
	{
		this.wareName = wareName;
	}

	public String getWareName() 
	{
		return wareName;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
}
