package com.ruoyi.im.socket.processor;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.im.socket.config.RedisExtendTemplate;
import com.ruoyi.im.socket.constant.ChannelAttrKey;
import com.ruoyi.im.socket.constant.SocketConstant;
import com.ruoyi.im.socket.constant.SocketRedisKey;
import com.ruoyi.im.socket.enums.CmdTypeEnum;
import com.ruoyi.im.socket.helper.UserChannelCtxMap;
import com.ruoyi.im.socket.model.SendInfo;
import com.ruoyi.im.socket.server.SocketServerGroup;
import com.ruoyi.im.socket.utils.RedisCache;
import com.ruoyi.im.socket.utils.RedisLock;
import io.jsonwebtoken.Claims;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 登录消息处理
 *
 * @author wocurr.com
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoginProcessor{

    private final RedisExtendTemplate redisExtendTemplate;
    private final RedisCache redisCache;
    private final RedisLock redisLock;

    /**
     * 登录用户 redis key
     */
    private static final String LOGIN_TOKEN_KEY = "login_tokens:";
    /**
     * 令牌前缀
     */
    private static final String LOGIN_USER_KEY = "login_user_key";
    private static final String SOCKET_USER_LOCK_KEY = "socket:user:lock:";

    public void process(ChannelHandlerContext ctx, Claims claims) throws IOException, InterruptedException {
        String token = (String) claims.get(LOGIN_USER_KEY);
        String tokenKey = getTokenKey(token);
        LoginUser loginUser = redisCache.getCacheObject(tokenKey);
        String userId = loginUser.getUser().getUserId();
        redisLock.doLock(SOCKET_USER_LOCK_KEY + userId, 10, TimeUnit.SECONDS, () -> {
            ChannelHandlerContext context = UserChannelCtxMap.getChannelCtx(userId, token);
            if (context != null && !ctx.channel().id().equals(context.channel().id())) {
                // 不允许多地登录,强制下线
                SendInfo<Object> sendInfo = new SendInfo<>();
                sendInfo.setCmd(CmdTypeEnum.FORCE_LOGOUT.code());
                sendInfo.setData("您已在其他地方登陆，将被强制下线");
                context.channel().writeAndFlush(sendInfo);
                log.error("异地登录，强制下线，userId:{}", userId);
            }
            // 绑定用户和channel
            UserChannelCtxMap.addChannelCtx(userId, token, ctx);
            // 设置用户id属性
            AttributeKey<String> userIdAttr = AttributeKey.valueOf(ChannelAttrKey.USER_ID);
            ctx.channel().attr(userIdAttr).set(userId);
            // 设置用户token
            AttributeKey<String> tokenAttr = AttributeKey.valueOf(ChannelAttrKey.USER_TOKEN);
            ctx.channel().attr(tokenAttr).set(token);
            // 初始化心跳次数
            AttributeKey<Long> heartBeatAttr = AttributeKey.valueOf(ChannelAttrKey.HEARTBEAT_TIMES);
            ctx.channel().attr(heartBeatAttr).set(0L);
            // 在redis上记录每个user的channelId，15秒没有心跳，则自动过期
            String key = String.join(":", SocketRedisKey.IM_USER_SERVER_ID, userId, token);
            redisExtendTemplate.opsForValue().set(key, SocketServerGroup.serverId, SocketConstant.ONLINE_TIMEOUT_SECOND, TimeUnit.SECONDS);
        });
    }

    /**
     * 获取tokenKey
     *
     * @param token
     * @return
     */
    private String getTokenKey(String token)
    {
        return LOGIN_TOKEN_KEY + token;
    }
}
