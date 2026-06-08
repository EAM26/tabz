package com.emcode.tabz.service;

import com.emcode.tabz.dto.AuthenticationRequest;
import com.emcode.tabz.dto.AuthenticationResponse;
import com.emcode.tabz.dto.UserRequest;

public interface AuthenticationService {

    AuthenticationResponse register(UserRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
