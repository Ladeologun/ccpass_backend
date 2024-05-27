package com.finclusion.ccppas.user.repositories;

import com.finclusion.ccppas.user.models.JusticePractitioner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JusticePractitionerRepository extends JpaRepository<JusticePractitioner, Long> {

    Optional<JusticePractitioner> findByUniqueId(String unique_id);
    Optional<JusticePractitioner> findByEmail(String email);
    boolean existsByUniqueId(String unique_id);
    boolean existsByEmail(String email);
}
