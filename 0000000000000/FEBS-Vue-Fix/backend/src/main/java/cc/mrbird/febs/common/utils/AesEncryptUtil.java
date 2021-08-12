package cc.mrbird.febs.common.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesEncryptUtil {

    /**
     * 保持前后端的KEY和IV一致
     */
    private static final String KEY = "4Dd2Bb3Cc1Aa5Ee0";
    private static final String IV = "4Dd2Bb3Cc1Aa5Ee0";

    /**
     * 加密方法
     * @param data
     * @param key
     * @param iv
     * @return
     */
    public static String encrypt(String data, String key, String iv){
        try {
            // 参数：算法/模式/补码方式
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return new Base64().encodeToString(encrypted);

        } catch (Exception e){
            e.printStackTrace();
            return null;

        }

    }

    /**
     * 解密方法
     * @param data
     * @param key
     * @param iv
     * @return
     */
    public static String desEncrypt(String data, String key, String iv) {
        try {
            byte[] encrypted1 = new Base64().decode(filter(data));

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString.trim();

        } catch (Exception e){
            e.printStackTrace();
            return null;

        }

    }

    public static String encrypt(String data) {
        return encrypt(data, KEY, IV);
    }

    public static String desEncrypt(String data) {
        return desEncrypt(data, KEY, IV);
    }

    public static String filter(String value) {
        value = value.replace("%23", "#")
                .replace("%25", "%")
                .replace("%26", "&")
                .replace("%2B", "+")
                .replace("%2F", "//")
                .replace("%3F", "?");
        return value;
    }

}
