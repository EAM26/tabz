package com.emcode.tabz.service.imp;

import com.emcode.tabz.dto.UserRequest;
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
    public User createUser(UserRequest userRequest) {
        return createEntity(userRequest);
    }

    private User createEntity(UserRequest req) {
        System.out.println(req.username());
        User user = new User();
        user.setUsername(req.username());
        System.out.println(user.getUsername());
        user.setEmail(req.email());
        user.setPassword(req.password());
        return userRepo.save(user);
    }

}
