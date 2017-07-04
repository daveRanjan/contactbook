package com.centralway.contactbook.service;

import com.centralway.contactbook.model.UserCredentials;

public interface UserService {
    String login(UserCredentials credentials);
}
