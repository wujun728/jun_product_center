package com.shuogesha.cms.entity;

import java.io.Serializable;
/**
 * 举报or意见反馈
 * @author zhaohaiyuan
 *
 */
public class Feedback implements Serializable {
	
	private Long id;
	private String name="jubao";//举报类型：jubao、yijian等
	private String content;//举报内容
	private String status="0";//举报状态0,待处理，1已经处理
	private String img;//图片
	private String remark;//备注/回复
	private String dateline;//举报时间
	private Long userId;//提交人
	private Integer toUid;//举报用户可为空
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content=content;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status=status;
	}
	public String getImg() {
		return this.img;
	}
	public void setImg(String img) {
		this.img=img;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark=remark;
	}
	public String getDateline() {
		return this.dateline;
	}
	public void setDateline(String dateline) {
		this.dateline=dateline;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getToUid() {
		return toUid;
	}

	public void setToUid(Integer toUid) {
		this.toUid = toUid;
	} 
	
}
