package me.wuwenbin.noteblogv5.model.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wuwen
 */
@Data
@Builder
public class Cash implements Serializable {

    private Long id;
    private String cashNo;
    private int cashValue;
    private Date createTime;
    private Date rechargeTime;
    private boolean enable;
    private Long userId;
    private String nickname;
}
