package com.jony.shop.spring.dao.impl;

import com.jony.shop.spring.dao.UserDao;
import com.jony.shop.spring.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public User getUser(String email,String password) {
        logger.debug("调用 getUser(),email: {},password: {}",email,password);
        User user = new User();
        if("hy352144278@gmail.com".equals(email)){
            if("admin".equals(password)) {
                user.setUseremail(email);
                user.setUsername("jony");
                logger.info("成功获取 \"{}\" 的用户信息",user.getUsername());
            }
        }else{
            logger.warn("获取 \"{}\" 的用户信息失败",email);
        }
        return user;
    }
}
