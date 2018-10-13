package com.jony.shop.spring.dao;

import com.jony.shop.spring.entity.User;

public interface UserDao {
    public User getUser(String email,String password);
}
