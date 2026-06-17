package com.aryan.backend.controller.user;

import com.aryan.backend.dto.User.UserRegisterRequestDto;
import com.aryan.backend.dto.User.UserRegisterResponseDto;
import com.aryan.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class CreateUserController {

    UserService service;

    public CreateUserController(UserService service){
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> registerUser(@RequestBody UserRegisterRequestDto dto){
        UserRegisterResponseDto response = service.addUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
