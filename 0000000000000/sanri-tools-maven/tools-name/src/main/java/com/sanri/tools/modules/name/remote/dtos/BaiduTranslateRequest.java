package com.sanri.tools.modules.name.remote.dtos;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;

@Data
public class BaiduTranslateRequest {
    private String from = "zh";
    private String to = "en";
    private String q;
    private String salt;
    private String sign;
    private String appid;

    public BaiduTranslateRequest(String q, String appid,String secret) {
        this.q = q;
        this.appid = appid;

        salt = String.valueOf(System.currentTimeMillis());
        sign = DigestUtils.md5Hex(appid + q + salt + secret);
    }
}
