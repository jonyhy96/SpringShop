package com.jony.shop.spring.service.impl;

import com.jony.shop.spring.dao.UserDao;
import com.jony.shop.spring.entity.User;
import com.jony.shop.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(String email, String password) {
        return userDao.getUser(email, password);
    }
}
