package com.brian.springbootmall.service;

import com.brian.springbootmall.dto.UserRegisterRequest;
import com.brian.springbootmall.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

}
