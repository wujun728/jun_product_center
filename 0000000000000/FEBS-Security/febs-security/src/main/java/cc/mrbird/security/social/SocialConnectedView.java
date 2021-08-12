package cc.mrbird.security.social;

import cc.mrbird.common.domain.FebsConstant;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class SocialConnectedView extends AbstractView {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    @SuppressWarnings("unchecked")
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String view = "";
        List<Connection<?>> connections = (List<Connection<?>>) model.get("connections");
        if (!connections.isEmpty()) {
            Connection<?> connection = connections.get(0);
            sessionStrategy.setAttribute(new ServletWebRequest(request, response), FebsConstant.SESSION_KEY_SOCIAL_OPENID, connection.getKey().getProviderUserId());
            view = FebsConstant.SOCIAL_BIND_SUCCESS_URL;
        } else {
            view = FebsConstant.SOCIAL_UNBIND_SUCCESS_URL;
        }
        response.setContentType(FebsConstant.HTML_UTF8);
        response.sendRedirect(view);
    }
}
