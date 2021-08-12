package cc.mrbird.security.exception;

import org.springframework.security.core.AuthenticationException;

public class FebsCredentialExcetion extends AuthenticationException {

    private static final long serialVersionUID = -920087729589688230L;

    public FebsCredentialExcetion(String message) {
        super(message);
    }
}
