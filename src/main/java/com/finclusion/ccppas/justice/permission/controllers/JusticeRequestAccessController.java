package com.finclusion.ccppas.justice.permission.controllers;

import com.finclusion.ccppas.justice.permission.dtos.GrantAccessRequestDto;
import com.finclusion.ccppas.justice.permission.dtos.GrantAccessResponseDto;
import com.finclusion.ccppas.justice.permission.dtos.RequestAccessDto;
import com.finclusion.ccppas.justice.permission.dtos.RequestAccessResponseDto;
import com.finclusion.ccppas.justice.permission.services.JusticeRequestAccessService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/justice/permission")
public class JusticeRequestAccessController {

    private final JusticeRequestAccessService justiceRequestAccessService;


    @PostMapping("/request-access")
    public ResponseEntity<RequestAccessResponseDto> requestAccess(
            @RequestBody @Valid RequestAccessDto request
    )
    {
        return ResponseEntity.ok(justiceRequestAccessService.justiceRequestAccess(request));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping ("/grant-access")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<GrantAccessResponseDto> grantAccess(
            @RequestBody @Valid GrantAccessRequestDto request
    )
    {
        return ResponseEntity.ok(justiceRequestAccessService.justiceGrantAccess(request));
    }
}
