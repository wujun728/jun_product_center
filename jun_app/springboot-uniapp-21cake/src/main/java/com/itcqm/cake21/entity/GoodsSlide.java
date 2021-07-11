package com.itcqm.cake21.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author CQM
 * @since 2020-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GoodsSlide implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 轮播图id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id(外键)
     */
    private Integer goodsId;

    /**
     * 轮播图顺序
     */
    private Integer slideOrder;

    /**
     * 轮播图地址(单个)
     */
    private String slideImg;


}
