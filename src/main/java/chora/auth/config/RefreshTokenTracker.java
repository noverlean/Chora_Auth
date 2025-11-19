package chora.auth.config;

import chora.auth.model.Account;
import chora.auth.model.Device;
import chora.auth.service.DeviceService;
import chora.auth.service.RefreshTokenService;
import chora.auth.utils.DeviceAwarePrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.UUID;

@Component
@AllArgsConstructor
public class RefreshTokenTracker implements OAuth2TokenCustomizer<JwtEncodingContext> {
    private final DeviceService deviceService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public void customize(JwtEncodingContext context) {
        if (OAuth2TokenType.REFRESH_TOKEN.equals(context.getTokenType())) {
            DeviceAwarePrincipal principal = (DeviceAwarePrincipal) context.getPrincipal().getPrincipal();
            String fingerprint = principal.getFingerprint();
            String label = principal.getLabel();
            UUID accountId = UUID.fromString(principal.getName());

            Account account = new Account().setId(accountId);

            Device device = deviceService.linkDevice(account, label, fingerprint);

            UUID jti = UUID.randomUUID();
            context.getClaims().claim("jti", jti.toString());
            context.getClaims().claim("device_id", device.getId().toString());

            refreshTokenService.issue(device, Duration.ofDays(30).toMillis(), jti);
        }
    }
}
