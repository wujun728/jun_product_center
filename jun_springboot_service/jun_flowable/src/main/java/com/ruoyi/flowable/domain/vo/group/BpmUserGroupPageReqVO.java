package com.ruoyi.flowable.domain.vo.group;

import com.ruoyi.common.mybatis.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel("管理后台 - 用户组分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmUserGroupPageReqVO extends PageParam {

    @ApiModelProperty(value = "组名", example = "芋道")
    private String name;

    @ApiModelProperty(value = "状态", example = "1")
    private Integer status;

    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date[] createTime;

}
