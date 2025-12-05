package chora.auth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "devices")
@Getter
@Setter
@Accessors(chain = true)
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private String label;
    private String fingerprintHash;
    private Timestamp linkedAt;
}
