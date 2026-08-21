package com.ruoyi.seal.enums;

import java.awt.*;

/**
 * @Author wocurr.com
 */
public enum SealColorEnum {
    RED("red", "红色"),
    BLUE("blue", "蓝色");

    private String code;
    private String message;
    SealColorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public static Color getByCode(String code) {
        for (SealColorEnum item : SealColorEnum.values()) {
            if (item.getCode().equals(code)) {
                if (item.getCode().equals("red")) {
                    return Color.RED;
                } else {
                    return Color.BLUE;
                }
            }
        }
        return null;
    }
    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
