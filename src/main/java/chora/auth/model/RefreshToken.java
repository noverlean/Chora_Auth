package chora.auth.model;

import jakarta.persistence.*;
import jdk.jfr.SettingDefinition;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "refresh_tokens")
@Setter
@Getter
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @Column
    private UUID jti;

    @Column
    private Timestamp expiresAt;

    @Column(nullable = false)
    private boolean revoked = false;

    @Column
    private Timestamp revokedAt;

    @Column
    private String reason;

}
