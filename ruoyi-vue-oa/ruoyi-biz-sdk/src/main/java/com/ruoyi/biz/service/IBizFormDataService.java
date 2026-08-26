package com.ruoyi.biz.service;

/**
 * <p> 业务表单数据接口 </p>
 *
 * @Author wocurr.com
 */
public interface IBizFormDataService<T> {

    /**
     * 获取表单类型
     *
     * @return String
     */
    String getBizType();

    /**
     * 获取表单Class
     *
     * @return Class<?>
     */
    Class<?> getFormClass();

    /**
     * 保存表单数据
     *
     * @param formData 表单数据
     */
    String save(T formData);

    /**
     * 更新表单数据
     *
     * @param formData 表单数据
     */
    void update(T formData);

    /**
     * 获取表单数据
     *
     * @param bizId 业务ID
     * @return 表单数据
     */
    Object getBizForm(String bizId);

    /**
     * 初始化表单数据
     *
     * @param templateId 模板ID
     * @return 表单数据
     */
    Object initBizForm(String templateId);
}
