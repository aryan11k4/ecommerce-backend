package com.aryan.backend.dto.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponseDto {
    String name;
    String email;
    Integer id;
}
