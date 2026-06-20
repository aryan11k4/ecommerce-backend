package com.aryan.backend.dto.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDto {
    String name;
    String password;
    String email;
}
