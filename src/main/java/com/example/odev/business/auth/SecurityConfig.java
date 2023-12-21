package com.example.odev.business.auth;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import com.example.odev.business.concretes.UserManager;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private  UserManager userManager;

    @Bean
    public AuthenticationManager authenticationManager (HttpSecurity httpSecurity, NoOpPasswordEncoder noOpPasswordEncoder) throws  Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userManager).passwordEncoder(noOpPasswordEncoder);

        return authenticationManagerBuilder.build();
    }

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-ui.html",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .requestMatchers("/api/login").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated())
                .formLogin().usernameParameter("username")
                .passwordParameter("password")
                .successHandler(((request, response, authentication) -> response.getWriter().write("başarılı")))
                .failureHandler((request, response, exception) -> response.getWriter().write("error"));
        httpSecurity.headers().frameOptions().disable();

        return httpSecurity.build();
    }

    @SuppressWarnings("deprecaiton")
    @Bean
    public NoOpPasswordEncoder passwordEncoder () {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}
