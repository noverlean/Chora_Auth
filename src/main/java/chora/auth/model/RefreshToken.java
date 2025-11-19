package chora.auth.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long deviceId;

    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private String jti;

    @Column
    private Timestamp expiresAt;

    @Column
    private Timestamp rotatedFrom;

    @Column
    private Timestamp revokedAt;

    @Column
    private String reason;
}
