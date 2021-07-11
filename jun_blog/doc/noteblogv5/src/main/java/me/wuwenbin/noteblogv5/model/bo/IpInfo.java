package me.wuwenbin.noteblogv5.model.bo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * created by Wuwenbin on 2019/3/13 at 11:53
 *
 * @author wuwen
 */
@Data
@Builder
public class IpInfo implements Serializable {

    /**
     * ip详细信息地址
     */
    private String address;

    /**
     * 运营商/网络线路
     */
    private String line;
}
