package chora.auth.service;

import chora.auth.model.Account;
import chora.auth.model.Device;
import chora.auth.repository.DeviceRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.System.currentTimeMillis;

@Service
@AllArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;

    @Transactional
    public Device linkDevice(Account account, String label, String fingerprint) {
        System.out.println(account);
        System.out.println(label);
        System.out.println(fingerprint);

        return deviceRepository.findByAccountAndFingerprintHash(account, fingerprint)
                .orElseGet(() -> {
                    Device device = new Device()
                            .setAccount(account)
                            .setLabel(label)
                            .setFingerprintHash(fingerprint)
                            .setLinkedAt(new java.sql.Timestamp(System.currentTimeMillis()));
                    return deviceRepository.save(device);
                });
    }

    public List<Device> listDevices(UUID accountId) {
        return deviceRepository.findByAccountId(accountId);
    }
}
