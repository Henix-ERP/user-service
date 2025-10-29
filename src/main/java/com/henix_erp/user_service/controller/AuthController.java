package com.henix_erp.user_service.controller;

import com.henix_erp.user_service.entity.Role;
import com.henix_erp.user_service.entity.User;
import com.henix_erp.user_service.security.JwtUtil;
import com.henix_erp.user_service.service.RoleService;
import com.henix_erp.user_service.service.UserDetailService;
import com.henix_erp.user_service.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth/v2")
public class AuthController {

    UserService userService;
    RoleService roleService;
    AuthenticationManager authenticationManager;
    UserDetailService userDetailService;
    JwtUtil jwtUtil;
    PasswordEncoder passwordEncoder;

    AuthController(
            UserService userService,
            RoleService roleService,
            AuthenticationManager authenticationManager,
            UserDetailService userDetailService,
            JwtUtil jwtUtil,
            PasswordEncoder passwordEncoder
    ) {
        this.userService = userService;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> req) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.get("email"), req.get("password"))
        );
        UserDetails userDetails = userDetailService.loadUserByUsername(req.get("email"));
        String token = jwtUtil.generateToken(userDetails);
        return Map.of("token", token);
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {

        if(userService.getByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already taken");
        }
        if(userService.getByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already taken");
        }
        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleService.get(user.getRoleId());
        user.setRole(role);
        User savedUser = userService.create(user);

        if(savedUser != null) {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(savedUser.getEmail(), rawPassword)
            );
            UserDetails userDetails = userDetailService.loadUserByUsername(savedUser.getEmail());
            String token = jwtUtil.generateToken(userDetails);
            return Map.of("token", token);
        }
        return Map.of("token", "");
    }
}
