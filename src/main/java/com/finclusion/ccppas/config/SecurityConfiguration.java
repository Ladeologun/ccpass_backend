package com.finclusion.ccppas.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    private final AuthenticationProvider justiceAuthenticationProvider;
    private final AuthenticationProvider LawEnforceAuthenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    public SecurityConfiguration(
        @Qualifier("justiceAuthenticationProvider") AuthenticationProvider justiceAuthenticationProvider,
        @Qualifier("LawEnforceAuthenticationProvider") AuthenticationProvider lawEnforceAuthenticationProvider,
        JwtAuthenticationFilter jwtAuthenticationFilter)
    {
        this.justiceAuthenticationProvider = justiceAuthenticationProvider;
        this.LawEnforceAuthenticationProvider = lawEnforceAuthenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    private static final String[] WHITE_LIST_URL = {
//            "/api/v1/justice/authenticate",
//            "/api/v1/justice/auth/register",
            "/api/v1/justice/auth/login",
            "/api/v1/justice/permission/request-access",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-ui/**"
//            "/error"
//            "/api/v1/justice/request-access",
//            "/api/v1/law-enforce/authenticate",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception{
        http
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(req ->
                    req.requestMatchers(WHITE_LIST_URL)
                            .permitAll()
                            .anyRequest()
                            .authenticated()
            )
            .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationManager(authenticationManager(http))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(justiceAuthenticationProvider);
        authenticationManagerBuilder.authenticationProvider(LawEnforceAuthenticationProvider);
        return authenticationManagerBuilder.build();

    }

}
