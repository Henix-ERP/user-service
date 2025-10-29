package com.henix_erp.user_service.service.impl;

import com.henix_erp.user_service.entity.Role;
import com.henix_erp.user_service.repository.RoleRepository;
import com.henix_erp.user_service.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;

    RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role get(int id) {
        Optional<Role> optional = roleRepository.findById(id);
        return optional.orElse(null);
    }
}
