package chora.auth.config;

import chora.auth.model.RefreshToken;
import chora.auth.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@AllArgsConstructor
public class RefreshTokenValidator implements OAuth2TokenValidator<Jwt> {
    private final RefreshTokenService refreshTokenService;

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        UUID jti = UUID.fromString(token.getClaim("jti"));
        RefreshToken refreshToken = refreshTokenService.findByJti(jti);

        if (refreshToken.isRevoked() || refreshToken.getExpiresAt().toInstant().isBefore(Instant.now())) {
            return OAuth2TokenValidatorResult.failure(new OAuth2Error("invalid_token", "Token revoked or expired", null));
        }

        return OAuth2TokenValidatorResult.success();
    }
}
