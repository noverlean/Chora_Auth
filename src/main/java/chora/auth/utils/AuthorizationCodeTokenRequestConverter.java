package chora.auth.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationConverter;

import java.util.HashMap;
import java.util.Map;

public class AuthorizationCodeTokenRequestConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {
        String grantType = request.getParameter("grant_type");
        if (!"authorization_code".equals(grantType)) {
            return null;
        }

        String code = request.getParameter("code");
        String redirectUri = request.getParameter("redirect_uri");
        String codeVerifier = request.getParameter("code_verifier");

        if (code == null || redirectUri == null) {
            throw new OAuth2AuthenticationException(
                    new OAuth2Error("invalid_request", "Missing code or redirect_uri", null)
            );
        }

        Map<String, Object> additionalParameters = new HashMap<>();
        if (codeVerifier != null) {
            additionalParameters.put("code_verifier", codeVerifier);
        }

        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();

        return new OAuth2AuthorizationCodeAuthenticationToken(
                code,
                clientPrincipal,   // теперь не null
                redirectUri,
                additionalParameters
        );
    }
}