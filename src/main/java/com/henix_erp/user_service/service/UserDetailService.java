package com.henix_erp.user_service.service;

import com.henix_erp.user_service.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    UserService userService;

    UserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByEmail(username);

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().getTitle()))
        );
    }
}
