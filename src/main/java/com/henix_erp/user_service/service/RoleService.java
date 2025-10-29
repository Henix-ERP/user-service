package com.henix_erp.user_service.service;

import com.henix_erp.user_service.entity.Role;

public interface RoleService {

    public Role create(Role role);

    public Role get(int id);
}
