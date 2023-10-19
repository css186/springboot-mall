package com.brian.springbootmall.service.impl;

import com.brian.springbootmall.dao.UserDao;
import com.brian.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


}
