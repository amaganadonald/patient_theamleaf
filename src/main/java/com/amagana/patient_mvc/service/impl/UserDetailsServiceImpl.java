package com.amagana.patient_mvc.service.impl;

import com.amagana.patient_mvc.entities.Roles;
import com.amagana.patient_mvc.entities.Users;
import com.amagana.patient_mvc.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = loginService.loadUserByUsername(username);
        if (users == null)
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        String[] roles = users.getRoles().stream().map(Roles::getRole).toArray(String[]::new);
        //List<SimpleGrantedAuthority> authorities = users.getRoles().stream().map(u -> new SimpleGrantedAuthority(u.getRole())).toList();
        return User.builder()
                .username(users.getUsername())
                .password(users.getPassword())
                .roles(roles)
                //.authorities(authorities)
                .build();
    }
}
