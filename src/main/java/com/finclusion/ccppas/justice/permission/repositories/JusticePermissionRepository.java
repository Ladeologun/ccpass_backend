package com.finclusion.ccppas.justice.permission.repositories;

import com.finclusion.ccppas.justice.permission.models.JusticePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JusticePermissionRepository extends JpaRepository<JusticePermission, Long> {

    Optional<JusticePermission> findById(Long unique_id);
    boolean existsById(Long unique_id);

}
