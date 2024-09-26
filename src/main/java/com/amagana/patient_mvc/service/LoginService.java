package com.amagana.patient_mvc.service;

import com.amagana.patient_mvc.dto.UsersDtoRequest;
import com.amagana.patient_mvc.entities.Roles;
import com.amagana.patient_mvc.entities.Users;

public interface LoginService {

    Users createUser(UsersDtoRequest user);
    Roles createRole(String role);
    void addRoleToUser(Long userId, Long roleId);
    void removeRoleToUser(Long userId, Long roleId);
    Users loadUserByUsername(String username);
}
