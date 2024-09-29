package com.amagana.patient_mvc.service;

import com.amagana.patient_mvc.dto.RolesDtoResponse;
import com.amagana.patient_mvc.entities.Roles;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService {

    List<RolesDtoResponse> getAllRoles();
    Page<RolesDtoResponse> getRolesPage(int page, int size);
    Roles findRoleById(Long id);
    RolesDtoResponse getRoleById(Long id);
    RolesDtoResponse createRole(String role);
    RolesDtoResponse updateRole(String role, Long id);
    void deleteRole(Long id);
}
