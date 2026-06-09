package com.emcode.tabz.service.imp;

import com.emcode.tabz.config.JwtService;
import com.emcode.tabz.dto.AuthenticationRequest;
import com.emcode.tabz.dto.AuthenticationResponse;
import com.emcode.tabz.dto.UserRequest;
import com.emcode.tabz.model.User;
import com.emcode.tabz.repository.UserRepo;
import com.emcode.tabz.service.AuthenticationService;
import com.emcode.tabz.util.ModelMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceBasic implements AuthenticationService {

    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;


    public AuthenticationResponse register(UserRequest request) {
        User user = mapper.createUserEntity(request);

        user.setPassword(passwordEncoder.encode(request.password()));

        userRepo.save(user);
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("No user found"));
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}
