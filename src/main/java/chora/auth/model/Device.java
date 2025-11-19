package chora.auth.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String id;
}
