package com.amagana.patient_mvc.service.impl;

import com.amagana.patient_mvc.Exception.EntityAlreadyExistException;
import com.amagana.patient_mvc.Exception.EntityNotFoundException;
import com.amagana.patient_mvc.dto.RolesDtoRequest;
import com.amagana.patient_mvc.dto.RolesDtoResponse;
import com.amagana.patient_mvc.entities.Roles;
import com.amagana.patient_mvc.repository.RoleRepository;
import com.amagana.patient_mvc.service.RoleService;
import com.amagana.patient_mvc.utils.RolesMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private static final RolesMapper rolesMapper = RolesMapper.INSTANCE;

    @Override
    public List<RolesDtoResponse> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(rolesMapper::rolesToRolesDtoResponse)
                .toList();
    }

    @Override
    public Page<RolesDtoResponse> getRolesPage(int page, int size) {
        return roleRepository.findAllBy(PageRequest.of(page, size));
    }

    @Override
    public Roles findRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(String.format("Role with id %s not found", id)));
    }

    @Override
    public RolesDtoResponse getRoleById(Long id) {
        return rolesMapper.rolesToRolesDtoResponse(findRoleById(id));
    }

    @Override
    public RolesDtoResponse createRole(String role) {
        RolesDtoRequest rolesDtoRequest = RolesDtoRequest.builder().role(role).build();
        if (roleRepository.findByRole(role) != null)
            throw new EntityAlreadyExistException(String.format("Role with name %s already exist", role));
        return rolesMapper.rolesToRolesDtoResponse(
                roleRepository.save(rolesMapper.rolesDtoRequestToRoles(rolesDtoRequest))
        );
    }

    @Override
    public RolesDtoResponse updateRole(String role, Long id) {
        Roles roles = findRoleById(id);
        roles.setRole(role);
        if (roleRepository.findByRole(role) != null)
            throw new EntityAlreadyExistException(String.format("Role with name %s already exist", role));
        return rolesMapper.rolesToRolesDtoResponse(
                roleRepository.save(roles)
        );
    }

    @Override
    public void deleteRole(Long id) {
        Roles role= findRoleById(id);
        roleRepository.deleteById(role.getId());
    }
}
