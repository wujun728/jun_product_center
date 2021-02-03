package me.wuwenbin.noteblogv5.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import me.wuwenbin.noteblogv5.model.entity.Message;

/**
 * @author wuwen
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MessageBo extends Message {

    private String nickname;
    private String avatar;
    private String role;
}
