package com.example.demo.a;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/doc.html").permitAll()
//                        .requestMatchers("/test/update").permitAll()
//                        .requestMatchers("/test/").denyAll()
//                        .requestMatchers("/test/query").denyAll()
//                        .anyRequest().authenticated()
//                ).csrf(csrf->csrf.disable())
////                .formLogin(form->form.disable())
//                .formLogin(Customizer.withDefaults());
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth -> {
                    auth.requestMatchers("/questionnaire/**").permitAll()
                            .anyRequest().authenticated();
                }
        ).formLogin(Customizer.withDefaults());
        return http.build();
    }
}
