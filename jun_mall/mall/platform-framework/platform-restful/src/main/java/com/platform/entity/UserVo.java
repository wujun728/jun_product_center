package com.platform.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * @author lipengjun
 * @date 2017年11月20日 下午3:29:40
 */
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * weixin_openid
     */
    private String weixinOpenid;

    /**
     * 设置：id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置：用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取：用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置：密码
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * 获取：密码
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * 设置：性别
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取：性别
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置：出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取：出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置：手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置：weixin_openid
     */
    public void setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid;
    }

    /**
     * 获取：weixin_openid
     */
    public String getWeixinOpenid() {
        return weixinOpenid;
    }
}
