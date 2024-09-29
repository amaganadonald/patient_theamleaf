package com.amagana.patient_mvc.dto;

import com.amagana.patient_mvc.entities.Roles;

import java.util.Collections;
import java.util.Set;

public record UsersDtoResponse(Long id, String username,
                               String password,
                               boolean active, String ConfirmPassword, Set<Roles> roles) {
    public UsersDtoResponse(Set<Roles> roles) {
        this(0L, "", "", false, "", Collections.unmodifiableSet(roles));
    }
}
