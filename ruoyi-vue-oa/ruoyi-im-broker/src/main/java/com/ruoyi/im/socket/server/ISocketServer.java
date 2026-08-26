package com.ruoyi.im.socket.server;

/**
 * 服务器接口
 *
 * @author wocurr.com
 */
public interface ISocketServer {

    /**
     * 是否就绪
     * */
    boolean isReady();

    /**
     * 启动
     */
    void start();

    /**
     * 关闭
     */
    void shutdown();
}
