package com.ruoyi.biz.service;

import com.ruoyi.biz.domain.CommonForm;

/**
 * <p> 业务表单服务接口 </p>
 *
 * @Author wocurr.com
 */
public interface IBizFormService {

    /**
     * 保存
     *
     * @param commonForm 表单参数
     */
    String save(CommonForm commonForm);

    /**
     * 更新
     *
     * @param commonForm 表单参数
     */
    void update(CommonForm commonForm);

    /**
     * 获取表单数据
     *
     * @param commonForm 表单参数
     * @return 表单数据
     */
    Object getBizForm(CommonForm commonForm);
}
