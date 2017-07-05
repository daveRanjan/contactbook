package com.centralway.contactbook.service.impl;

import com.centralway.contactbook.model.Role;
import com.centralway.contactbook.model.User;
import com.centralway.contactbook.model.UserRole;
import com.centralway.contactbook.repository.UserRepository;
import com.centralway.contactbook.security.model.UserContext;
import com.centralway.contactbook.security.model.token.AccessJwtToken;
import com.centralway.contactbook.security.model.token.JwtTokenFactory;
import com.centralway.contactbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenFactory jwtTokenFactory;


    @Override
    public AccessJwtToken login(User credentials) {
        Optional<User> user = userRepository.findByUserName(credentials.getUserName());

        if (user.isPresent()) {
            return jwtTokenFactory.createAccessJwtToken(UserContext.create(user.get()));
        }

        throw new IllegalArgumentException("Username not valid");
    }

    @Override
    public Optional<User> getByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public AccessJwtToken register(User user) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashPassword = encoder.encode(user.getPassword());
        user.setPasswordHash(hashPassword);
        user.setRoles(Collections.singletonList(new UserRole(Role.MEMBER)));
        Optional<User> existingUser = userRepository.findByUserName(user.getUserName());

        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username already exists. Please choose a unique username");
        }

        User savedUser = userRepository.saveAndFlush(user);

        return jwtTokenFactory.createAccessJwtToken(UserContext.create(savedUser));
    }
}
