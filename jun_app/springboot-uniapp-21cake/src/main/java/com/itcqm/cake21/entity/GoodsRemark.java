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
public class GoodsRemark implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品备注id
     */
    private Integer id;

    /**
     * 商品id(外键)
     */
    private Integer goodsId;

    /**
     * 商品备注标题
     */
    private String remarkKey;

    /**
     * 商品备注值
     */
    private String remarkVal;


}
