package com.aryan.backend.service;

import com.aryan.backend.dto.User.UserRegisterRequestDto;
import com.aryan.backend.dto.User.UserRegisterResponseDto;
import com.aryan.backend.entity.User;
import com.aryan.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepo, BCryptPasswordEncoder encoder){
        this.userRepo = userRepo;
        this.encoder = encoder;
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
}
