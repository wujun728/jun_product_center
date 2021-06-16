package me.wuwenbin.noteblogv5.model.bo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wuwen
 */
@Data
@Builder
public class EchartsBo implements Serializable {

    private String name;
    private String value;
}
