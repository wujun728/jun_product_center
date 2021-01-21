package com.doroodo.sys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * SyOrgan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sy_organ")
public class SyOrgan implements java.io.Serializable {

	// Fields

	private Integer id;
	private String organid="";
	private String uporganid;
	private String organname;
	private String routeid;
	private String routename;

	// Constructors

	/** default constructor */
	public SyOrgan() {
	}

	/** minimal constructor */
	public SyOrgan(String organid, String uporganid, String organname) {
		this.organid = organid;
		this.uporganid = uporganid;
		this.organname = organname;
	}

	/** full constructor */
	public SyOrgan(String organid, String uporganid, String organname, String routeid, String routename) {
		this.organid = organid;
		this.uporganid = uporganid;
		this.organname = organname;
		this.routeid = routeid;
		this.routename = routename;
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

	@Column(name = "organid")
	public String getOrganid() {
		return this.organid;
	}

	public void setOrganid(String organid) {
		this.organid = organid;
	}

	@Column(name = "uporganid", nullable = false)
	public String getUporganid() {
		return this.uporganid;
	}

	public void setUporganid(String uporganid) {
		this.uporganid = uporganid;
	}

	@Column(name = "organname", nullable = false)
	public String getOrganname() {
		return this.organname;
	}

	public void setOrganname(String organname) {
		this.organname = organname;
	}

	@Column(name = "routeid")
	public String getRouteid() {
		return this.routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	@Column(name = "routename")
	public String getRoutename() {
		return this.routename;
	}

	public void setRoutename(String routename) {
		this.routename = routename;
	}

}