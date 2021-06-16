package com.lu.modulargenerate.service;



import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.lu.modulargenerate.model.CodeStrategy;
import com.lu.modulargenerate.model.Datasource;

import javax.servlet.http.HttpServletResponse;

public interface GeneratorCodeService {

    /**
     * 检查数据源连接
     * @param driver
     * @param url
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    Object check(String driver, String url, String username, String password) throws Exception;

    /**
     * 获取表信息
     * @param driver
     * @param url
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    Object getTableInfo(String driver, String url, String username, String password) throws Exception;

    /**
     * 执行
     * @return
     * @throws Exception
     */
    void execute(HttpServletResponse response, String codeStrategyData, String datasourceData) throws Exception;

}
