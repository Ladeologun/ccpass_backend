package com.finclusion.ccppas.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService justiceuserDetailsService;
    private final UserDetailsService lawEnforceUserDetailsService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            @Qualifier("justiceUserDetailService") UserDetailsService justiceuserDetailsService,
            @Qualifier("lawEnforceUserDetailService") UserDetailsService lawEnforceUserDetailsService)
    {
        this.jwtService = jwtService;
        this.justiceuserDetailsService = justiceuserDetailsService;
        this.lawEnforceUserDetailsService = lawEnforceUserDetailsService;
    }


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException
    {

        if (request.getServletPath().contains("/api/v1/justice/auth/login")) {
            System.out.println("this filter was abadoned");
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String uniqueId;
        final UserDetails userDetails;
        if (authHeader == null ||!authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        uniqueId = jwtService.extractUsername(jwt);

        if (uniqueId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (request.getServletPath().contains("/api/v1/justice")){
                userDetails = this.justiceuserDetailsService.loadUserByUsername(uniqueId);
            } else {
                userDetails = this.lawEnforceUserDetailsService.loadUserByUsername(uniqueId);
            }


            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}

