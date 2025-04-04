package com.finclusion.ccppas.helper;

import com.finclusion.ccppas.user.models.JusticePractitioner;
import com.finclusion.ccppas.user.models.JusticePractitionerRole;
import com.finclusion.ccppas.user.models.UserStatus;
import com.finclusion.ccppas.user.repositories.JusticePractitionerRepository;
import com.finclusion.ccppas.user.repositories.JusticeRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ApplicationBackgroundRunner implements CommandLineRunner {

    private final JusticeRoleRepository justiceRoleRepository;
    private final JusticePractitionerRepository justicePractitionerRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        var admin_role = JusticePractitionerRole.builder()
                .name("SUPER_ADMIN")
                .build();
        var superAdmin = justiceRoleRepository.save(admin_role);
        var user_role = JusticePractitionerRole.builder()
                .name("USER")
                .build();
        var user = justiceRoleRepository.save(user_role);

        var super_admin = JusticePractitioner.builder()
                .email("superadmin@gmail.com")
                .firstname("dancing")
                .lastname("freeman")
                .uniqueId("FMOJ11111111")
                .password(passwordEncoder.encode("00000000"))
                .status(UserStatus.ACTIVE)
                .roles(List.of(superAdmin))
                .build();
        System.out.println(super_admin.getUniqueId());
        var regular_user = JusticePractitioner.builder()
                .email("regularuser@gov.ng")
                .firstname("badboy")
                .lastname("ladelowski")
                .uniqueId("FMOJ22222222")
                .password(passwordEncoder.encode("00000000"))
                .status(UserStatus.ACTIVE)
                .roles(List.of(user))
                .build();
        if (!justicePractitionerRepository.existsByEmail(super_admin.getEmail())){
            justicePractitionerRepository.save(super_admin);
        }
        if (!justicePractitionerRepository.existsByEmail(regular_user.getEmail())){
            justicePractitionerRepository.save(regular_user);
        }


    }

}
