package chora.auth.utils;

import chora.auth.model.Account;
import chora.auth.model.Device;
import chora.auth.repository.AccountRepository;
import chora.auth.service.AccountService;
import chora.auth.service.DeviceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeviceAwareTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {
    private final DeviceService deviceService;
    private final AccountRepository accountRepository;

    @Override
    public void customize(JwtEncodingContext context) {
        System.out.println("0980809809809");
        if (!OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
            return;
        }

        Map<String, Object> attrs = context.getAuthorization().getAttributes();
        String fingerprint = (String) attrs.get("device_fingerprint");
        String label = (String) attrs.get("device_label");

        if (fingerprint == null) return;

        String usernameOrEmail = context.getPrincipal().getName();
        Account account = accountRepository.findByEmail(usernameOrEmail)
                .orElseThrow(() -> new IllegalStateException("Account not found"));

        Device device = deviceService.linkDevice(account, label, fingerprint);

        attrs.put("device_id", device.getId().toString());
        context.getClaims().claim("device_id", device.getId().toString());
    }
}

