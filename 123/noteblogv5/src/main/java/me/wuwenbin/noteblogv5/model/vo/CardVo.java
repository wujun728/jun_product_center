package me.wuwenbin.noteblogv5.model.vo;

import cn.hutool.core.util.StrUtil;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author wuwen
 */
@Data
@Builder
public class CardVo implements Serializable {

    private String url;
    private String headerHtml;

    private String logoImgSrc;
    /**
     * 显示形状1：方形，0圆形
     */
    private int shape;

    private String cardTitle;
    private String cardDesc;

    private List<String> itemTextList;
    private String itemTexts;

    private String footerText;

    public List<String> getItemTextList() {
        if (StrUtil.isEmpty(itemTexts)) {
            return Collections.emptyList();
        } else {
            return Arrays.asList(itemTexts.split("\n"));
        }
    }


}
