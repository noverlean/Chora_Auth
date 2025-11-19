package chora.auth.service;

import chora.auth.model.Account;
import chora.auth.model.Device;
import chora.auth.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static java.lang.System.currentTimeMillis;

@Service
public class DeviceService {
    private final DeviceRepository repo;

    public DeviceService(DeviceRepository repo) {
        this.repo = repo;
    }

    public Device linkDevice(Account account, String label, String fingerprintHash) {
        Device device = new Device()
                .setAccount(account)
                .setLabel(label)
                .setFingerprintHash(fingerprintHash)
                .setLinked_at(new Timestamp(currentTimeMillis()));
        return repo.save(device);
    }

    public List<Device> listDevices(UUID accountId) {
        return repo.findByAccountId(accountId);
    }
}
