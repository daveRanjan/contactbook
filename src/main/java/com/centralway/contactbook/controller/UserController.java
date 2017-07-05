package com.centralway.contactbook.controller;

import com.centralway.contactbook.model.User;
import com.centralway.contactbook.security.model.token.AccessJwtToken;
import com.centralway.contactbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        return "Hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AccessJwtToken login(@RequestBody User user) {
        return userService.login(user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public AccessJwtToken register(@RequestBody User user) {
        return userService.register(user);
    }
}
