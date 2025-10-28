package io.github.wujun728.admin.common.data;
import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

import java.util.Date;

/***
 * @date 2022-01-27 14:35:19
 * @remark 系统文件
 */
@Data
public class SysFile extends BaseData {
    //文件名
    private String fileName;
    //文件后缀
    private String suffix;
    //文件大小
    private Long size;
    //路径
    private String path;
    //上传时间
    private Date uploadTime;
    //文件类型
    private String contentType;
}