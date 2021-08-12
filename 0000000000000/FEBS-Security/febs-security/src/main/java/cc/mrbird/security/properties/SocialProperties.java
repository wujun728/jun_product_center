package cc.mrbird.security.properties;


public class SocialProperties {

    private String filterProcessesUrl = "/auth";

    private String socialRedirectUrl = "";

    private String socialBindUrl = "";

    private String socialRegistUrl = "";

    private QQProperties qq = new QQProperties();

    private WeiXinProperties weixin = new WeiXinProperties();

    public QQProperties getQQ() {
        return qq;
    }

    public void setQQ(QQProperties qq) {
        this.qq = qq;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    public String getSocialRedirectUrl() {
        return socialRedirectUrl;
    }

    public void setSocialRedirectUrl(String socialRedirectUrl) {
        this.socialRedirectUrl = socialRedirectUrl;
    }

    public String getSocialBindUrl() {
        return socialBindUrl;
    }

    public void setSocialBindUrl(String socialBindUrl) {
        this.socialBindUrl = socialBindUrl;
    }

    public String getSocialRegistUrl() {
        return socialRegistUrl;
    }

    public void setSocialRegistUrl(String socialRegistUrl) {
        this.socialRegistUrl = socialRegistUrl;
    }

    public WeiXinProperties getWeixin() {
        return weixin;
    }

    public void setWeixin(WeiXinProperties weixin) {
        this.weixin = weixin;
    }
}
