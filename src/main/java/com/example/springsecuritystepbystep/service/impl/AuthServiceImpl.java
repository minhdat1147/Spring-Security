package com.example.springsecuritystepbystep.service.impl;

import com.example.springsecuritystepbystep.dto.LoginDto;
import com.example.springsecuritystepbystep.dto.RegisterDto;
import com.example.springsecuritystepbystep.entity.RoleEntity;
import com.example.springsecuritystepbystep.entity.UserEntity;
import com.example.springsecuritystepbystep.repository.RoleRepository;
import com.example.springsecuritystepbystep.repository.UserRepository;
import com.example.springsecuritystepbystep.service.AuthService;
import com.example.springsecuritystepbystep.validate.TodoAPIException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST,"Username already exists!!");

        }
        if (userRepository.existsByEmail(registerDto.getEmail())){
            throw  new TodoAPIException(HttpStatus.BAD_REQUEST, "Email already exists!!");

        }
        UserEntity user = new UserEntity();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return "User Registered Successfully!.";
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
           loginDto.getUsernameOrEmail(), loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Login Successful";
    }
}
