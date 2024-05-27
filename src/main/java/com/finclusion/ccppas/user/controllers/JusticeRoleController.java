package com.finclusion.ccppas.user.controllers;


import com.finclusion.ccppas.user.dtos.AddRoleRequestDto;
import com.finclusion.ccppas.user.dtos.AddRoleResponseDto;
import com.finclusion.ccppas.user.services.JusticeRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/justice")
@RequiredArgsConstructor
public class JusticeRoleController {

    private final JusticeRoleService justiceRoleService;

    @PostMapping("/roles")
    public ResponseEntity<AddRoleResponseDto> addRole(
            @RequestBody @Valid AddRoleRequestDto request
    )
    {
        return ResponseEntity.ok(justiceRoleService.createRole(request));
    }
}
