package cc.mrbird.febs.gateway.enhance.auth;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author MrBird
 */
@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtTokenHelper tokenHelper;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        String username;
        try {
            username = tokenHelper.getUsernameFromToken(token);
        } catch (Exception e) {
            username = null;
        }
        if (StringUtils.isNotBlank(username) && tokenHelper.validateToken(token)) {
            Claims claims = tokenHelper.getAllClaimsFromToken(token);
            String permissions = claims.get("permission", String.class);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions)
            );
            return Mono.just(auth);
        } else {
            return Mono.empty();
        }
    }
}
