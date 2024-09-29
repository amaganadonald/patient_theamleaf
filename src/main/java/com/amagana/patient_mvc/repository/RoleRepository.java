package com.amagana.patient_mvc.repository;

import com.amagana.patient_mvc.dto.RolesDtoResponse;
import com.amagana.patient_mvc.entities.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Page<RolesDtoResponse> findAllBy(Pageable pageable);
    Roles findByRole(String role);
}
