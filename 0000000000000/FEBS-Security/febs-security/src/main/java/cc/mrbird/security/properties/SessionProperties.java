package cc.mrbird.security.properties;

public class SessionProperties {

    // 最大并发登录数量，默认值为-1，表示无限制
    private Integer maximumSessions = -1;

    public Integer getMaximumSessions() {
        return maximumSessions;
    }

    public void setMaximumSessions(Integer maximumSessions) {
        this.maximumSessions = maximumSessions;
    }

}
