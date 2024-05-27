package com.finclusion.ccppas.user.repositories;

import com.finclusion.ccppas.user.models.LawEnforcer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LawEnforcerRepository extends JpaRepository<LawEnforcer, Long> {
    Optional<LawEnforcer> findByUniqueId(String unique_id);
}
