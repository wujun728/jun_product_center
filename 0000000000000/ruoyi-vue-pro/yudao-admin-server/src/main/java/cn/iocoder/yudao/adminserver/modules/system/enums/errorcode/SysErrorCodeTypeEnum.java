package cn.iocoder.yudao.adminserver.modules.system.enums.errorcode;

import cn.iocoder.yudao.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 错误码的类型枚举
 *
 * @author dylan
 */
@AllArgsConstructor
@Getter
public enum SysErrorCodeTypeEnum implements IntArrayValuable {

    /**
     * 自动生成
     */
    AUTO_GENERATION(1),
    /**
     * 手动编辑
     */
    MANUAL_OPERATION(2);

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(SysErrorCodeTypeEnum::getType).toArray();

    /**
     * 类型
     */
    private final Integer type;

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
