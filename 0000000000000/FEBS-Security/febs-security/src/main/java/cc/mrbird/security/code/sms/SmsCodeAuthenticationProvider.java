package cc.mrbird.security.code.sms;

import cc.mrbird.security.service.FebsUserDetailService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private FebsUserDetailService userDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        UserDetails userDetails = userDetailService.loadUserByUsername((String) authenticationToken.getPrincipal());

        if (userDetails == null)
            throw new InternalAuthenticationServiceException("没有该用户！");

        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public FebsUserDetailService getUserDetailService() {
        return userDetailService;
    }

    public void setUserDetailService(FebsUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

}
