package com.brian.springbootmall.controller;

import com.brian.springbootmall.dto.UserLoginRequest;
import com.brian.springbootmall.dto.UserRegisterRequest;
import com.brian.springbootmall.model.User;
import com.brian.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    // Create a user
    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        // Get id from created user
        Integer userId = userService.register(userRegisterRequest);

        // Get user data from the id
        User user = userService.getUserById(userId);

        // return to frontend
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {

        User user = userService.login(userLoginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }



}
