package com.finclusion.ccppas.user.services;

import com.finclusion.ccppas.user.dtos.AddRoleRequestDto;
import com.finclusion.ccppas.user.dtos.AddRoleResponseDto;
import com.finclusion.ccppas.user.models.JusticePractitionerRole;
import com.finclusion.ccppas.user.repositories.JusticeRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class JusticeRoleService {
    private final JusticeRoleRepository justiceRoleRepository;

    public AddRoleResponseDto createRole(AddRoleRequestDto request) {

        var roleExists = justiceRoleRepository.existsByNameIgnoreCase(request.getName());
        if (roleExists){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This role exists");

        }
        var role = JusticePractitionerRole.builder()
                .name(request.getName().toUpperCase())
                .build();
        var savedRole = justiceRoleRepository.save(role);
        return AddRoleResponseDto.builder().name(savedRole.getName()).build();
    }
}

//TO-DO:create service to patch,list and dalete roles
