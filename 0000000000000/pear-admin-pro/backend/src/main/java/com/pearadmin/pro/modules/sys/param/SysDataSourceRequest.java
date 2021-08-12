package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.base.page.PageRequest;
import lombok.Data;

@Data
public class SysDataSourceRequest extends PageRequest {

    /**
     * 多库名称
     * */
    private String name;

}
