package chora.auth.controller;

import chora.auth.model.Account;
import chora.auth.model.Device;
import chora.auth.service.AccountService;
import chora.auth.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService deviceService;
    private final AccountService accountService;

    @PostMapping("/link")
    public Device link(@RequestParam UUID accountId,
                       @RequestParam String label,
                       @RequestParam String fingerprintHash) {
        Account account = accountService.findById(accountId);
        return deviceService.linkDevice(account, label, fingerprintHash);
    }

    @GetMapping("/{accountId}")
    public List<Device> list(@PathVariable UUID accountId) {
        return deviceService.listDevices(accountId);
    }
}
