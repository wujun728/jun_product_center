package com.ruoyi.seal.utils.pdf.param;

import lombok.Data;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.InputStream;
import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * 签章参数
 *
 * @author wocurr.com
 */
@Data
public class SignParam {
    /**
     * 签名的原因，显示在pdf签名属性中
     */
    private String reason;

    /**
     * 签名的地点，显示在pdf签名属性中
     */
    private String location;

    /**
     * 摘要算法名称，例如SHA-1
     */
    private String digestAlgorithm;

    /**
     * 图章
     */
    private InputStream sealImageIn;

    /**
     * 证书链
     */
    private Certificate[] chain;

    /**
     * 签名私钥
     */
    private PrivateKey pk;

    private BouncyCastleProvider provider;

    /**
     * 印章所在页码
     */
    private int sealPage;

    /**
     * 图章左下角x
     */
    private float rectllx;

    /**
     * 图章左下角y
     */
    private float rectlly;

    /**
     * 图章右上角x
     */
    private float recturx;

    /**
     * 图章右上角y
     */
    private float rectury;

}
