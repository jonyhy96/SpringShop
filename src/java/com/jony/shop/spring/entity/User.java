package com.jony.shop.spring.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component(value = "user")
public class User implements Serializable {
    private String username;
    private String password;
    private String useremail;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", useremail='" + useremail + '\'' +
                '}';
    }
}
