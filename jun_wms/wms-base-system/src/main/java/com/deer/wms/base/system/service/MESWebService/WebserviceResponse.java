package com.deer.wms.base.system.service.MESWebService;

public class WebserviceResponse {
    private String taskCode;
    private String errorCode;
    private String errorMsg;
    private String resultData;

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    public WebserviceResponse() {

    }

    public WebserviceResponse(String taskCode, String errorCode, String errorMsg, String resultData) {
        this.taskCode = taskCode;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.resultData = resultData;
    }
}
