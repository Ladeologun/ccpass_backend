package com.finclusion.ccppas.user.repositories;

import com.finclusion.ccppas.user.models.JusticePractitionerRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JusticeRoleRepository extends JpaRepository<JusticePractitionerRole, Long> {

    Optional<JusticePractitionerRole> findByName(String name);
    Boolean existsByNameIgnoreCase(String name);
}
