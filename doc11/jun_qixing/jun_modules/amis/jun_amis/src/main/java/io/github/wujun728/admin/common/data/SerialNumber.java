package io.github.wujun728.admin.common.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

/***
 * @date 2022-01-28 11:14:41
 * @remark 序号
 */
@Data
public class SerialNumber extends BaseData {
    //编号
    private String code;
    //名称
    private String name;
    //前缀
    private String prefix;
    //日期格式
    private String dateFormat;
    //当前日期
    private String curDate;
    //当前顺序号
    private Integer curSerial;
    //顺序号长度
    private Integer serialLenth;
}