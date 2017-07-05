package com.centralway.contactbook.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegisterUserResponse {
    String username;
    String token;
}
