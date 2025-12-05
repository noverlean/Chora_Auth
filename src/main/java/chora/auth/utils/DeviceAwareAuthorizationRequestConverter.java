package chora.auth.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2AuthorizationCodeRequestAuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationConverter;

import java.util.*;

public class DeviceAwareAuthorizationRequestConverter implements AuthenticationConverter {
    private final AuthenticationConverter delegate = new OAuth2AuthorizationCodeRequestAuthenticationConverter();

    @Override
    public Authentication convert(HttpServletRequest request) {
        Authentication auth = delegate.convert(request);
        if (!(auth instanceof OAuth2AuthorizationCodeRequestAuthenticationToken token)) {
            return auth;
        }



        Map<String, Object> additionalParameters = new HashMap<>(token.getAdditionalParameters());
        String fingerprint = request.getParameter("device_fingerprint");
        String label = request.getParameter("device_label");
        if (fingerprint != null) {
            additionalParameters.put("device_fingerprint", fingerprint);
        }
        if (label != null) {
            additionalParameters.put("device_label", label);
        }

        System.out.println("!!!!!!!!!!!!!");
        System.out.println(fingerprint);
        System.out.println(label);

        String authorizationUri = token.getAuthorizationUri();
        String clientId = token.getClientId();
        Authentication principal = (Authentication) token.getPrincipal();
        String redirectUri = token.getRedirectUri();
        String state = token.getState();
        Set<String> scopes = token.getScopes() != null ? token.getScopes() : Collections.emptySet();

        return new OAuth2AuthorizationCodeRequestAuthenticationToken(
                authorizationUri,
                clientId,
                principal,
                redirectUri,
                state,
                scopes,
                additionalParameters
        );
    }
}

