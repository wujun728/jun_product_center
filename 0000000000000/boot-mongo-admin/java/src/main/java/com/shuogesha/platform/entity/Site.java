package com.shuogesha.platform.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 系统设置
 * @author zhaohaiyuan
 *
 */
@Document(collection="site")
public class Site implements Serializable {
	
	@Id 
	private String tongjiCode; 
	private java.lang.String countClearTime;
	private java.lang.String countCopyTime; 
	private String yaoqingOpen;//邀请注册 
	private String oss;//附件存储方案
	
	private Integer yaoqingLevel;//分销等级
	private Double yaoqingOne;//一级奖励
	private Double yaoqingTwo;//二级奖励
	
	private String gitHost;//gitlab地址
	private String gitToken;//gitlab的token 

	private String imagesHost;//镜像地址
	private String imagesOrg;//镜像机构
 	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getTplSolution() {
		return tplSolution;
	}
	public void setTplSolution(String tplSolution) {
		this.tplSolution = tplSolution;
	}
	public String getTongjiCode() {
		return tongjiCode;
	}
	public void setTongjiCode(String tongjiCode) {
		this.tongjiCode = tongjiCode;
	}
	public String getDxHost() {
		return dxHost;
	}
	public void setDxHost(String dxHost) {
		this.dxHost = dxHost;
	}
	public String getDxAppkey() {
		return dxAppkey;
	}
	public void setDxAppkey(String dxAppkey) {
		this.dxAppkey = dxAppkey;
	}
	public String getDxSecret() {
		return dxSecret;
	}
	public void setDxSecret(String dxSecret) {
		this.dxSecret = dxSecret;
	}
	public String getDxSign() {
		return dxSign;
	}
	public void setDxSign(String dxSign) {
		this.dxSign = dxSign;
	}
	public String getDxTpl() {
		return dxTpl;
	}
	public void setDxTpl(String dxTpl) {
		this.dxTpl = dxTpl;
	}
	public String getEmailPort() {
		return emailPort;
	}
	public void setEmailPort(String emailPort) {
		this.emailPort = emailPort;
	}
	public String getEmailUsername() {
		return emailUsername;
	}
	public void setEmailUsername(String emailUsername) {
		this.emailUsername = emailUsername;
	}
	public String getEmailHost() {
		return emailHost;
	}
	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}
	public String getEmailPassword() {
		return emailPassword;
	}
	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}
	public String getEmailEncoding() {
		return emailEncoding;
	}
	public void setEmailEncoding(String emailEncoding) {
		this.emailEncoding = emailEncoding;
	}
	public String getMessageCodeSubject() {
		return messageCodeSubject;
	}
	public void setMessageCodeSubject(String messageCodeSubject) {
		this.messageCodeSubject = messageCodeSubject;
	}
	public String getMessageCodeText() {
		return messageCodeText;
	}
	public void setMessageCodeText(String messageCodeText) {
		this.messageCodeText = messageCodeText;
	}
	public java.lang.String getCountClearTime() {
		return countClearTime;
	}
	public void setCountClearTime(java.lang.String countClearTime) {
		this.countClearTime = countClearTime;
	}
	public java.lang.String getCountCopyTime() {
		return countCopyTime;
	}
	public void setCountCopyTime(java.lang.String countCopyTime) {
		this.countCopyTime = countCopyTime;
	}
	public String getYaoqingOpen() {
		return yaoqingOpen;
	}
	public void setYaoqingOpen(String yaoqingOpen) {
		this.yaoqingOpen = yaoqingOpen;
	}
	public String getOss() {
		return oss;
	}
	public void setOss(String oss) {
		this.oss = oss;
	}
	public Integer getYaoqingLevel() {
		return yaoqingLevel;
	}
	public void setYaoqingLevel(Integer yaoqingLevel) {
		this.yaoqingLevel = yaoqingLevel;
	}
	public Double getYaoqingOne() {
		return yaoqingOne;
	}
	public void setYaoqingOne(Double yaoqingOne) {
		this.yaoqingOne = yaoqingOne;
	}
	public Double getYaoqingTwo() {
		return yaoqingTwo;
	}
	public void setYaoqingTwo(Double yaoqingTwo) {
		this.yaoqingTwo = yaoqingTwo;
	}
	public String getGitHost() {
		return gitHost;
	}
	public void setGitHost(String gitHost) {
		this.gitHost = gitHost;
	}
	public String getGitToken() {
		return gitToken;
	}
	public void setGitToken(String gitToken) {
		this.gitToken = gitToken;
	}
	public String getImagesHost() {
		return imagesHost;
	}
	public void setImagesHost(String imagesHost) {
		this.imagesHost = imagesHost;
	}
	public String getImagesOrg() {
		return imagesOrg;
	}
	public void setImagesOrg(String imagesOrg) {
		this.imagesOrg = imagesOrg;
	} 
	
	
}