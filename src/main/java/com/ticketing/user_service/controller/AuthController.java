package com.ticketing.user_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketing.user_service.dto.SigninRequest;
import com.ticketing.user_service.dto.SigninResponse;
import com.ticketing.user_service.dto.SignupRequest;
import com.ticketing.user_service.dto.SignupResponse;
import com.ticketing.user_service.security.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

    final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public SigninResponse signIn(@RequestBody SigninRequest request) {
        return authService.authenticate(request);
    }

    @PostMapping("register")
    public SignupResponse register(@RequestBody SignupRequest request) {
        return authService.register(request);
    }

}
