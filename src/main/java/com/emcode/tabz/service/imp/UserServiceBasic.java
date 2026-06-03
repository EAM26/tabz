package com.emcode.tabz.service.imp;

import com.emcode.tabz.dto.UserRequest;
import com.emcode.tabz.dto.UserResponse;
import com.emcode.tabz.model.User;
import com.emcode.tabz.repository.UserRepo;
import com.emcode.tabz.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceBasic implements UserService {

    private final UserRepo userRepo;

    public UserServiceBasic(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User savedUser = userRepo.save(createEntity(userRequest));
        return createResponse(savedUser);
    }

    private User createEntity(UserRequest req) {
        System.out.println(req.username());
        User user = new User();
        user.setUsername(req.username());
        System.out.println(user.getUsername());
        user.setEmail(req.email());
        user.setPassword(req.password());
        return user;
    }

    private UserResponse createResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getTabs()
        );
    }

}
