package com.deer.wms.common.core.result;

/**
 * 业务错误代码枚举需要实现的接口，其目的就是为了达到各个子系统或模块各自维护自己的业务错误代码。
 *
 * Created by Floki on 2017/9/28.
 */
public interface Code {
    /**
     * 获取错误代码
     *
     * @return 错误代码
     */
    int getCode();

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    String getMessage();

    /**
     * 设置错误信息
     *
     * @param message
     */
    void setMessage(String message);
}
