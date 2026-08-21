package com.ruoyi.im.socket.server;

import com.ruoyi.im.socket.codec.MessageProtocolDecoder;
import com.ruoyi.im.socket.codec.MessageProtocolEncoder;
import com.ruoyi.im.socket.config.JwtConfig;
import com.ruoyi.im.socket.handler.AuthHandler;
import com.ruoyi.im.socket.handler.SocketChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p> Socket 服务器 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
public class SocketServer implements ISocketServer {

    private final int port;
    private final String uri;
    private boolean readyStatus = false;
    private final JwtConfig jwtConfig;

    /**
     * 服务端线程组
     */
    private NioEventLoopGroup bossGroup;
    /**
     * 服务端工作线程组
     */
    private NioEventLoopGroup workerGroup;

    public SocketServer(int port, String uri, JwtConfig jwtConfig) {
        this.port = port;
        this.uri = uri;
        this.jwtConfig = jwtConfig;
    }

    public boolean isReady() {
        return readyStatus;
    }

    public void start() {
        bossGroup = new NioEventLoopGroup(2);
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 5) // backlog表示主线程池中在套接口排队的最大数量，队列由未连接队列（三次握手未完成的）和已连接队列
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //保持长连接
                    .childOption(ChannelOption.WRITE_BUFFER_WATER_MARK, new WriteBufferWaterMark(32 * 1024, 64 * 1024)) //设置高低水位，控制发送netty缓冲区的大小
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new IdleStateHandler(60, 0, 0, TimeUnit.SECONDS)); //空闲心跳
                            ch.pipeline().addLast(new HttpServerCodec()); //编解码
                            ch.pipeline().addLast(new HttpObjectAggregator(65535)); //请求内容
                            ch.pipeline().addLast(new ChunkedWriteHandler()); //按块处理
                            ch.pipeline().addLast(new AuthHandler(jwtConfig.getSecret(), uri)); //jwt鉴权
                            ch.pipeline().addLast(new WebSocketServerProtocolHandler(uri));
                            ch.pipeline().addLast(new MessageProtocolEncoder());
                            ch.pipeline().addLast(new MessageProtocolDecoder());
                            ch.pipeline().addLast(new SocketChannelHandler());
                        }
                    });

            // 绑定端口，启动select线程，轮询监听channel事件，监听到事件之后就会交给工作线程池处理
            b.bind(port).sync().channel();
            this.readyStatus = true;
            log.info("websocket server 初始化完成,端口：{}", port);
        } catch (Exception e) {
            log.info("websocket server 初始化异常", e);
        }
    }

    @Override
    public void shutdown() {
        if (bossGroup != null && !bossGroup.isShuttingDown() && !bossGroup.isShutdown()) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null && !workerGroup.isShuttingDown() && !workerGroup.isShutdown()) {
            workerGroup.shutdownGracefully();
        }
        this.readyStatus = false;
        log.info("websocket server 停止");
    }
}
