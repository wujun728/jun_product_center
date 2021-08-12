package cc.mrbird.febs.common.redis.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author MrBird
 */
@ConfigurationProperties(prefix = "febs.lettuce.redis")
public class FebsLettuceRedisProperties {

    /**
     * 是否开启Lettuce Redis
     */
    private Boolean enable = true;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "FebsLettuceRedisProperties{" +
                "enable=" + enable +
                '}';
    }
}
