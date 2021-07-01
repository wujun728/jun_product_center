package cn.lxsir.uniapp.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Auth:luoxiang
 * Time:2019/7/7 5:53 PM
 * Desc: 图片识别结果
 */
@Data
public class ImageResultVo  implements Serializable{
    private static final long serialVersionUID = 1L;

    private double score;
    private String root;
    private String keyword;
}
