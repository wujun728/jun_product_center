package com.ruoyi.im.socket.handler;

import com.ruoyi.im.socket.processor.LoginProcessor;
import com.ruoyi.im.socket.utils.JwtUtil;
import com.ruoyi.im.socket.utils.SpringContextHolder;
import io.jsonwebtoken.Claims;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class AuthHandler extends ChannelInboundHandlerAdapter {
    private final String secret;
    private final String uri;

    public static final String AUTH_TOKEN = "Authorization";
    private static final String UNAUTHORIZED = "Unauthorized";
    private static final String CONTENT_TYPE = "text/plain; charset=UTF-8";
    private static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    public AuthHandler(String secret, String uri) {
        this.secret = secret;
        this.uri = uri;
    }

    /**
     * 处理 HTTP 请求，检查鉴权参数
     *
     * @param ctx 上下文
     * @param msg 消息
     * @throws Exception 异常
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            log.info("[AuthHandler] 收到握手请求，channelId:{}, uri:{}", ctx.channel().id().asLongText(), ((FullHttpRequest)msg).uri());
            FullHttpRequest req = (FullHttpRequest) msg;
            QueryStringDecoder decoder = new QueryStringDecoder(req.uri());
            Map<String, List<String>> params = decoder.parameters();
            List<String> tokens = params.get(AUTH_TOKEN);
            log.info("[AuthHandler] 解析到 Authorization 参数: {}", tokens);
            if (CollectionUtils.isEmpty(tokens)) {
                tokens = params.get("token");
                log.info("[AuthHandler] 解析到 token 参数: {}", tokens);
            }
            if (CollectionUtils.isEmpty(tokens)) {
                log.error("[AuthHandler] 未获取到有效 token，拒绝连接，channelId:{}", ctx.channel().id().asLongText());
                returnResponse(ctx, HttpResponseStatus.UNAUTHORIZED, UNAUTHORIZED);
                return;
            }
            Claims claims = JwtUtil.parseToken(tokens.get(0), secret);
            log.info("[AuthHandler] JWT解析结果: {}", claims);
            if (Objects.isNull(claims)) {
                log.error("[AuthHandler] token解析失败，拒绝连接，channelId:{}", ctx.channel().id().asLongText());
                returnResponse(ctx, HttpResponseStatus.UNAUTHORIZED, UNAUTHORIZED);
                return;
            }
            LoginProcessor loginProcessor = SpringContextHolder.getBean(LoginProcessor.class);
            if (Objects.isNull(loginProcessor)) {
                log.error("[AuthHandler] LoginProcessor获取失败，拒绝连接，channelId:{}", ctx.channel().id().asLongText());
                returnResponse(ctx, HttpResponseStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR);
                return;
            }
            loginProcessor.process(ctx, claims);
            // 重置url，放行
            ((FullHttpRequest) msg).setUri(uri);
            ctx.fireChannelRead(msg);
            log.info("[AuthHandler] 鉴权成功，握手消息已透传，channelId:{}", ctx.channel().id().asLongText());
            return;
        }
        ctx.fireChannelRead(msg);
    }

    /**
     * 返回响应
     *
     * @param ctx ChannelHandlerContext 上下文
     * @param status HTTP 响应状态
     * @param message 响应消息
     */
    private void returnResponse(ChannelHandlerContext ctx, HttpResponseStatus status, String message) {
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, status,
                Unpooled.copiedBuffer(message, CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, CONTENT_TYPE);
        ctx.writeAndFlush(response);
        ctx.close();
    }
}
