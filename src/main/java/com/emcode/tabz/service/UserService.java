package com.emcode.tabz.service;

import com.emcode.tabz.dto.UserResponse;

import java.util.List;

public interface UserService {

//    UserResponse createUser(UserRequest userRequest);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();
}
