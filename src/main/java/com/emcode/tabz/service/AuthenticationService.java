package com.emcode.tabz.service;

import com.emcode.tabz.dto.AuthenticationRequest;
import com.emcode.tabz.dto.AuthenticationResponse;
import com.emcode.tabz.dto.UserRequest;
import com.emcode.tabz.model.User;

public interface AuthenticationService {

    AuthenticationResponse register(UserRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse register(UserRequest request, User user);
}
