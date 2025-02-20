// package com.example.api_gateway.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.oauth2.jwt.JwtDecoder;
// import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

// import static org.springframework.security.config.Customizer.withDefaults;

// @Configuration
// @EnableWebSecurity
// public class ApiGatewaySecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(authorizeRequests ->
//                 authorizeRequests
//                     .requestMatchers("/auth/**").permitAll()
//                     .requestMatchers("/api/user/**").permitAll()
//                     .requestMatchers("/login").permitAll()
//                     .requestMatchers("/register").permitAll()
//                     .anyRequest().permitAll()
//             )
//             .oauth2ResourceServer(oauth2ResourceServer ->
//                 oauth2ResourceServer.jwt(withDefaults()));
//         return http.build();
//     }

//     @Bean
//     public JwtDecoder jwtDecoder() {
//         return NimbusJwtDecoder.withJwkSetUri("http://localhost:8080/oauth2/jwks").build();
//     }
// }




