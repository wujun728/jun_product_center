package cc.mrbird.security.social;

import cc.mrbird.common.domain.FebsConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component("connect/status")
public class SocialConnectionStatusView extends AbstractView {

    @Autowired
    private ObjectMapper mapper;

    @Override
    @SuppressWarnings("unchecked")
    protected void renderMergedOutputModel(@Nullable Map<String, Object> map, @Nullable HttpServletRequest httpServletRequest, @Nullable HttpServletResponse httpServletResponse) throws Exception {
        Map<String, List<Connection<?>>> connection = (Map<String, List<Connection<?>>>) Objects.requireNonNull(map).get("connectionMap");
        Map<String, Boolean> result = new HashMap<>();
        connection.forEach((key, value) -> result.put(key, CollectionUtils.isNotEmpty(connection.get(key))));

        Objects.requireNonNull(httpServletResponse).setContentType(FebsConstant.JSON_UTF8);
        httpServletResponse.getWriter().write(mapper.writeValueAsString(result));

    }
}
