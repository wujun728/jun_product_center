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
public class RouteLog {

    @Id
    private String id;
    /**
     * 请求IP
     */
    private String ip;
    /**
     * 请求URI
     */
    private String requestUri;
    /**
     * 目标URI
     */
    private String targetUri;
    /**
     * 请求方法
     */
    private String requestMethod;
    /**
     * 目标服务
     */
    private String targetServer;
    /**
     * 请求时间点
     */
    private String createTime;
    /**
     * 请求地点
     */
    private String location;

    @Transient
    private String createTimeFrom;
    @Transient
    private String createTimeTo;
}
