package chora.auth.service;

import chora.auth.dto.RegisterDto;
import chora.auth.exceptions.custom.UserAlreadyExistsException;
import chora.auth.exceptions.custom.UserNotFoundException;
import chora.auth.model.Account;
import chora.auth.model.AccountStatus;
import chora.auth.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepository repo;
    private final PasswordEncoder encoder;

    public AccountService(AccountRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public Account register(RegisterDto registerDto) {
        if (repo.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        Account account = new Account()
                .setEmail(registerDto.getEmail())
                .setPasswordHash(encoder.encode(registerDto.getPassword()))
                .setStatus(AccountStatus.ACTIVE);

        return repo.save(account);
    }

    public Account findByEmail(String email) {
        return repo.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public Account findById(UUID id) {
        return repo.findById(id).orElseThrow(UserNotFoundException::new);
    }
}