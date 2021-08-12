package cc.mrbird.febs.gateway.enhance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author MrBird
 */
@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class BlockLog {

    @Id
    private String id;
    /**
     * 被拦截请求IP
     */
    private String ip;
    /**
     * 被拦截请求URI
     */
    private String requestUri;
    /**
     * 被拦截请求方法
     */
    private String requestMethod;
    /**
     * IP对应地址
     */
    private String location;
    /**
     * 拦截时间点
     */
    private String createTime;

    @Transient
    private String createTimeFrom;
    @Transient
    private String createTimeTo;
}
