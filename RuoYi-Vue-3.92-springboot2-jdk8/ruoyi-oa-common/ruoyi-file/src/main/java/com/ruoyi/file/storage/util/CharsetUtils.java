package com.ruoyi.file.storage.util;

import com.ruoyi.common.exception.base.BaseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class CharsetUtils {

    public static byte[] convertTxtCharsetToGBK(byte[] bytes, String extendName) {
        if (Arrays.asList(FileStorageUtils.TXT_FILE).contains(extendName)) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            try {
                String str = new String(bytes, getFileCharsetName(byteArrayInputStream));
                return str.getBytes("GBK");
            } catch (IOException e) {
                throw new BaseException("操作失败");
            }
        }
        return bytes;
    }

    public static byte[] convertTxtCharsetToUTF8(byte[] bytes, String extendName) {

        if (Arrays.asList(FileStorageUtils.TXT_FILE).contains(extendName)) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            try {
                String str = new String(bytes, getFileCharsetName(byteArrayInputStream));
                return str.getBytes(StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new BaseException("操作失败");
            }
        }
        return bytes;
    }


    public static String getFileCharsetName(InputStream inputStream) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                bis.close();
                return charset; // 文件编码为 ANSI
            } else if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE"; // 文件编码为 Unicode
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE"; // 文件编码为 Unicode big endian
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8"; // 文件编码为 UTF-8
                checked = true;
            }
            bis.reset();
            if (!checked) {
                while ((read = bis.read()) != -1) {
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
                        // (0x80 - 0xBF),也可能在GB编码内
                        {
                        } else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) { // 也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                            }
                            break;
                        } else
                            break;
                    }
                }
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charset;

    }

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (isEncoding(str, encode)) { // 判断是不是GB2312
                return encode;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (isEncoding(str, encode)) { // 判断是不是ISO-8859-1
                return encode;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (isEncoding(str, encode)) { // 判断是不是UTF-8
                return encode;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (isEncoding(str, encode)) { // 判断是不是GBK
                return encode;
            }
        } catch (Exception exception3) {
        }
        return ""; // 如果都不是，说明输入的内容不属于常见的编码格式。
    }

    public static boolean isEncoding(String str, String encode) {
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

}
