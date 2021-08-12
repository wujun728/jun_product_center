package cc.mrbird.febs.common.core.entity.constant;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author MrBird
 */
public interface SocialConstant {

    String SOCIAL_LOGIN = "social_login";
    ThreadLocal<String> PASSWORD_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取随机生成的密码
     *
     * @return String 密码
     */
    static String getSocialLoginPassword() {
        String password = PASSWORD_THREAD_LOCAL.get();
        PASSWORD_THREAD_LOCAL.remove();
        return password;
    }

    /**
     * 设置随机生成的密码
     *
     * @return String 密码
     */
    static String setSocialLoginPassword() {
        String randomPassword = RandomStringUtils.randomAlphanumeric(64);
        PASSWORD_THREAD_LOCAL.set(randomPassword);
        return randomPassword;
    }
}
