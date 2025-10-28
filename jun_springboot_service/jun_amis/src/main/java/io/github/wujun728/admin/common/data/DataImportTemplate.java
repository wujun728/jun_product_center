package io.github.wujun728.admin.common.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/***
 * @date 2022-07-11 15:08:05
 * @remark 数据导入配置
 */
@Data
public class DataImportTemplate extends BaseData {
    //表
    private String tableName;
    //模板编号
    private String code;
    //模板名称
    private String name;
    //判重字段列表
    private String repeatFields;
    //判重处理策略
    private String repeatStrategy;
    private List<DataImportTemplateField> fields = new ArrayList<>();
}