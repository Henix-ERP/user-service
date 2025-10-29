package com.henix_erp.user_service.controller;

import com.henix_erp.user_service.entity.Role;
import com.henix_erp.user_service.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class RoleController {

    RoleService roleService;

    RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/role")
    public ResponseEntity<Role> create(@RequestBody Role role) {
        Role savedRole = roleService.create(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
    }

    @GetMapping("/role")
    public ResponseEntity<Role> get(@PathVariable int id) {
        Role role = roleService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }
}
