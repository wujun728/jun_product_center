package cc.mrbird.security.session;

import cc.mrbird.common.domain.FebsConstant;
import cc.mrbird.common.domain.ResponseBo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

/**
 * 处理 session过期
 * 导致 session 过期的原因有：
 * 1. 并发登录控制
 * 2. 被踢出
 */
public class FebsExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        event.getResponse().setContentType(FebsConstant.JSON_UTF8);
        event.getResponse().getWriter().write(mapper.writeValueAsString(ResponseBo.unAuthorized("登录已失效")));
    }

}
