package cc.mrbird.common.shiro;

public class ShiroProperties {

    // shiro redis缓存时长，默认值 1800 秒
    private int expireIn = 1800;
    // session 超时时间，默认 1800000毫秒
    private long sessionTimeout = 1800000L;
    // rememberMe 有效时长，默认为 86400 秒，即一天
    private int cookieTimeout = 86400;

    private String anonUrl;

    private String loginUrl = "/login";

    private String successUrl = "/index";

    private String logoutUrl = "/logout";

    private String unauthorizedUrl;

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public long getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(long sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public int getCookieTimeout() {
        return cookieTimeout;
    }

    public void setCookieTimeout(int cookieTimeout) {
        this.cookieTimeout = cookieTimeout;
    }

    public String getAnonUrl() {
        return anonUrl;
    }

    public void setAnonUrl(String anonUrl) {
        this.anonUrl = anonUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }
}
