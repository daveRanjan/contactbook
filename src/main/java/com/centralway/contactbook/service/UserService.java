package com.centralway.contactbook.service;

import com.centralway.contactbook.model.User;
import com.centralway.contactbook.security.model.token.AccessJwtToken;

import java.util.Optional;

public interface UserService {
    String login(User credentials);

    Optional<User> getByUserName(String username);

    AccessJwtToken register(User user);
}
