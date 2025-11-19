package chora.auth.controller;

import chora.auth.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/oauth/revoke")
public class TokenRevocationController {
    private final RefreshTokenService refreshTokenService;

    @PostMapping
    public ResponseEntity<?> revoke(@RequestParam UUID jti, @RequestParam String reason) {
        refreshTokenService.revoke(jti, reason);
        return ResponseEntity.ok().build();
    }
}