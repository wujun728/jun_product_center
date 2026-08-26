package com.ruoyi.serial.api;

/**
 * 编号生成服务
 * @Author wocurr.com
 */
public interface ICodeGenService {

    /**
     * 获取下一个编号
     * @param confId 编号配置id
     * @return
     */
    public String getNextCode(String confId);
}
