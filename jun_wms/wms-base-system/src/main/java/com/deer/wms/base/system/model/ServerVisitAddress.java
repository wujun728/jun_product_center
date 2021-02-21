package com.deer.wms.base.system.model;

import javax.persistence.*;

@Table(name = "server_visit_address")
public class ServerVisitAddress {
    /**
     * 请求接口Id
     */
    @Id
    @Column(name = "visit_address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer visitAddressId;

    /**
     * 服务地址
     */
    @Column(name = "visit_address")
    private String visitAddress;

    /**
     * 服务地址描述
     */
    private String description;

    /**
     * token用户名
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 获取请求接口Id
     *
     * @return visit_address_id - 请求接口Id
     */
    public Integer getVisitAddressId() {
        return visitAddressId;
    }

    /**
     * 设置请求接口Id
     *
     * @param visitAddressId 请求接口Id
     */
    public void setVisitAddressId(Integer visitAddressId) {
        this.visitAddressId = visitAddressId;
    }

    /**
     * 获取服务地址
     *
     * @return visit_address - 服务地址
     */
    public String getVisitAddress() {
        return visitAddress;
    }

    /**
     * 设置服务地址
     *
     * @param visitAddress 服务地址
     */
    public void setVisitAddress(String visitAddress) {
        this.visitAddress = visitAddress;
    }

    /**
     * 获取服务地址描述
     *
     * @return description - 服务地址描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置服务地址描述
     *
     * @param description 服务地址描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}