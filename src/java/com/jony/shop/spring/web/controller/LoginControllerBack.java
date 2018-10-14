package com.jony.shop.spring.web.controller;

import com.jony.shop.spring.commons.context.SpringContext;
import com.jony.shop.spring.commons.utils.CookieUtils;
import com.jony.shop.spring.entity.User;
import com.jony.shop.spring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Deprecated
public class LoginControllerBack extends HttpServlet {
    private static final String COOKIE_NAME_USER_INFO="userInfo";

    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    private UserService userService =SpringContext.getBean("userService");
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cookie = CookieUtils.getCookieValue(req,COOKIE_NAME_USER_INFO);
        if(!cookie.equals("")){
            String[] strings = cookie.split(":");
            req.setAttribute("email",strings[0]);
            req.setAttribute("password",strings[1]);
            req.setAttribute("remember","checked");
        }
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password= req.getParameter("password");
        String remember = req.getParameter("remember");
        User admin = userService.login(email,password);
        if(admin.getUsername()!=null){
            if(remember!=null){
                CookieUtils.setCookie(req,resp,COOKIE_NAME_USER_INFO,String.format("%s:%s",email,password),(7*24*3600));
                logger.debug("设置cookie userinfo: {}",String.format("%s:%s",email,password));
            }else{
                CookieUtils.deleteCookie(req,resp,COOKIE_NAME_USER_INFO);
            }
            logger.info("登陆成功，跳转到main.jsp");
            resp.sendRedirect("main.jsp");
        }else{
            logger.warn("登陆失败，重载index.jsp");
            req.setAttribute("message","登陆失败");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
    }
}
