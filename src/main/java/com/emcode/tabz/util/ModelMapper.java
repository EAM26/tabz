package com.emcode.tabz.util;

import com.emcode.tabz.dto.TabResponse;
import com.emcode.tabz.dto.UserRequest;
import com.emcode.tabz.dto.UserResponse;
import com.emcode.tabz.model.Role;
import com.emcode.tabz.model.Tab;
import com.emcode.tabz.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

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
        List<TabResponse> tabs =  user.getTabs().stream().map(this::createTabResponse).toList();
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                tabs,
                user.getUserRole()
        );
    }

    public TabResponse createTabResponse(Tab tab) {
        return new TabResponse(
                tab.getId(),
                tab.getShop().getId(),
                tab.getFileName(),
                tab.getCreatedAt()
        );
    }
}
