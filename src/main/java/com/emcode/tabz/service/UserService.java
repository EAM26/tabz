package com.emcode.tabz.service;

import com.emcode.tabz.dto.UserRequest;
import com.emcode.tabz.model.User;

public interface UserService {

    User createUser(UserRequest userRequest);
}
