package com.example.sgo_crm.service.impl;

import com.example.sgo_crm.exception.AuthenticationFailedException;
import com.example.sgo_crm.model.User;
import com.example.sgo_crm.request.AuthenticationRequest;
import com.example.sgo_crm.service.AuthenticationService;
import com.example.sgo_crm.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private final UserServiceImpl userService;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService, UserServiceImpl userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public String authenticate(AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = (User) authentication.getPrincipal();

            String token = jwtService.generateToken(user);
            user.setToken(token);
            userService.save(user);
            return token;
        } catch (AuthenticationException e) {
            throw new AuthenticationFailedException("Thông tin tài khoản hoặc mật khẩu không chính xác", e);
        }
    }
}
