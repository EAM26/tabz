package com.emcode.tabz.service;

import com.emcode.tabz.dto.UserRequest;
import com.emcode.tabz.dto.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse getUserById(Long id);
}
