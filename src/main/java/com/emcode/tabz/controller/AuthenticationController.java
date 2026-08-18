package com.emcode.tabz.controller;

import com.emcode.tabz.dto.AuthenticationRequest;
import com.emcode.tabz.dto.AuthenticationResponse;
import com.emcode.tabz.dto.UserRequest;
import com.emcode.tabz.model.User;
import com.emcode.tabz.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/register-user-by-admin")
    public ResponseEntity<AuthenticationResponse> registerUserByAdmin(
            @RequestBody UserRequest request, Authentication authentication
    ) {
        User loggedInUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(service.register(request, loggedInUser));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
