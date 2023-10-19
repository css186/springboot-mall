package com.brian.springbootmall.service.impl;

import com.brian.springbootmall.dao.UserDao;
import com.brian.springbootmall.dto.UserRegisterRequest;
import com.brian.springbootmall.model.User;
import com.brian.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
