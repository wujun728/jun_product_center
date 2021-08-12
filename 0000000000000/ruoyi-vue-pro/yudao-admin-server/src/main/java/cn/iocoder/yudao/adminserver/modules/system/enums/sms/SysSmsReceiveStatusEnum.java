package cn.iocoder.yudao.adminserver.modules.system.enums.sms;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 短信的接收状态枚举
 *
 * @author 芋道源码
 * @date 2021/2/1 13:39
 */
@Getter
@AllArgsConstructor
public enum SysSmsReceiveStatusEnum {

    INIT(0), // 初始化
    SUCCESS(10), // 接收成功
    FAILURE(20), // 接收失败
    ;

    private final int status;

}
