package chora.auth.controller;

import chora.auth.dto.RegisterDto;
import chora.auth.model.Account;
import chora.auth.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    @PostMapping("/register")
    public Account register(@RequestBody RegisterDto registerDto) {
        return service.register(registerDto);
    }
}
