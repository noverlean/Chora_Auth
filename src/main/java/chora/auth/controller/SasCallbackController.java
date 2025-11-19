package chora.auth.controller;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpaCallbackController {
    @GetMapping("/callback")
    public ResponseEntity<String> handleCallback(@RequestParam String code) {
        return ResponseEntity.ok("Authorization code: " + code);
    }
}
