package com.itcqm.cake21.entity;

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
public class GoodsImg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品详细图片id
     */
    private Integer id;

    /**
     * 商品id(外键)
     */
    private Integer goodsId;

    /**
     * 商品详细图片顺序
     */
    private Integer imgOrder;

    /**
     * 商品详细图片地址(单个)
     */
    private String img;


}
