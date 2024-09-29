package com.amagana.patient_mvc.service;

import com.amagana.patient_mvc.dto.RolesDtoResponse;
import com.amagana.patient_mvc.dto.UsersDtoRequest;
import com.amagana.patient_mvc.dto.UsersDtoResponse;
import com.amagana.patient_mvc.entities.Users;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    List<UsersDtoResponse> getUsers();
    Page<UsersDtoResponse> getUsersPages(int page, int size);
    UsersDtoResponse createUser(UsersDtoRequest usersDtoRequest);
    UsersDtoResponse updateUser(UsersDtoRequest usersDtoRequest, Long id);
    void deleteUser(Long id);
    void addRoleToUser(Long userId, Long roleId);
    void removeRoleToUser(Long userId, Long roleId);
    UsersDtoResponse loadUserByUsername(String username);
}
