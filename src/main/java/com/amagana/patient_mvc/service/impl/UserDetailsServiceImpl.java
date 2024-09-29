package com.amagana.patient_mvc.service.impl;

import com.amagana.patient_mvc.dto.UsersDtoResponse;
import com.amagana.patient_mvc.entities.Roles;
import com.amagana.patient_mvc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService loginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersDtoResponse users = loginService.loadUserByUsername(username);
        if (users == null)
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        String[] roles = users.roles().stream().map(Roles::getRole).toArray(String[]::new);
        //List<SimpleGrantedAuthority> authorities = users.getRoles().stream().map(u -> new SimpleGrantedAuthority(u.getRole())).toList();
        return User.builder()
                .username(users.username())
                .password(users.password())
                .roles(roles)
                //.authorities(authorities)
                .build();
    }
}
