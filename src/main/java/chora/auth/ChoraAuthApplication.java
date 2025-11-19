package chora.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

@SpringBootApplication
public class ChoraAuthApplication {

	public static void main(String[] args) throws Exception {

        System.out.println(generateCodeChallenge("1234567890"));
        SpringApplication.run(ChoraAuthApplication.class, args);
	}

    public static String generateCodeChallenge(String codeVerifier) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(codeVerifier.getBytes(StandardCharsets.US_ASCII));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
    }
}
