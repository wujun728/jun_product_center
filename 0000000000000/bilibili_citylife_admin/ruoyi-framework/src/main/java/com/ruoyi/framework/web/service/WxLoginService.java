package com.ruoyi.framework.web.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.AddressUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 *
 * @author ruoyi
 */
@Component
public class WxLoginService
{
    // 令牌自定义标识
    private final String header = "Authorization";
    
    // 令牌秘钥
    private final String secret = "zhuawashi";
    
    // 令牌有效期（默认30分钟）
    private final int expireTime = 200;

    protected static final Long MILLIS_SECOND = 1000L;

    protected static final Long MILLIS_MINUTE = 60 * 1000L;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;
    
    private static final String wx_prefix = "wx";

    @Autowired
    private RedisCache redisCache;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public WxUser getWxUser(String token)
    {
       
        if (StringUtils.isNotEmpty(token))
        {
            Claims claims = parseToken(token);
            // 解析对应的权限以及用户信息
            String uuid = (String) claims.get(wx_prefix);
            String userKey = getTokenKey(uuid);
            WxUser wxUser = redisCache.getCacheObject(userKey);//wx_token:xxxx-xxxx-xxxx
            return wxUser;
        }
        return null;
    }

    /**
     * 设置用户身份信息
     */
    public void setWxUser(WxUser wxUser)
    {
        if (StringUtils.isNotNull(wxUser) && StringUtils.isNotEmpty(wxUser.getUuid()))
        {
            refreshToken(wxUser);
        }
    }

    /**
     * 删除用户身份信息
     */
    public void delWxUser(String token)
    {
        if (StringUtils.isNotEmpty(token))
        {
            Claims claims = parseToken(token);
            String uuid = (String) claims.get(wx_prefix);
            String userKey = getTokenKey(uuid);//wx_token:xxxx-xxxx-xxxx-xxxx
            redisCache.deleteObject(userKey);
        }
    }

    /**
     * 创建令牌
     *
     * @param wxUser 用户信息
     * @return 令牌
     */
    public String createToken(WxUser wxUser)
    {
        String uuid = IdUtils.fastUUID();
        wxUser.setUuid(uuid);
        setUserAgent(wxUser);
        refreshToken(wxUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put(wx_prefix, uuid);//wx : xxxx-xxxx
        return createToken(claims);
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param wxUser
     * @return 令牌
     */
    public void verifyToken(WxUser wxUser)
    {
        long expireTime = wxUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN)
        {
            refreshToken(wxUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param wxUser 登录信息
     */
    public void refreshToken(WxUser wxUser)
    {
        wxUser.setLoginTime(System.currentTimeMillis());
        wxUser.setExpireTime(wxUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将wxUser缓存
        String userKey = getTokenKey(wxUser.getUuid());
        redisCache.setCacheObject(userKey, wxUser, expireTime, TimeUnit.MINUTES);
    }

    /**
     * 设置用户代理信息
     *
     * @param wxUser 登录信息
     */
    public void setUserAgent(WxUser wxUser)
    {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        wxUser.setIpaddr(ip);
        wxUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        wxUser.setBrowser(userAgent.getBrowser().getName());
        wxUser.setOs(userAgent.getOperatingSystem().getName());
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims)
    {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }


    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    public String getToken(HttpServletRequest request)//wx exykskamasama.amamaamama
    {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(wx_prefix)) {
            token = token.replace(wx_prefix, "");
            return token;
        } else {
            return null;
        }
    }

    private String getTokenKey(String uuid)
    {
        return "wx_token:" + uuid;
    }
}
