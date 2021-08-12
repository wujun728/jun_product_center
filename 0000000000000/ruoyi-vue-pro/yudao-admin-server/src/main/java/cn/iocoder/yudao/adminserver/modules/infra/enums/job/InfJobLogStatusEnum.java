package cn.iocoder.yudao.adminserver.modules.infra.enums.job;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务日志的状态枚举
 *
 * @author 芋道源码
 */
@Getter
@AllArgsConstructor
public enum InfJobLogStatusEnum {

    RUNNING(0), // 运行中
    SUCCESS(1), // 成功
    FAILURE(2); // 失败

    /**
     * 状态
     */
    private final Integer status;

}
