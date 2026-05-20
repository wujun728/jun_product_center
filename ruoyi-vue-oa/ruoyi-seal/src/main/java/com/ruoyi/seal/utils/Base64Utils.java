package com.ruoyi.seal.utils;

import java.util.Base64;

/**
 * Base64工具类
 * @Author wocurr.com
 */
public class Base64Utils {
    /**
     * 将带前缀的Base64字符串转为字节数组
     * @param base64Str 示例："data:image/png;base64,iVBORw0KGg..."
     * @return 图片字节数组
     */
    public static byte[] base64ToBytes(String base64Str) {
        // 去除前缀（如果有）
        if (base64Str.contains(",")) {
            base64Str = base64Str.split(",")[1];
        }
        // 解码
        return Base64.getDecoder().decode(base64Str);
    }
}
