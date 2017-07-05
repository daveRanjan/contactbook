package com.centralway.contactbook.service;

import com.centralway.contactbook.controller.response.RegisterUserResponse;
import com.centralway.contactbook.model.User;
import com.centralway.contactbook.security.model.token.AccessJwtToken;

import java.util.Optional;

public interface UserService {
    AccessJwtToken login(User credentials);

    Optional<User> getByUserName(String username);

    RegisterUserResponse register(User user);

    User update(User user);
}
