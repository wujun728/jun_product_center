package me.wuwenbin.noteblogv5.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wuwen
 */

public enum OperateType implements IEnum<Integer> {
    /**
     * 硬币增加/减少的操作类型
     */
    ADMIN_DEDUCT_MINUS(-2, "管理员扣除", Type.MINUS),
    PURCHASE_MINUS(-1, "购买主题扣减", Type.MINUS),
    INIT_REG(0, "初始注册：0", Type.ADD),
    SIGN_ADD(1, "签到充值", Type.ADD),
    CASH_RECHARGE_ADD(2, "点卡充值", Type.ADD),
    ALIPAY_RECHARGE_ADD(3, "支付宝充值", Type.ADD),
    ADMIN_RECHARGE_ADD(4, "管理员直接充值", Type.ADD);

    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String desc;
    @Getter
    @Setter
    private Type type;


    OperateType(int code, String desc, Type type) {
        this.code = code;
        this.desc = desc;
        this.type = type;
    }

    @Override
    public Integer getValue() {
        return this.code;
    }

    private enum Type {
        /**
         * +或-
         */
        ADD, MINUS
    }

}
