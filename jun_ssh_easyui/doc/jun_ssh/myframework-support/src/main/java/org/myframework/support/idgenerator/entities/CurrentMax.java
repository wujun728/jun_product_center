package org.myframework.support.idgenerator.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p>
 * Title: 流水号的当前最大号
 * </p>
 * <p>
 * Description: 航信税务软件-稽查部分
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: 合力金桥软件公司
 * </p>
 * 
 * @author Wujun
 * @version 1.0
 */
@Entity
@Table(name = "TBL_SYS_CURRENT_MAX")
public class CurrentMax implements Serializable {

	/**
	 * 主键，唯一标识
	 */
	@EmbeddedId
	private CurrentMaxPK pk = new CurrentMaxPK();

	/**
	 * 当前最大值
	 */
	@Column(name = "CURRENT_VALUE")
	private BigDecimal value;

	/**
	 * 类别，1静态，2动态
	 */
	@Column(name = "MAX_TYPE")
	private String type;

	/**
	 * 备注
	 */
	@Column(name = "MEMO")
	private String affix;

	/** full constructor */
	public CurrentMax(CurrentMaxPK pk, BigDecimal value, String type,
			String affix) {
		this.pk = pk;
		this.value = value;
		this.type = type;
		this.affix = affix;
	}

	/** default constructor */
	public CurrentMax() {
	}

	/** minimal constructor */
	public CurrentMax(CurrentMaxPK comp_id) {
		this.pk = comp_id;
	}

	public CurrentMaxPK getPk() {
		return this.pk;
	}

	public void setPk(CurrentMaxPK comp_id) {
		this.pk = comp_id;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAffix() {
		return this.affix;
	}

	public void setAffix(String affix) {
		this.affix = affix;
	}

	public String toString() {
		return new ToStringBuilder(this).append("comp_id", getPk()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof CurrentMax))
			return false;
		CurrentMax castOther = (CurrentMax) other;
		return new EqualsBuilder().append(this.getPk(), castOther.getPk())
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getPk()).toHashCode();
	}

	@Embeddable
	public static class CurrentMaxPK implements Serializable {
		@Column(name = "SEQUENCE_ID",length=40)
		private String sequenceId;

		@Column(name = "MAX_ID", length = 50)
		private String maxId;


		public CurrentMaxPK() {
			super();
		}

		public CurrentMaxPK(String sequenceId, String maxId) {
			super();
			this.sequenceId = sequenceId;
			this.maxId = maxId;
		}

		public String getSequenceId() {
			return sequenceId;
		}

		public void setSequenceId(String sequenceId) {
			this.sequenceId = sequenceId;
		}

		public String getMaxId() {
			return maxId;
		}

		public void setMaxId(String maxId) {
			this.maxId = maxId;
		}

	}

}
