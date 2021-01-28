package com.doroodo.sys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sy_parameter")
public class SyParameter implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String navtitle;
	private String footer;

	// Constructors

	/** default constructor */
	public SyParameter() {
	}

	/** full constructor */
	public SyParameter(String title, String navtitle, String footer) {
		this.title = title;
		this.navtitle = navtitle;
		this.footer = footer;
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

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "navtitle")
	public String getNavtitle() {
		return this.navtitle;
	}

	public void setNavtitle(String navtitle) {
		this.navtitle = navtitle;
	}

	@Column(name = "footer")
	public String getFooter() {
		return this.footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

}