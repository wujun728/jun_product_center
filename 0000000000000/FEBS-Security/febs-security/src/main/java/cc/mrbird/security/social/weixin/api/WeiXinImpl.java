package cc.mrbird.security.social.weixin.api;

import cc.mrbird.common.domain.FebsConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

public class WeiXinImpl extends AbstractOAuth2ApiBinding implements WeiXin {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private ObjectMapper objectMapper = new ObjectMapper();


    public WeiXinImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }

    /**
     * 获取微信用户信息。
     */
    @Override
    public WeiXinUserInfo getUserInfo(String openId) {
        String url = FebsConstant.GET_WEIXIN_USER_INFO_URL + openId;
        String response = getRestTemplate().getForObject(url, String.class);
        if (StringUtils.contains(response, "errcode")) {
            return null;
        }
        WeiXinUserInfo weiXinUserInfo = null;
        try {
            log.info("微信用户信息：{}", response);
            weiXinUserInfo = objectMapper.readValue(response, WeiXinUserInfo.class);
        } catch (Exception e) {
            log.error("获取微信用户信息失败", e);
        }
        return weiXinUserInfo;
    }
}
