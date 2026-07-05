package com.aryan.backend.service;

import com.aryan.backend.dto.User.UserLoginRequestDto;
import com.aryan.backend.dto.User.UserLoginResponseDto;
import com.aryan.backend.dto.User.UserRegisterRequestDto;
import com.aryan.backend.dto.User.UserRegisterResponseDto;
import com.aryan.backend.entity.Cart;
import com.aryan.backend.entity.User;
import com.aryan.backend.repository.CartRepository;
import com.aryan.backend.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final CartRepository cartRepo;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JWTService jwtService;

    public UserService(
            UserRepository userRepo, CartRepository cartRepo, BCryptPasswordEncoder encoder,
            AuthenticationManager authManager, JWTService jwtService){
        this.userRepo = userRepo;
        this.cartRepo = cartRepo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public UserRegisterResponseDto addUser(UserRegisterRequestDto dto) {
        if(userRepo.existsByEmail(dto.getEmail())){
            throw new RuntimeException("User with this email already exists");
        }

        User u1 = new User();
        Cart cart = new Cart();
        u1.setCart(cart);
        cart.setUser(u1);
        u1.setName(dto.getName());
        u1.setEmail(dto.getEmail());
        u1.setPassword(encoder.encode(dto.getPassword()));

        User created = userRepo.save(u1);
        cartRepo.save(cart);

        return new UserRegisterResponseDto(created.getId(), created.getName(), created.getEmail());
    }

    public UserLoginResponseDto verifyLogIn(UserLoginRequestDto dto) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail() ,dto.getPassword()));


        UserLoginResponseDto responseDto = new UserLoginResponseDto();

        if(authentication.isAuthenticated()){
            User u1 = userRepo.findByEmail(dto.getEmail());

            responseDto.setId(u1.getId());
            responseDto.setName(u1.getName());
            responseDto.setEmail(u1.getEmail());
            responseDto.setToken(jwtService.generateToken(u1.getName()));

            return responseDto;
        }

        return responseDto;
    }
}
