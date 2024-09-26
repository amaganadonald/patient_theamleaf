package com.amagana.patient_mvc.service.impl;

import com.amagana.patient_mvc.dto.UsersDtoRequest;
import com.amagana.patient_mvc.entities.Roles;
import com.amagana.patient_mvc.entities.Users;
import com.amagana.patient_mvc.repository.UsersRepository;
import com.amagana.patient_mvc.service.LoginService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private UsersRepository usersRepository;

    @Override
    public Users createUser(UsersDtoRequest user) {
        return null;
    }

    @Override
    public Roles createRole(String role) {
        return null;
    }

    @Override
    public void addRoleToUser(Long userId, Long roleId) {

    }

    @Override
    public void removeRoleToUser(Long userId, Long roleId) {

    }

    @Override
    public Users loadUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
