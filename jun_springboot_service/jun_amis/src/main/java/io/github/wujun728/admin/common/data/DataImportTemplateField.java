package io.github.wujun728.admin.common.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

/***
 * @date 2022-07-11 15:08:18
 * @remark 数据导入配置字段
 */
@Data
public class DataImportTemplateField extends BaseData {
    //模板id
    private Long templateId;
    //字段名
    private String columnName;
    //字段备注
    private String columnComment;
    //字段类型
    private String columnType;
    //格式化
    private String columnFormat;
    //字段映射
    private String fieldsMapping;
    //是否必填
    private String must;
    //正则表达式
    private String regex;
    //序号
    private Integer seq;
    //宽度
    private Integer width;
}