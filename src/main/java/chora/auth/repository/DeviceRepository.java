package chora.auth.repository;

import chora.auth.model.Account;
import chora.auth.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {
    List<Device> findByAccountId(UUID accountId);

    Optional<Device> findByAccountAndFingerprintHash(Account account, String fingerprint);
}
