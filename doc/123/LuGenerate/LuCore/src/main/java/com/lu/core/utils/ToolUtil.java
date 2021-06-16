package com.lu.core.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.*;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program LuBoot
 * @description:
 * @author: zhanglu
 * @create: 2019-10-08 17:34:00
 */
public class ToolUtil {

    /**
     * 默认密码盐长度
     */
    public static final int SALT_LENGTH = 6;

    /**
     * 获取随机字符,自定义长度
     *
     * @author fengshuonan
     * @Date 2018/3/18 21:55
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取随机字符,自定义长度
     *
     * @author fengshuonan
     * @Date 2018/3/18 21:55
     */
    public static String getRandomNum(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    /**
     * md5加密(加盐)
     *
     * @author fengshuonan
     * @Date 2018/3/18 21:56
     */
    public static String md5Hex(String password, String salt) throws Exception {
        return md5Hex(password + salt);
    }

    /**
     * md5加密(不加盐)
     *
     * @author fengshuonan
     * @Date 2018/3/18 21:56
     */
    public static String md5Hex(String str) throws Exception {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(str.getBytes());
            StringBuffer md5StrBuff = new StringBuffer();
            for (int i = 0; i < bs.length; i++) {
                if (Integer.toHexString(0xFF & bs[i]).length() == 1)
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & bs[i]));
                else
                    md5StrBuff.append(Integer.toHexString(0xFF & bs[i]));
            }
            return md5StrBuff.toString();
        } catch (Exception e) {
            throw new Exception("CoreExceptionEnum.ENCRYPT_ERROR");
        }
    }

    /**
     * 过滤掉掉字符串中的空白
     *
     * @author fengshuonan
     * @Date 2018/3/22 15:16
     */
    public static String removeWhiteSpace(String value) {
        if (isEmpty(value)) {
            return "";
        } else {
            return value.replaceAll("\\s*", "");
        }
    }

    /**
     * 获取某个时间间隔以前的时间 时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @author stylefeng
     * @Date 2018/5/8 22:05
     */
    public static String getCreateTimeBefore(int seconds) {
        long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
        Date date = new Date(currentTimeInMillis - seconds * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 获取异常的具体信息
     *
     * @author fengshuonan
     * @Date 2017/3/30 9:21
     * @version 2.0
     */
    public static String getExceptionMsg(Throwable e) {
        StringWriter sw = new StringWriter();
        try {
            e.printStackTrace(new PrintWriter(sw));
        } finally {
            try {
                sw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return sw.getBuffer().toString().replaceAll("\\$", "T");
    }

    /**
     * 获取ip地址
     *
     * @author fengshuonan
     * @Date 2018/5/15 下午6:36
     */
    public static String getIP() {
        try {
            StringBuilder IFCONFIG = new StringBuilder();
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
                        IFCONFIG.append(inetAddress.getHostAddress().toString() + "\n");
                    }

                }
            }
            return IFCONFIG.toString();

        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拷贝属性，为null的不拷贝
     *
     * @author fengshuonan
     * @Date 2018/7/25 下午4:41
     */
    public static void copyProperties(Object source, Object target) {
        BeanUtil.copyProperties(source, target, CopyOptions.create().setIgnoreNullValue(true));
    }

    /**
     * 判断是否是windows操作系统
     *
     * @author stylefeng
     * @Date 2017/5/24 22:34
     */
    public static Boolean isWinOs() {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取临时目录
     *
     * @author stylefeng
     * @Date 2017/5/24 22:35
     */
    public static String getTempPath() {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * 把一个数转化为int
     *
     * @author fengshuonan
     * @Date 2017/11/15 下午11:10
     */
    public static Integer toInt(Object val) {
        if (val instanceof Double) {
            BigDecimal bigDecimal = new BigDecimal((Double) val);
            return bigDecimal.intValue();
        } else {
            return Integer.valueOf(val.toString());
        }

    }

    /**
     * 是否为数字
     *
     * @author fengshuonan
     * @Date 2017/11/15 下午11:10
     */
    public static boolean isNum(Object obj) {
        try {
            Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(getWebRootPath("templates"));
    }

    /**
     * 获取项目路径
     *
     * @author fengshuonan
     * @Date 2017/11/15 下午11:10
     */
    public static String getWebRootPath(String filePath) {
        try {
            String path = ToolUtil.class.getClassLoader().getResource("").toURI().getPath();
            path = path.replace("/WEB-INF/classes/", "");
            path = path.replace("/target/classes/", "");
            path = path.replace("file:/", "");
            if (ToolUtil.isEmpty(filePath)) {
                return path;
            } else {
                return path + "/" + filePath;
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取当前Jar文件
     *   String path = LuManageApplication.class.getProtectionDomain().getCodeSource().getLocation().getFile();
     *
     * @return
     */
    private static File getFile(String path) {
        try {
            path = URLDecoder.decode(path, "UTF-8"); // 转换处理中文及空格
        }
        catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return new File(path);
    }

    /**
     * 获取jar绝对路径
     *
     * @return
     */
    public static String getJarPath(String path) {
        File file = getFile(path);
        if (file == null) {
            return null;
        }
        return file.getAbsolutePath();
    }

    /**
     * 获取文件后缀名 不包含点
     *
     * @author fengshuonan
     * @Date 2017/11/15 下午11:10
     */
    public static String getFileSuffix(String fileWholeName) {
        if (ToolUtil.isEmpty(fileWholeName)) {
            return "none";
        }
        int lastIndexOf = fileWholeName.lastIndexOf(".");
        return fileWholeName.substring(lastIndexOf + 1);
    }

    /**
     * 判断一个对象是否是时间类型
     *
     * @author stylefeng
     * @Date 2017/4/18 12:55
     */
    public static String dateType(Object o) {
        if (o instanceof Date) {
            return DateUtil.formatDate((Date) o);
        } else {
            return o.toString();
        }
    }

    /**
     * 当前时间
     *
     * @author stylefeng
     * @Date 2017/5/7 21:56
     */
    public static String currentTime() {
        return DateUtil.formatDateTime(new Date());
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        } else {
            if (o instanceof String) {
                if (o.toString().trim().equals("")) {
                    return true;
                }
            } else if (o instanceof List) {
                if (((List)o).size() == 0) {
                    return true;
                }
            } else if (o instanceof Map) {
                if (((Map)o).size() == 0) {
                    return true;
                }
            } else if (o instanceof Set) {
                if (((Set)o).size() == 0) {
                    return true;
                }
            } else if (o instanceof Object[]) {
                if (((Object[])((Object[])o)).length == 0) {
                    return true;
                }
            } else if (o instanceof int[]) {
                if (((int[])((int[])o)).length == 0) {
                    return true;
                }
            } else if (o instanceof long[] && ((long[])((long[])o)).length == 0) {
                return true;
            }

            return false;
        }
    }

    public static boolean isOneEmpty(Object... os){
        if(isEmpty(os)){
            return false;
        }
        for (Object o : os) {
            if(isEmpty(o)){
                return true;
            }
        }
        return false;
    }

    public static boolean isAllEmpty(Object... os){
        if(isEmpty(os)){
            return true;
        }
        for (Object o : os) {
            if(isNotEmpty(o)){
                return false;
            }
        }
        return true;
    }

}
