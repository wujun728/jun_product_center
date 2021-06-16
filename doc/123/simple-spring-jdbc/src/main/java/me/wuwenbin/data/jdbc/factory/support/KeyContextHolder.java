package me.wuwenbin.data.jdbc.factory.support;

/**
 * 处理多数据源的key
 * Created by wuwenbin on 2017/5/4.
 */
public class KeyContextHolder {

    private static final ThreadLocal<String> keyHolder = new ThreadLocal<>();

    public static void setKey(String key) {
        keyHolder.set(key);
    }

    public static String getKey() {
        return keyHolder.get();
    }

    public static void clearKey() {
        keyHolder.remove();
    }
}
