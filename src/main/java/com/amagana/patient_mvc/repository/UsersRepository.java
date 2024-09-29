package com.amagana.patient_mvc.repository;

import com.amagana.patient_mvc.dto.UsersDtoResponse;
import com.amagana.patient_mvc.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);
    Page<UsersDtoResponse> findAllBy(Pageable pageable);
}
