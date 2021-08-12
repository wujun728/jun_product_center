package cc.mrbird.security.social.qq.connect;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

public class QQOAuth2Template extends OAuth2Template {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        // access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
        String result = this.getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        log.info("responseToken: {}", result);
        String[] params = StringUtils.splitByWholeSeparatorPreserveAllTokens(result, "&");

        String accessToken = StringUtils.substringAfterLast(params[0], "=");
        Long expiresIn = Long.valueOf(StringUtils.substringAfterLast(params[1], "="));
        String refreshToken = StringUtils.substringAfterLast(params[2], "=");
        return new AccessGrant(accessToken, null, refreshToken, expiresIn);
    }
}
