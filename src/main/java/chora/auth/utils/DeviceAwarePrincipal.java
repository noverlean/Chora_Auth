package chora.auth.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.security.Principal;

@Getter
@AllArgsConstructor
public class DeviceAwarePrincipal implements Principal {
    private final String name;
    private final String fingerprint;
    private final String label;

    @Override
    public String getName() {
        return name;
    }
}
