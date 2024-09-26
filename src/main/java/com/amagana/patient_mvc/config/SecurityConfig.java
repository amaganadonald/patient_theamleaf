package com.amagana.patient_mvc.config;

import com.amagana.patient_mvc.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private DataSource dataSource;
    private UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(DataSource dataSource, UserDetailsServiceImpl userDetailsService) {
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
    }

    //@Bean
    //public UserDetailsService users() {

        // The builder will ensure the passwords are encoded before saving in memory
        // User.UserBuilder users = User.withDefaultPasswordEncoder();
       /* UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("donald"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);*/
        /*JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
         userDetailsManager.setUsersByUsernameQuery("select username as principal, password as credentials, active " +
                "from users where username=?");
         userDetailsManager.setAuthoritiesByUsernameQuery("select username as principal, role as role " +
               // "from users_roles where username=?");
        userDetailsManager.setRolePrefix("ROLE_");
        return userDetailsManager;*/
    //}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .rememberMe(httpSecurityRememberMe -> httpSecurityRememberMe.useSecureCookie(true))
                .formLogin(httpSecurityFormLogin -> httpSecurityFormLogin.loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/", "/webjars/**").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(exceptionHandling-> exceptionHandling.accessDeniedPage("/403"))
                .userDetailsService(userDetailsService);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
