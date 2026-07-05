package com.aryan.backend.repository;

import com.aryan.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String username);

    boolean existsByEmail(String email);


    User findByEmail(String email);

//    User findByNameOrEmail(String identifier);
}
