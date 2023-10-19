package com.brian.springbootmall.dao;

import com.brian.springbootmall.dto.UserRegisterRequest;
import com.brian.springbootmall.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

}
