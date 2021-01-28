package sy.model.app;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 药品批次 - 供客户端用
 * 
 * @author Wujun
 * 
 */
@SuppressWarnings("serial")
public class MyDrugTimes implements Serializable {
	
	private int id;
	private int drugCode;
	private String drugName;
	private String drugDesc;
	private String drugLotNo;
	
	private Date expireTime;
	private Date produceTime;
	private String specification;
	private BigDecimal price;
	private int total;
	private int rest;

	private String opUserId;
	private Date createTime;
	private Date updateTime;
	private String ext1;
	private byte status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getProduceTime() {
		return produceTime;
	}

	public void setProduceTime(Date produceTime) {
		this.produceTime = produceTime;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}

	public Date getCreateTime() {
		if (this.createTime != null)
			return this.createTime;
		return new Date();
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		if (this.updateTime != null)
			return this.updateTime;
		return new Date();
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the drugCode
	 */
	public int getDrugCode() {
		return drugCode;
	}

	/**
	 * @param drugCode
	 *            the drugCode to set
	 */
	public void setDrugCode(int drugCode) {
		this.drugCode = drugCode;
	}

	/**
	 * @return the drugName
	 */
	public String getDrugName() {
		return drugName;
	}

	/**
	 * @param drugName
	 *            the drugName to set
	 */
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	/**
	 * @return the drugDesc
	 */
	public String getDrugDesc() {
		return drugDesc;
	}

	/**
	 * @param drugDesc
	 *            the drugDesc to set
	 */
	public void setDrugDesc(String drugDesc) {
		this.drugDesc = drugDesc;
	}

	/**
	 * @return the drugLotNo
	 */
	public String getDrugLotNo() {
		return drugLotNo;
	}

	/**
	 * @param drugLotNo the drugLotNo to set
	 */
	public void setDrugLotNo(String drugLotNo) {
		this.drugLotNo = drugLotNo;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the rest
	 */
	public int getRest() {
		return rest;
	}

	/**
	 * @param rest the rest to set
	 */
	public void setRest(int rest) {
		this.rest = rest;
	}

}