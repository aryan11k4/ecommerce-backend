package com.aryan.backend.dto.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestDto {
    String name;
    String email;
    String password;
}
