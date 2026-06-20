package com.aryan.backend.service;

import com.aryan.backend.dto.User.UserLoginRequestDto;
import com.aryan.backend.dto.User.UserLoginResponseDto;
import com.aryan.backend.dto.User.UserRegisterRequestDto;
import com.aryan.backend.dto.User.UserRegisterResponseDto;
import com.aryan.backend.entity.User;
import com.aryan.backend.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager auhtManager;

    public UserService(
            UserRepository userRepo, BCryptPasswordEncoder encoder, AuthenticationManager authManager){
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.auhtManager = authManager;
    }

    public UserRegisterResponseDto addUser(UserRegisterRequestDto dto) {
        if(userRepo.existsByEmail(dto.getEmail())){
            throw new RuntimeException("User with this email already exists");
        }

        User u1 = new User();
        u1.setName(dto.getName());
        u1.setEmail(dto.getEmail());
        u1.setPassword(encoder.encode(dto.getPassword()));

        User created = userRepo.save(u1);

        return new UserRegisterResponseDto(created.getId(), created.getName(), created.getEmail());
    }

    public UserLoginResponseDto verifyLogIn(UserLoginRequestDto dto) {
        User u1 = userRepo.findByEmail(dto.getEmail());

        if(!encoder.matches(dto.getPassword(), u1.getPassword())){
//            throw new RuntimeException("Email and password doesnt match!!");
            throw new IllegalStateException("Email and fsfaspassword doesnt match!!");
        }

        UserLoginResponseDto responseDto = new UserLoginResponseDto();

        responseDto.setId(u1.getId());
        responseDto.setName(u1.getName());
        responseDto.setEmail(u1.getEmail());

        return responseDto;
    }
}
