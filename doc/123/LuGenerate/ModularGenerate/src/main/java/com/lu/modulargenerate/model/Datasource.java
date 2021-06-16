package com.lu.modulargenerate.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Project sKnowledge_Blog
 * @Author: zhanglu
 * @Date: 2019-07-14 16:06:25
 * @Description: 数据源 实体类
 */
@Data
@Accessors(chain = true)
public class Datasource {

   /**
    * url
    */
    private String url;

   /**
    * 驱动
    */
    private String driver;

   /**
    * 用户名
    */
    private String username;

   /**
    * 密码
    */
    private String password;

}
