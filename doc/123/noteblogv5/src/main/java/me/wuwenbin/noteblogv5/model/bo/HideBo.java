package me.wuwenbin.noteblogv5.model.bo;

import lombok.Getter;
import lombok.Setter;
import me.wuwenbin.noteblogv5.model.entity.Hide;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wuwen
 */
@Getter
@Setter
public class HideBo extends Hide implements Serializable {

    private String title;
    private Date purchaseTime;


    public HideBo(String id, String articleId, String hideType, String hideHtml, Integer hidePrice) {
        super(id, articleId, hideType, hideHtml, hidePrice);
    }
}
