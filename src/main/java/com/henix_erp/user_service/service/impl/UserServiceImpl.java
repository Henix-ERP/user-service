package com.henix_erp.user_service.service.impl;

import com.henix_erp.user_service.entity.User;
import com.henix_erp.user_service.repository.UserRepository;
import com.henix_erp.user_service.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        Optional<User> optional = userRepository.findByUsername(username);
        return optional.orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> optional = userRepository.findByEmail(email);
        return optional.orElse(null);
    }
}
