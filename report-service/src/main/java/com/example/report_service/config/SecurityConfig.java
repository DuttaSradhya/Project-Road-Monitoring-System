// package com.example.report_service.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.oauth2.jwt.JwtDecoder;
// import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
// import org.springframework.security.web.SecurityFilterChain;
// import static org.springframework.security.config.Customizer.withDefaults;

// import java.nio.charset.StandardCharsets;
// import java.security.Key;

// import javax.crypto.SecretKey;
// import javax.crypto.spec.SecretKeySpec;

// import io.jsonwebtoken.security.Keys;
// import lombok.extern.slf4j.Slf4j;



// @Configuration
// @EnableWebSecurity
// @Slf4j
// public class SecurityConfig {

//     private static final String SECRET_KEY = "your_secret_keysdfhlkfdghkjkkjkhjcskhjsdcjkhjkhscdkhjcslsjglesrfghjlsdkfskdfhgkejrhfg";
//     private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .authorizeHttpRequests(authorizeRequests ->
//                 authorizeRequests.anyRequest().permitAll()
//             )
//             .oauth2ResourceServer(oauth2ResourceServer ->
//                 oauth2ResourceServer.jwt(withDefaults()));
//         return http.build();
//     }

//     @Bean
//     public JwtDecoder jwtDecoder() {
//         SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//         log.info("null, NimbusJwtDecoder.withSecretKey(secretKey).build()");
//         return NimbusJwtDecoder.withSecretKey(secretKey).build();
//     }
// }

