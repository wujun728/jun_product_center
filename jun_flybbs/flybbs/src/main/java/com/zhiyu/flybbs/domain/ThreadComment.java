package com.zhiyu.flybbs.domain;

import java.io.Serializable;

/**
 * @author zhiyu
 */
public class ThreadComment implements Serializable {
    private static final long serialVersionUID = -5535531672646261652L;

    private int id;

    private Integer parentThreadId;

    private String content;

    private Integer likeCount;

    private String createUser;

    private String gmtCreate;

    private String gmtModified;

    private String isAccepted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getParentThreadId() {
        return parentThreadId;
    }

    public void setParentThreadId(Integer parentThreadId) {
        this.parentThreadId = parentThreadId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(String isAccepted) {
        this.isAccepted = isAccepted;
    }
}
