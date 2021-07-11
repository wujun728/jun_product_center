package com.itcqm.cake21.entity;

import java.math.BigDecimal;
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
public class GoodsSpec implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品规格id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id(外键)
     */
    private Integer goodsId;

    /**
     * 商品规格(磅)
     */
    private BigDecimal goodsSizeB;

    /**
     * 商品规格(克)
     */
    private Integer goodsSizeG;

    /**
     * 商品尺寸
     */
    private String goodsSize;

    /**
     * 商品配件
     */
    private String goodsParts;

    /**
     * 商品价格(以规格变动)
     */
    private BigDecimal goodsPrice;


}
