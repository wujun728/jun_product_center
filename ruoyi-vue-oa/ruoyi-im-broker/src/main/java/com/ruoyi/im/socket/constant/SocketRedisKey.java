package com.ruoyi.im.socket.constant;

/**
 * redis key
 *
 * @author wocurr.com
 */
public final class SocketRedisKey {

    /**
     * server最大id,从0开始递增
     */
    public static final String  IM_MAX_SERVER_ID = "im:max_server_id";
    /**
     * 用户ID所连接的server的ID
     */
    public static final String  IM_USER_SERVER_ID = "im:user:server_id";
    /**
     * 登录用户 token
     */
    public static final String USER_TOKEN_KEY = "user_tokens:";
}
