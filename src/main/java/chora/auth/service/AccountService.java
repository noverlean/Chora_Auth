package chora.auth.service;

import chora.auth.model.AccountStatus;
import chora.auth.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository repo;
    private final PasswordEncoder encoder;

    public AccountService(AccountRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public Account register(String email, String rawPassword) {
        Account acc = new Account();
        acc.setEmail(email);
        acc.setPasswordHash(encoder.encode(rawPassword));
        acc.setStatus(AccountStatus.ACTIVE);
        return repo.save(acc);
    }

    public Account findByEmail(String email) {
        return repo.findByEmail(email).orElseThrow();
    }
}