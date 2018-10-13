package com.jony.shop.spring.service;

import com.jony.shop.spring.entity.User;

public interface UserService {
    public User login(String email,String password);
}
