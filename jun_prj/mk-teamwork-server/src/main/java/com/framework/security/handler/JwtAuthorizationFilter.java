package com.framework.security.handler;

import cn.hutool.core.util.StrUtil;
import com.framework.common.ResultCode;
import com.framework.common.ResultJson;
import com.framework.common.constant.Constants;
import com.framework.common.utils.ServletUtils;
import com.framework.common.utils.StringUtils;
import com.framework.security.util.RedisCache;
import com.framework.security.util.TokenUtil;
import com.projectm.login.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @version V1.0
 * @program: teamwork
 * @package: com.framework.security
 * @description: Token解析
 * @author: lzd
 * @create: 2020-06-25 09:52
 **/
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Value("${custom.ignored-role-path}")
    private String[] strings;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorization = request.getHeader(Constants.TOKEN);
        String orgCode = request.getHeader(Constants.ORGCODE);
        String url = request.getRequestURI();
        String requestUrl = url.substring(1).toLowerCase();
        List<String> urlList = Arrays.asList(strings);
        //对特定的url放行
        if (urlList.contains(url)) {
            chain.doFilter(request, response);
            return;
        }
        // 请求头中有token，则进行解析，并且设置认证信息
        String token = authorization.replace(Constants.TOKEN_PREFIX, "");
        String userCode = tokenUtil.getUserCode(token);
        if (StringUtils.isNotEmpty(userCode)) {
            LoginUser loginUser = redisCache.getCacheObject(Constants.LOGIN_USER_KEY + userCode);
            //鉴权
            AtomicBoolean contains = new AtomicBoolean(false);
            loginUser.getUser().getMemberAccountList().forEach(memberAccount -> {
                if (StrUtil.isNotEmpty(orgCode)) {
                    if (Objects.equals(memberAccount.getOrganization_code(), orgCode)) {
                        contains.set(memberAccount.getNodeList().contains(requestUrl));
                    }
                } else {
                    contains.set(memberAccount.getNodeList().contains(requestUrl));
                }

            });
            if (contains.get()) {
            //if (true) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
            } else {
                ServletUtils.renderString(response, ResultJson.failure(ResultCode.FORBIDDEN).toString());
            }
        } else {
            ServletUtils.renderString(response, ResultJson.failure(ResultCode.VERIFY_TOKEN_FAIL).toString());
        }
    }
}
