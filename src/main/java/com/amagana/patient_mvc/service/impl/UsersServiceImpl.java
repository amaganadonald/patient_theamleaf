package com.amagana.patient_mvc.service.impl;

import com.amagana.patient_mvc.Exception.EntityAlreadyExistException;
import com.amagana.patient_mvc.Exception.EntityNotFoundException;
import com.amagana.patient_mvc.dto.UsersDtoRequest;
import com.amagana.patient_mvc.dto.UsersDtoResponse;
import com.amagana.patient_mvc.entities.Roles;
import com.amagana.patient_mvc.entities.Users;
import com.amagana.patient_mvc.repository.UsersRepository;
import com.amagana.patient_mvc.service.RoleService;
import com.amagana.patient_mvc.service.UserService;
import com.amagana.patient_mvc.utils.RolesMapper;
import com.amagana.patient_mvc.utils.UsersMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UsersServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private static final UsersMapper usersMapper = UsersMapper.INSTANCE;
    private static final RolesMapper rolesMapper = RolesMapper.INSTANCE;
    private final RoleService roleService;

    @Override
    public List<UsersDtoResponse> getUsers() {
        return usersRepository.findAll()
                .stream().map(usersMapper::usersToUsersDtoResponse)
                .toList();
    }

    public Users findUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(String.format("User not found with id %s", id)));
    }

    @Override
    public Page<UsersDtoResponse> getUsersPages(int page, int size) {
        return usersRepository.findAllBy(PageRequest.of(page, size));
    }

    @Override
    public UsersDtoResponse createUser(UsersDtoRequest user) {
        if(loadUserByUsername(user.username()) != null)
            throw new EntityAlreadyExistException("Username already Exist in the database");
        return usersMapper.usersToUsersDtoResponse(
                usersRepository.save(usersMapper.usersDtoRequestToUsers(user))
        );
    }

    @Override
    public UsersDtoResponse updateUser(UsersDtoRequest usersDtoRequest, Long id) {
        Users user = findUserById(id);
        user.setActive(usersDtoRequest.active());
        user.setPassword(usersDtoRequest.password());
        user.setUsername(usersDtoRequest.username());
        if(loadUserByUsername(user.getUsername()) != null)
            throw new EntityAlreadyExistException("Username already Exist in the database");
        return usersMapper.usersToUsersDtoResponse(usersRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        Users user = findUserById(id);
        usersRepository.deleteById(user.getId());
    }

    @Override
    public void addRoleToUser(Long userId, Long roleId) {
        Users user = findUserById(userId);
        Roles role = roleService.findRoleById(roleId);
        user.getRoles().add(role);
    }

    @Override
    public void removeRoleToUser(Long userId, Long roleId) {
        Users user = findUserById(userId);
        Roles role = roleService.findRoleById(roleId);
        user.getRoles().remove(role);
    }

    @Override
    public UsersDtoResponse loadUserByUsername(String username) {
        return usersMapper.usersToUsersDtoResponse(usersRepository.findByUsername(username));
    }
}
