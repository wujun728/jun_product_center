package com.erp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 自己发送到外部的entity，可自己定义
 * @ClassName: User 
 * @Description: 
 * @author: renkai721
 * @date: 2018年6月25日 下午1:31:56
 */
@Data
@Entity
@Table(name = "USERS")
public class User {
	@Id   // 表明id
    @GeneratedValue   //  自动生成
    @Column(name = "USER_ID", unique = true, nullable = false)
	private String id;
	@Column(name = "NAME", length = 50)
	private String name;
}
