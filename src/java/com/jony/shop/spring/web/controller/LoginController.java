package com.jony.shop.spring.web.controller;

import com.jony.shop.spring.entity.User;
import com.jony.shop.spring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import static com.jony.shop.spring.commons.constent.SessionUtils.SESSION_EMAIL;

@Controller
public class LoginController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }


    /**
     * 登陆页面显示
     * @return
     */
    @RequestMapping(value = {"","login"},method = RequestMethod.GET)
    public String login(){
        return "login";
    }


    /**
     * 实现登陆逻辑
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email,
                        @RequestParam(required = true)String password,
                        HttpServletRequest request){
        User user = userService.login(email,password);
        if(user.getUsername()!=null){
            request.getSession().setAttribute(SESSION_EMAIL,email);
            logger.info("用户 {}登陆成功",email);
            return "redirect:/main";
        }
        //登陆失败返回login
        else{
            logger.warn("用户 {}登陆失败",email);
            return login();
        }
    }
}
