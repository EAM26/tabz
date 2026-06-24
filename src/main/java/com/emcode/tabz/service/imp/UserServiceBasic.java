package com.emcode.tabz.service.imp;

import com.emcode.tabz.dto.UserResponse;
import com.emcode.tabz.exception.RecordNotFoundException;
import com.emcode.tabz.model.User;
import com.emcode.tabz.repository.UserRepo;
import com.emcode.tabz.service.UserService;
import com.emcode.tabz.util.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceBasic implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper mapper;

    public UserServiceBasic(UserRepo userRepo, ModelMapper mapper) {
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

//    @Override
//    public UserResponse createUser(UserRequest userRequest) {
//        User savedUser = userRepo.save(mapper.createUserEntity(userRequest));
//        return mapper.createUserResponse(savedUser);
//    }

    @Override
    public UserResponse getUserById(Long id) {
        User user =  userRepo.findById(id).orElseThrow(() -> new RecordNotFoundException("No user found with id: " + id));
        return mapper.createUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepo.findAll().stream().map(mapper::createUserResponse).toList();
    }


}
