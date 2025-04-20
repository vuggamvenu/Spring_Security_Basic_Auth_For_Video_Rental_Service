package com.crio.VideoRentalBasicAuthWithMongo.service;

import org.springframework.aot.hint.annotation.RegisterReflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.webauthn.api.AuthenticatorResponse;
import org.springframework.stereotype.Service;

import com.crio.VideoRentalBasicAuthWithMongo.controller.exchanges.request.AuthRequest;
import com.crio.VideoRentalBasicAuthWithMongo.controller.exchanges.request.RegisterRequest;
import com.crio.VideoRentalBasicAuthWithMongo.controller.exchanges.response.AuthResponse;
import com.crio.VideoRentalBasicAuthWithMongo.model.User;
import com.crio.VideoRentalBasicAuthWithMongo.model.enums.Role;
import com.crio.VideoRentalBasicAuthWithMongo.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (request.getRole() == null) {
            request.setRole(Role.CUSTOMER);
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        return AuthResponse.builder().build();

    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));
        return AuthResponse.builder().build();
    }

}
