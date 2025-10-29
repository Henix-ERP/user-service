package com.henix_erp.user_service.service;

import com.henix_erp.user_service.entity.User;

public interface UserService {

    public User create(User user);

    public User getByUsername(String username);

    public User getByEmail(String email);
}
