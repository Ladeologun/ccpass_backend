package com.finclusion.ccppas.auth.controllers;

import com.finclusion.ccppas.auth.dtos.LoginRequestDto;
import com.finclusion.ccppas.auth.dtos.LoginResponseDto;
import com.finclusion.ccppas.auth.dtos.RegisterRequestDto;
import com.finclusion.ccppas.auth.dtos.RegisterResponseDto;
import com.finclusion.ccppas.auth.services.JusticeAuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/justice/auth")
@RequiredArgsConstructor
public class JusticeAuthenticationController {

    private final JusticeAuthenticationService justiceAuthenticationService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<RegisterResponseDto> register(
            @RequestBody @Valid RegisterRequestDto request
    )
    {
        return ResponseEntity.ok(justiceAuthenticationService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody @Valid LoginRequestDto request
    )
    {
        return ResponseEntity.ok(justiceAuthenticationService.authenticateUser(request));
    }


}
