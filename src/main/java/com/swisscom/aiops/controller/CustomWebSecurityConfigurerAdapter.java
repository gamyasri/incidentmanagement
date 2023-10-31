package com.swisscom.aiops.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter {
    @Value("${security.basicauth.username}")
    private String username;
    @Value("${security.basicauth.password}")
    private String password;

    @Bean
    protected InMemoryUserDetailsManager configureAuthentication() {
        return new InMemoryUserDetailsManager(
                User.withUsername(username)
                        .password(passwordEncoder().encode(password))
                        .authorities("USER")
                        .build()
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .requestMatchers("/inc/check")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}