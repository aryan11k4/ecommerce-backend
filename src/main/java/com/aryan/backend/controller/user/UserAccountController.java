package com.aryan.backend.controller.user;

import com.aryan.backend.dto.User.UserLoginRequestDto;
import com.aryan.backend.dto.User.UserLoginResponseDto;
import com.aryan.backend.dto.User.UserRegisterRequestDto;
import com.aryan.backend.dto.User.UserRegisterResponseDto;
import com.aryan.backend.security.UserPrincipal;
import com.aryan.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserAccountController {

    UserService service;

    public UserAccountController(UserService service){
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> registerUser(@RequestBody UserRegisterRequestDto dto){
        UserRegisterResponseDto response = service.addUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> loginUser(@RequestBody UserLoginRequestDto dto){
        UserLoginResponseDto response = service.verifyLogIn(dto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        service.deleteUser(userPrincipal.getUsername());
        return ResponseEntity.noContent().build(); // HTTP 204
    }

}
