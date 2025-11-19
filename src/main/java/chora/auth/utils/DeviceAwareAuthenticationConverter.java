package chora.auth.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import jakarta.servlet.http.HttpServletRequest;

public class DeviceAwareAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fingerprint = request.getParameter("device_fingerprint");
        String label = request.getParameter("device_label");

        DeviceAwarePrincipal principal = new DeviceAwarePrincipal(username, fingerprint, label);
        return new UsernamePasswordAuthenticationToken(principal, password);
    }
}


