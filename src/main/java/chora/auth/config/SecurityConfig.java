package chora.auth.config;

import chora.auth.utils.DeviceAwareAuthorizationRequestConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http,
                                                          UserDetailsService userDetailsService,
                                                          PasswordEncoder passwordEncoder) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/login",
                                "/login/**",
                                "/static/**",
                                "/",
                                "/accounts/register",
                                "/oauth2/authorize",
                                "/login",
                                "/error",
                                "/favicon.ico",
                                "/.well-known/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/login", "/accounts/register", "/oauth2/authorize", "/.well-known/**")
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .userDetailsService(userDetailsService)
                .authenticationProvider(new DaoAuthenticationProvider() {{
                    setUserDetailsService(userDetailsService);
                    setPasswordEncoder(passwordEncoder);
                }});

        return http.build();
    }
}
