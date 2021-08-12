package com.sanri.tools.modules.name.remote.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

@Data
public class YoudaoTranslateRequest {
    private String from = "auto";
    private String to = "auto";
    private String signType = "v3";
    // 当前时间 单位:秒
    private String curtime;
    private String appKey;
    private String q;
    private String salt;
    private String sign;

    /**
     * 构建一个查询实体
     * @param q
     * @param appKey    在有道官网注册的 key
     * @param appSecret 在有道官网注册的 secret
     */
    public YoudaoTranslateRequest(String q, String appKey, String appSecret) {
        this.q = q;
        this.appKey = appKey;

        // 这里设置全部参数,使用者只需要传要查询的字符串就可以了
        salt = String.valueOf(System.currentTimeMillis());
        curtime = String.valueOf(System.currentTimeMillis() / 1000);
        String signStr = appKey + truncate(q) + salt + curtime + appSecret;

        // 加密 ,使用 sha256 然后 hex , 字符串不使用 utf-8
        byte[] bytes = DigestUtils.sha256(signStr.getBytes());
        sign = Hex.encodeHexString(bytes);
    }

    public static String truncate(String q) {
        if (q == null) {
            return null;
        }
        int len = q.length();
        String result;
        return len <= 20 ? q : (q.substring(0, 11) + len + q.substring(len - 10, len));
    }
}
