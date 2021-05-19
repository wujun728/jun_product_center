package com.platform.utils;

/**
 * 常量
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public class Constant {
    /**
     * 超级管理员ID
     */
    public static final String SUPER_ADMIN = "1";

    /**
     * 默认系统domainId
     */
    public static final String DOMAIN_ID = "735e3dd6bb4f4a9191a66761d6g73918";

    public static final String USE_DATA = "MYSQL";

    /**
     * 排序方式名称 asc:正序 | desc:倒序
     */
    public static final String SORT_ORDER = "sortOrder";
    /**
     * 当前登录用户
     */
    public static final String CURRENT_USER = "curUser";
    /**
     * 默认密码
     */
    public static final String DEFAULT_PASS_WORD = "888888";
    /**
     * 权限前缀
     */
    public static final String PERMS_LIST = "permsList";

    /**
     * 菜单类型
     *
     * @author lipengjun
     * @email 939961241@qq.com
     * @date 2016年11月15日 下午1:24:29
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     *
     * @author lipengjun
     * @email 939961241@qq.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        private CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
