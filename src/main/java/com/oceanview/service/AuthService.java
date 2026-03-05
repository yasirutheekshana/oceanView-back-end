package com.oceanview.service;

import com.oceanview.model.User;
import com.oceanview.repository.UserRepository;
import com.oceanview.utils.PasswordUtil;

public class AuthService {

    private final UserRepository userRepository = new UserRepository();

    public String register(String username, String password) {

        if (userRepository.findByUsername(username) != null) {
            return "User already exists!";
        }

        String hashed = PasswordUtil.hashPassword(password);
        userRepository.save(new User(username, hashed));

        return "User registered successfully!";
    }

    public boolean login(String username, String password) {

        User user = userRepository.findByUsername(username);

        if (user == null) return false;

        String hashed = PasswordUtil.hashPassword(password);
        return user.getPassword().equals(hashed);
    }
}
