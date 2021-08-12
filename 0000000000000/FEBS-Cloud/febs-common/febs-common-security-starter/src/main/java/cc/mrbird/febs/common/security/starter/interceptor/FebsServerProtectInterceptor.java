package cc.mrbird.febs.common.security.starter.interceptor;

import cc.mrbird.febs.common.core.entity.FebsResponse;
import cc.mrbird.febs.common.core.entity.constant.FebsConstant;
import cc.mrbird.febs.common.core.utils.FebsUtil;
import cc.mrbird.febs.common.security.starter.properties.FebsCloudSecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MrBird
 */
public class FebsServerProtectInterceptor implements HandlerInterceptor {

    private FebsCloudSecurityProperties properties;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws IOException {
        if (!properties.getOnlyFetchByGateway()) {
            return true;
        }
        String token = request.getHeader(FebsConstant.GATEWAY_TOKEN_HEADER);
        String gatewayToken = new String(Base64Utils.encode(FebsConstant.GATEWAY_TOKEN_VALUE.getBytes()));
        if (StringUtils.equals(gatewayToken, token)) {
            return true;
        } else {
            FebsResponse febsResponse = new FebsResponse();
            FebsUtil.makeJsonResponse(response, HttpServletResponse.SC_FORBIDDEN, febsResponse.message("请通过网关获取资源"));
            return false;
        }
    }

    public void setProperties(FebsCloudSecurityProperties properties) {
        this.properties = properties;
    }
}
