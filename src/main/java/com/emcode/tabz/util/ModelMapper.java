package com.emcode.tabz.util;

import com.emcode.tabz.dto.UserRequest;
import com.emcode.tabz.dto.UserResponse;
import com.emcode.tabz.model.Role;
import com.emcode.tabz.model.User;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    public User createUserEntity(UserRequest req) {
        User user = new User();
        user.setUsername(req.username());
        if (req.userRole() != Role.ADMIN) {
            user.setUserRole(Role.USER);
        } else {
            user.setUserRole(Role.ADMIN);
        }
        user.setEmail(req.email());
        return user;
    }

    public UserResponse createUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getTabs(),
                user.getUserRole()
        );
    }
}
