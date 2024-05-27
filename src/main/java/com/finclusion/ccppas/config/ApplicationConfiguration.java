package com.finclusion.ccppas.config;

import com.finclusion.ccppas.user.repositories.JusticePractitionerRepository;
import com.finclusion.ccppas.user.repositories.LawEnforcerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final JusticePractitionerRepository justicePractitionerRepository;
    private final LawEnforcerRepository lawEnforcerRepository;


    @Bean
    @Qualifier("justiceUserDetailService")
    public UserDetailsService justiceUserDetailsService() {
        return uniqueID -> justicePractitionerRepository.findByUniqueId(uniqueID)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    @Bean
    @Qualifier("lawEnforceUserDetailService")
    public UserDetailsService lawEnforceUserDetailsService() {
        return uniqueID -> lawEnforcerRepository.findByUniqueId(uniqueID)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    @Qualifier("justiceAuthenticationProvider")
    public AuthenticationProvider justiceAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(justiceUserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    @Qualifier("LawEnforceAuthenticationProvider")
    public AuthenticationProvider LawEnforceAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(lawEnforceUserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
