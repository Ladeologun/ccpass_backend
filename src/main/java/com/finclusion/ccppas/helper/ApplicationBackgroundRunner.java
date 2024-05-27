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

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

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
                .firstname("oluwadahunsi")
                .lastname("mojigbotoluwa")
                .uniqueId(generateFmojId())
                .password(passwordEncoder.encode("12345666764565"))
                .status(UserStatus.ACTIVE)
                .roles(List.of(superAdmin))
                .build();
        System.out.println(super_admin.getUniqueId());
        var regular_user = JusticePractitioner.builder()
                .email("regularuser@gov.ng")
                .firstname("omolade")
                .lastname("mojigbotoluwa")
                .uniqueId(generateFmojId())
                .password(passwordEncoder.encode("123456667645"))
                .status(UserStatus.ACTIVE)
                .roles(List.of(user))
                .build();

        justicePractitionerRepository.save(super_admin);
        justicePractitionerRepository.save(regular_user);
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
