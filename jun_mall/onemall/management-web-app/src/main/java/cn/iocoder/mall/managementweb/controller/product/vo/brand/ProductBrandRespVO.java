package cn.iocoder.mall.managementweb.controller.product.vo.brand;

import lombok.*;
import io.swagger.annotations.*;
import java.util.*;

@ApiModel("商品品牌 Response VO")
@Data
public class ProductBrandRespVO {

    @ApiModelProperty(value = "品牌编号", required = true, example = "1024")
    private Integer id;
    @ApiModelProperty(value = "品牌名称", required = true, example = "这个商品品牌很吊")
    private String name;
    @ApiModelProperty(value = "品牌描述", example = "这个商品描述很吊")
    private String description;
    @ApiModelProperty(value = "品牌名图片", example = "http://www.iocoder.cn/xx.jpg")
    private String picUrl;
    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    private Integer status;
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
