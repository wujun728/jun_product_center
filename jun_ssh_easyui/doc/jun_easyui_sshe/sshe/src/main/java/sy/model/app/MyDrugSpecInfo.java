package sy.model.app;

import java.io.Serializable;

public class MyDrugSpecInfo implements Serializable {

	private int specId;
	private int drugCode;
	private String drugName;
	private String drugDesc;
	private String specification;
	private String ext1;
	private int customerId;
	private String customerName;
	private String unit;

	/**
	 * @return the specId
	 */
	public int getSpecId() {
		return specId;
	}

	/**
	 * @param specId
	 *            the specId to set
	 */
	public void setSpecId(int specId) {
		this.specId = specId;
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
	 * @return the specification
	 */
	public String getSpecification() {
		return specification;
	}

	/**
	 * @param specification
	 *            the specification to set
	 */
	public void setSpecification(String specification) {
		this.specification = specification;
	}

	/**
	 * @return the ext1
	 */
	public String getExt1() {
		return ext1;
	}

	/**
	 * @param ext1
	 *            the ext1 to set
	 */
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

}