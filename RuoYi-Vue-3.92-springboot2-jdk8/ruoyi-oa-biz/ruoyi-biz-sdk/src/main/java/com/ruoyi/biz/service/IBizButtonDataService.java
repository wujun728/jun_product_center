package com.ruoyi.biz.service;

import com.ruoyi.biz.domain.CommonButton;

/**
 * <p> 业务按钮数据接口 </p>
 *
 * @Author wocurr.com
 */
public interface IBizButtonDataService {


    /**
     * 按钮code
     *
     * @return String
     */
    String getCode();

    /**
     * 按钮名称
     *
     * @return String
     */
    String getName();

    /**
     * 按钮排序
     *
     * @return Integer
     */
    Integer getSort();

    /**
     * 是否显示
     *
     * @return Boolean
     */
    Boolean isShow(CommonButton button);
}
