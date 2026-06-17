package com.aryan.backend.security;

import com.aryan.backend.repository.UserRepository;
import com.aryan.backend.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    UserRepository userRepo;

    public MyUserDetailsService(UserRepository repo) {
        this.userRepo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User u1 = userRepo.findByName(username);

        System.out.println(username + " ");

        if(u1 == null){
            System.out.println("User Not Found!!");
            throw new UsernameNotFoundException("User Not Found");
        }

        return new UserPrincipal(u1);
    }
}
