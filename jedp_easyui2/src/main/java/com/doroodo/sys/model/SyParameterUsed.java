package com.doroodo.sys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * SyParameterUsed entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sy_parameter_used")
public class SyParameterUsed implements java.io.Serializable {

	// Fields

	private Integer id;
	private String syParameterId;

	// Constructors

	/** default constructor */
	public SyParameterUsed() {
	}

	/** minimal constructor */
	public SyParameterUsed(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public SyParameterUsed(Integer id, String syParameterId) {
		this.id = id;
		this.syParameterId = syParameterId;
	}

	// Property accessors
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "increment")
	@Column(name = "Id", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "sy_parameter_id")
	public String getSyParameterId() {
		return this.syParameterId;
	}

	public void setSyParameterId(String syParameterId) {
		this.syParameterId = syParameterId;
	}

}