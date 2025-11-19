package chora.auth.service;

import chora.auth.model.Device;
import chora.auth.model.RefreshToken;
import chora.auth.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken issue(Device device, long ttlMillis, UUID jti) {
        RefreshToken token = new RefreshToken();
        token.setDevice(device);
        token.setJti(jti);
        token.setExpiresAt(new Timestamp(System.currentTimeMillis() + ttlMillis));
        token.setRevoked(false);
        return refreshTokenRepository.save(token);
    }

    public void revoke(UUID jti, String reason) {
        RefreshToken token = refreshTokenRepository.findByJti(jti).orElseThrow();
        token.setRevoked(true);
        token.setRevokedAt(new Timestamp(System.currentTimeMillis()));
        token.setReason(reason);
        refreshTokenRepository.save(token);
    }

    public RefreshToken findByJti(UUID jti) {
        return refreshTokenRepository.findByJti(jti).orElseThrow();
    }
}
