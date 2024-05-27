package com.finclusion.ccppas.user.repositories;

import com.finclusion.ccppas.user.models.LawEnforcerRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LawEnforcerRoleRepository extends JpaRepository<LawEnforcerRole, Long> {

    Optional<LawEnforcerRole> findByName(String name);
}
