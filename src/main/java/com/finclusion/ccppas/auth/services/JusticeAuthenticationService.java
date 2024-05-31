package com.finclusion.ccppas.auth.services;

import com.finclusion.ccppas.auth.dtos.LoginRequestDto;
import com.finclusion.ccppas.auth.dtos.LoginResponseDto;
import com.finclusion.ccppas.auth.dtos.RegisterRequestDto;
import com.finclusion.ccppas.auth.dtos.RegisterResponseDto;
import com.finclusion.ccppas.config.JwtService;
import com.finclusion.ccppas.user.models.JusticePractitioner;
import com.finclusion.ccppas.user.models.UserStatus;
import com.finclusion.ccppas.user.repositories.JusticePractitionerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class JusticeAuthenticationService {

    private final JusticePractitionerRepository justicePractitionerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public RegisterResponseDto registerUser (RegisterRequestDto requestDto){
        var userExists = justicePractitionerRepository.existsByEmail(requestDto.getEmail());
        if (userExists){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already in use");
        }
        var supervisor = justicePractitionerRepository.findByEmail(requestDto.getSupervisorEmail());
        var uniqueID = generateFmojId();
        var newUser = JusticePractitioner.builder()
                .email(requestDto.getEmail())
                .firstname(requestDto.getFirstname())
                .lastname(requestDto.getLastname())
                .uniqueId(uniqueID)
                .bvn(requestDto.getBvn())
                .nin(requestDto.getNin())
                .supervisor(supervisor.get())
                .status(UserStatus.PENDING)
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();
        var savedUser = justicePractitionerRepository.save(newUser);
        var response = RegisterResponseDto.builder()
                .uniqueId(savedUser.getUniqueId())
                .email(savedUser.getEmail())
                .firstname(savedUser.getFirstname())
                .lastname(savedUser.getLastname())
                .build();

        return response;
    }


    public LoginResponseDto authenticateUser(LoginRequestDto request) {
        var auth  = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUniqueId(), request.getPassword())
        );
        var claims = new HashMap<String, Object>();
        var user = ((JusticePractitioner)auth.getPrincipal());
        claims.put("fullName", user.fullName());
        var jwtToken = jwtService.generateToken(claims, user);
        return LoginResponseDto.builder().token(jwtToken).build();
    }



    private String generateFmojId (){
        final String ID_PREFIX = "FMOJ";
        final Random RANDOM = new SecureRandom();
        String fmojid;
        do {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 8; i++) {builder.append(RANDOM.nextInt(10));}
            fmojid = ID_PREFIX + builder.toString();

        }while(justicePractitionerRepository.existsByUniqueId(fmojid));

        return fmojid;
    }


}
