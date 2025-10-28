package io.github.wujun728.admin.common.constants;

/***
 * @date 2022-07-13 10:15:26
 * @remark 数据导入状态
 */
public class DataImportTaskStatus {
    /**未开始*/
    public static final String NOT_START = "NotStart";
    /**正在验证*/
    public static final String VALIDATION = "Validation";
    /**验证失败*/
    public static final String IN_VALID = "InValid";
    /**正在导入*/
    public static final String IMPORTING = "Importing";
    /**导入成功*/
    public static final String SUCCESS = "Success";
    /**异常关闭*/
    public static final String EXCEPTION_CLOSE = "ExceptionClose";
}