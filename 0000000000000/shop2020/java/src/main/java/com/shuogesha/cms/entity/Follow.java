package com.shuogesha.cms.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;

import com.shuogesha.platform.entity.UnifiedUser;
/**
 * 用户关注功能
 * @author zhaohaiyuan
 *
 */
public class Follow implements Serializable {
	
		private Long id;
	private String name;
	private Long fromUid;//来源
	private Long toUid;//关注人
	private String dateline;
	
	private UnifiedUser toUser;
	private UnifiedUser fromUser;
	
	@Transient
	private Boolean followed;//是否关注
 
 
	public Boolean getFollowed() {
        return followed;
    }

    public void setFollowed(Boolean followed) {
        this.followed = followed;
    }

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
	}  	public Long getFromUid() {
		return fromUid;
	}

	public void setFromUid(Long fromUid) {
		this.fromUid = fromUid;
	}

	public Long getToUid() {
		return toUid;
	}

	public void setToUid(Long toUid) {
		this.toUid = toUid;
	}

	public String getDateline() {
		return this.dateline;
	}
	public void setDateline(String dateline) {
		this.dateline=dateline;
	}

	public UnifiedUser getToUser() {
		return toUser;
	}

	public void setToUser(UnifiedUser toUser) {
		this.toUser = toUser;
	}

	public UnifiedUser getFromUser() {
		return fromUser;
	}

	public void setFromUser(UnifiedUser fromUser) {
		this.fromUser = fromUser;
	}

	 
	
	
}
