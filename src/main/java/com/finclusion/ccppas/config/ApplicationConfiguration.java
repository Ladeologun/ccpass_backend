package com.finclusion.ccppas.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.finclusion.ccppas.user.repositories.JusticePractitionerRepository;
import com.finclusion.ccppas.user.repositories.LawEnforcerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.format.DateTimeFormatter;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final JusticePractitionerRepository justicePractitionerRepository;
    private final LawEnforcerRepository lawEnforcerRepository;

    private static final String dateFormat = "yyyy-MM-dd";
    private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";


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



    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat(dateTimeFormat);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
        };
    }


}
