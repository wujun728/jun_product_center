package com.deer.wms.base.system.model.ware;


import javax.persistence.*;
import javax.validation.groups.ConvertGroup;


/**
 * 供应商表 supplier
 * 
 * @author guo
 * @date 2019-06-16
 */
@Table(name = "supplier")
public class Supplier
{
	/** ID */
	@Id
	@Column(name = "supplier_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer supplierId;
	/** 供应商名称 */
	@Column(name = "supplier_name")
	private String supplierName;
	/** 供应商编码 */
	@Column(name="supplier_code")
	private String supplierCode;
	/** 联系人 */
	@Column(name = "link_man")
	private String linkMan;
	/** 联系方式 */
	@Column(name = "link_phone")
	private String linkPhone;
	/** 邮箱 */
	@Column(name = "email")
	private String email;
	/** 地址 */
	@Column(name = "address")
	private String address;
	/** EBS供应商ID */
	@Column(name="vendor_id")
	private Integer vendorId;

	public void setSupplierId(Integer supplierId)
	{
		this.supplierId = supplierId;
	}

	public Integer getSupplierId()
	{
		return supplierId;
	}
	public void setSupplierName(String supplierName) 
	{
		this.supplierName = supplierName;
	}

	public String getSupplierName() 
	{
		return supplierName;
	}
	public void setLinkMan(String linkMan) 
	{
		this.linkMan = linkMan;
	}

	public String getLinkMan() 
	{
		return linkMan;
	}
	public void setLinkPhone(String linkPhone) 
	{
		this.linkPhone = linkPhone;
	}

	public String getLinkPhone() 
	{
		return linkPhone;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getEmail() 
	{
		return email;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public Supplier() {
	}

	public Supplier(String supplierName, String supplierCode, Integer vendorId) {
		this.supplierName = supplierName;
		this.supplierCode = supplierCode;
		this.vendorId = vendorId;
	}
}
