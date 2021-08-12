package com.wechat.oauth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

import com.wechat.util.ConfKit;

/**
 * paySign
 *
 */
public class PaySign {

    /**
     * 参与 paySign 签名的字段包括：appid、timestamp、noncestr、package 以及 appkey。
     * 这里 signType 并不参与签名微信的Package参数
     * @param params
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String getPackage(Map<String, String> params) throws UnsupportedEncodingException {
        String partnerKey = ConfKit.get("partnerKey");
        String partnerId = ConfKit.get("partnerId");
        String notifyUrl = ConfKit.get("notify_url");
        // 公共参数
        params.put("bank_type", "WX");
        params.put("attach", "yongle");
        params.put("partner", partnerId);
        params.put("notify_url", notifyUrl);
        params.put("input_charset", "UTF-8");
        return packageSign(params, partnerKey);
    }

    /**
     * 构造签名
     * @param params
     * @param encode
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String createSign(Map<String, String> params, boolean encode) throws UnsupportedEncodingException {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = value.toString();
            }
            if (encode) {
                temp.append(URLEncoder.encode(valueString, "UTF-8"));
            } else {
                temp.append(valueString);
            }
        }
        return temp.toString();
    }

    /**
     * 构造package, 这是我见到的最草蛋的加密，尼玛文档还有错
     * @param params
     * @param paternerKey
     * @return
     * @throws UnsupportedEncodingException 
     */
    private static String packageSign(Map<String, String> params, String paternerKey) throws UnsupportedEncodingException {
        String string1 = createSign(params, false);
        String stringSignTemp = string1 + "&key=" + paternerKey;
        String signValue = DigestUtils.md5Hex(stringSignTemp).toUpperCase();
        String string2 = createSign(params, true);
        return string2 + "&sign=" + signValue;
    }
    
    /**
     * 获得返回参数验证支付签名
     * @param params
     * @param paternerKey
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String getSign(Map<String, String> params) throws UnsupportedEncodingException {
        String string1 = createSign(params, false);
        String stringSignTemp = string1 + "&key=" + ConfKit.get("partnerKey");
        String signValue = DigestUtils.md5Hex(stringSignTemp).toUpperCase(); 
        return signValue;
    }
}
