package com.example.springsecuritystepbystep.service;

import com.example.springsecuritystepbystep.dto.LoginDto;
import com.example.springsecuritystepbystep.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
