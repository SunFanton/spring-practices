package com.course.springsecuritycourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.springsecuritycourse.dto.AuthResponseDTO;
import com.course.springsecuritycourse.dto.LoginDTO;
import com.course.springsecuritycourse.dto.RegisterDTO;
import com.course.springsecuritycourse.entity.User;
import com.course.springsecuritycourse.repository.UserRepository;
import com.course.springsecuritycourse.service.AuthService;
import com.course.springsecuritycourse.service.JwtService;
import com.course.springsecuritycourse.util.Role;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        return null;
    }

    @Override
    public AuthResponseDTO register(RegisterDTO registerDTO) {
        User user = User.builder()
                        .username(registerDTO.getUsername())
                        .password(registerDTO.getPassword())
                        .firstName(registerDTO.getFirstName())
                        .lastName(registerDTO.getLastName())
                        .country(registerDTO.getCountry())
                        .role(Role.CUSTOMER)
                        .build();

        userRepository.save(user);

        return AuthResponseDTO.builder()
                              .token(jwtService.getToken(user))
                              .build();
    }
    
}
