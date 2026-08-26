package com.ruoyi.seal.enums;

import lombok.Getter;

/**
 * 盖章位置枚举
 *
 * @Author wocurr.com
 */
@Getter
public enum SealPositionEnum {
    LEFT("0", "left", "同一行盖章，一般甲方在左"),
    RIGHT("1", "right", "同一行盖章，一般乙方在右，或者只有一方章，一般也在右"),
    ;

    private String participant;
    private String position;
    private String desc;

    SealPositionEnum(String participant, String position, String desc) {
        this.participant = participant;
        this.desc = desc;
        this.position = position;
    }

    public static SealPositionEnum getByParticipant(String participant) {
        for (SealPositionEnum item : SealPositionEnum.values()) {
            if (item.getParticipant().equals(participant)) {
                return item;
            }
        }
        return null;
    }
}
