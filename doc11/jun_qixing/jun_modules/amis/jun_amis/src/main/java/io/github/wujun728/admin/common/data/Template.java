package io.github.wujun728.admin.common.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

/***
 * @date 2023-03-31 10:49:35
 * @remark 模板
 */
@Data
public class Template extends BaseData {
    //路径
    private String path;
    //名称
    private String name;
    //序号
    private Integer seq;
    //父id
    private Long parentId;
    //内容
    private String content;
}
