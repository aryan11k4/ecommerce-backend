package com.aryan.backend.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterResponseDto {
    Integer id;
    String name;
    String email;
}
