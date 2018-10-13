package com.jony.shop.spring.service.impl;

import com.jony.shop.spring.commons.context.SpringContext;
import com.jony.shop.spring.dao.UserDao;
import com.jony.shop.spring.entity.User;
import com.jony.shop.spring.service.UserService;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    private UserDao userDao = SpringContext.getBean(UserDao.class);
    public User login(String email, String password) {
        return userDao.getUser(email, password);
    }
}
