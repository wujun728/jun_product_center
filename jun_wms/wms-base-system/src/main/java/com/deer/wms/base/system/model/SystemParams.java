package com.deer.wms.base.system.model;

public class SystemParams {
    //请求ID，随机字符串UUID()获取，需保证一致性
    private String requestId;
    //应用名称
    private String application;
    //源系统(PCB_APS/PCBA_APS/SRM/E-SOURCE)
    private String sourceSystem;
    //目标系统（ESB、EBS、INPLAN）
    private String targetSystem;
    //实际调用接口程序名
    private String serviceName;
    //随机数
    private Integer nonce;
    //时间戳
    private Long timestamp;
    //服务操作类型{"S":"查询","E":"执行"}
    private String serviceOperation;
    //接口版本
    private String serviceVersion;
    //鉴权token
    private String token;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getTargetSystem() {
        return targetSystem;
    }

    public void setTargetSystem(String targetSystem) {
        this.targetSystem = targetSystem;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getNonce() {
        return nonce;
    }

    public void setNonce(Integer nonce) {
        this.nonce = nonce;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getServiceOperation() {
        return serviceOperation;
    }

    public void setServiceOperation(String serviceOperation) {
        this.serviceOperation = serviceOperation;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SystemParams() {
    }

    public SystemParams(String requestId, String application, String sourceSystem, String targetSystem, String serviceName, Integer nonce, Long timestamp, String serviceOperation, String serviceVersion, String token) {
        this.requestId = requestId;
        this.application = application;
        this.sourceSystem = sourceSystem;
        this.targetSystem = targetSystem;
        this.serviceName = serviceName;
        this.nonce = nonce;
        this.timestamp = timestamp;
        this.serviceOperation = serviceOperation;
        this.serviceVersion = serviceVersion;
        this.token = token;
    }
}