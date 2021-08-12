package com.pearadmin.system.domain;

import com.pearadmin.common.web.base.BaseDomain;
import lombok.Data;

import java.time.LocalDate;

/**
 * Describe: 文 件 接 口 实 体
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
public class SysFile extends BaseDomain {

    /**
     * 文件编号
     * */
    private String id;

    /**
     * 文件名称
     * */
    private String fileName;

    /**
     * 文件路径
     * */
    private String filePath;

    /**
     * 文件类型
     * */
    private String fileType;

    /**
     * 文件大小
     * */
    private String fileSize;

    /**
     * 文件描述
     * */
    private String fileDesc;

    /**
     * 所属日期
     * */
    private LocalDate targetDate;

}
