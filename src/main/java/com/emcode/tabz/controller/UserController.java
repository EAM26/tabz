package com.emcode.tabz.controller;

import com.emcode.tabz.dto.UserResponse;
import com.emcode.tabz.model.User;
import com.emcode.tabz.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping(value = "/me")
    public ResponseEntity<UserResponse> getLoggedInUser(Authentication authentication) {
        System.out.println("controllermethod getLoggedInUser called");
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(userService.getUserById(user.getId()));

    }
}
