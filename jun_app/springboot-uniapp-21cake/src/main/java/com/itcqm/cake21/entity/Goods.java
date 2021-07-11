package com.itcqm.cake21.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
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
@ToString
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 商品英文名
     */
    private String ename;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品标题图片地址
     */
    private String titleImg;

    /**
     * 新品标题图片地址
     */
    private String newImg;
    /**
     * 商品详细信息(描述)
     */
    private String detail;

    /**
     * 商品标记(例如:新品,儿童,成人)(标记新品将在首页显示)[因此标签无其他用处,所以暂存在此]
     */
    private String tag;

    /**
     * 商品分类(外键)
     */
    private Integer type;

    /**
     * 商品默认规格(虚外键)
     */
    private Integer defaultSize;

    /**
     * 商品状态(-1为下架,0为上架中,1为新品,新品会影响商品标记)
     */
    private Integer state;

    /**
     * 商品轮播图集合
     */
    private List<GoodsSlide> goodsSlideList;

    /**
     * 商品详细图片集合
     */
    private List<GoodsImg> goodsImgList;

    /**
     * 商品备注集合
     */
    private List<GoodsRemark> goodsRemarkList;

    /**
     * 商品规格集合
     */
    private List<GoodsSpec> goodsSpecList;
}
