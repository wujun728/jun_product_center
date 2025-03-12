//package com.gitthub.wujun728.engine.util;
//
//import java.util.UUID;
//public class IdUtil {
//    private static int counter=0;
//    /**
//     * 生成不带"-"的15位uuid
//     *
//     * @return 不带"-"的15位uuid
//     */
//    public static String generateUUID() {
//        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 15);
//    }
//
//    /**
//     * 生成指定长度的不带”-“的uuid
//     *
//     * @param len 长度
//     * @return 指定长度的不带”-“的uuid
//     */
//    public static String generateUUID(int len) {
//        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, len);
//    }
//
//    /**
//     * 从某个位置开始生成指定长度的不带”-“的uuid。<br>
//     * {@code start}和{@code start+len}小于36的参数为合法参数。
//     *
//     * @param start        开始位置
//     * @param len          长度
//     * @param excludeFirst 是否将下标为{@code start}的字符算进长度中。举例：字符串{@code abcdefg}，{@code start}为2，{@code len}为3，{@code excludeFirst}为true，<br>
//     *                     返回{@code 'cde'},如果{@code excludeFirst}为false，返回{@code 'cdef'}
//     * @return 合法参数返回UUID，不合法参数返回空字符串。
//     */
//    public static String generateUUID(int start, int len, boolean excludeFirst) {
//        if (start < 36 && start + len < 36) {
//            if (excludeFirst) {
//                return UUID.randomUUID().toString().replaceAll("-", "").substring(start, start + len);
//            } else {
//                return UUID.randomUUID().toString().replaceAll("-", "").substring(start, start + len + 1);
//            }
//        } else {
//            return "";
//        }
//    }
//
//
//
//    /**
//     * 生成带有”-“的UUID（15位）
//     *
//     * @return 带有”-“的UUID
//     */
//    public static String generateUUIDWithHyphen() {
//        return UUID.randomUUID().toString().substring(0, 15);
//    }
//
//    /**
//     * 生成指定长度的带有“-”的UUID
//     * @param len 字符串长度
//     * @return 指定长度的带有“-”的UUID
//     */
//    public static String generateUUIDWithHyphen(int len) {
//        return UUID.randomUUID().toString().substring(0, len);
//    }
//
//    /**
//     * 生成指定长度的带有“-”的
//     * @param start 开始位置
//     * @param len 字符串长度
//     * @param excludeFirst 计算字符串长度时是否包含开始位置处的字符
//     * @return 指定长度带有"-"的UUID
//     */
//    public static String generateUUIDWithHyphen(int start, int len, boolean excludeFirst) {
//        if (start < 36 && start + len < 36) {
//            if (excludeFirst) {
//                return UUID.randomUUID().toString().substring(start, start + len);
//            } else {
//                return UUID.randomUUID().toString().substring(start, start + len + 1);
//            }
//        } else {
//            return "";
//        }
//    }
//}
//
