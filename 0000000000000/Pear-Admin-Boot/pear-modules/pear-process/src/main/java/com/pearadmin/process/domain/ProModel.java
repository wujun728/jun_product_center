package com.pearadmin.process.domain;

import com.pearadmin.common.web.base.BaseDomain;
import lombok.Data;

/**
 * Describe: 流程模型实体
 * Author: 就眠仪式
 * createTime: 2019/10/23
 * */
@Data
public class ProModel extends BaseDomain {

        /**
         * 编号
         * */
        private String id;

        /**
         * 名称
         * */
        private String name;

        /**
         * 标识
         * */
        private String key;

        /**
         * 版本
         * */
        private Integer version;
}
