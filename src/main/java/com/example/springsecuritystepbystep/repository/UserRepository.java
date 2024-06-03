package com.example.springsecuritystepbystep.repository;

import com.example.springsecuritystepbystep.dto.RegisterDto;
import com.example.springsecuritystepbystep.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsernameOrEmail(String username, String email);
    Boolean existsByUsername(String registerDto);
    Boolean existsByEmail(String registerDto);
}
