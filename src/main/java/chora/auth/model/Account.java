package chora.auth.models;

import jakarta.persistence.*;

@Entity
@Table(name = "devices")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String id;

    @Column
    private String email;
}
