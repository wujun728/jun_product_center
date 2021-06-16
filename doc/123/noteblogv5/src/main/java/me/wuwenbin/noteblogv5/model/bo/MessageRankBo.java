package me.wuwenbin.noteblogv5.model.bo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * created by Wuwenbin on 2019/10/21 at 5:32 下午
 *
 * @author wuwenbin
 */
@Data
@Builder
public class MessageRankBo implements Serializable {

    private String avatar;
    private String nickname;
    private String msgCnt;
    private String latestMsgDate;

}
