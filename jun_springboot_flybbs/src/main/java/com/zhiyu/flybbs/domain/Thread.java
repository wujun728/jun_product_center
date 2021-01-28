package com.zhiyu.flybbs.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author Wujun
 */
public class Thread implements Serializable {

    private static final long serialVersionUID = 8318642423911577778L;

    private int id;

    private String title;

    private String content;

    private String category;

    private Integer rewardKissCount;

    private String status;

    private Integer commentCount;

    private String isTop;

    private String isStar;

    private String gmtCreate;

    private String gmtModified;

    private String createUser;

    private List<ThreadComment> threadCommentList;

    public List<ThreadComment> getThreadCommentList() {
        return threadCommentList;
    }

    public void setThreadCommentList(List<ThreadComment> threadCommentList) {
        this.threadCommentList = threadCommentList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getRewardKissCount() {
        return rewardKissCount;
    }

    public void setRewardKissCount(Integer rewardKissCount) {
        this.rewardKissCount = rewardKissCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getIsStar() {
        return isStar;
    }

    public void setIsStar(String isStar) {
        this.isStar = isStar;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
