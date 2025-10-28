package com.jun.plugin.demo.jpa.respository.baseEntity;

import java.io.Serializable;
import javax.persistence.*;

import com.jun.plugin.common.dataaccess.entity.BaseEntity;
import com.jun.plugin.common.dataaccess.entity.VersionEntity;

import java.util.Date;


/**
 * The persistent class for the aentity database table.
 * 
 */
@Entity
@Table(name="aentity")
@Access(AccessType.FIELD)
public class Aentity extends VersionEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String content;
	
	@Column(name="AentityName")
	private String aentityName;
	
    public String getAentityName() {
		return aentityName;
	}

	public void setAentityName(String aentityName) {
		this.aentityName = aentityName;
	}

	public Aentity() {
    	
    }

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}