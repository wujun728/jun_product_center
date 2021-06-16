package me.wuwenbin.noteblogv5.model.bo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wuwen
 */
@Data
@Builder
public class EchartsUrlBo implements Serializable {

    private String item;
    private long cnt;

}
