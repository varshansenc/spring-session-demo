package com.shz.security.springmvc.controller;

import com.shz.security.springmvc.query.LoginQuery;
import com.shz.security.springmvc.entity.UserEntity;
import com.shz.security.springmvc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login",produces = "text/plain;charset=utf-8")
    public String login(LoginQuery loginQuery, HttpSession session){
        UserEntity userEntity = loginService.login(loginQuery);
        //存入session
        session.setAttribute(UserEntity.SESSION_USER_KEY, userEntity);
        return userEntity.getUsername() +"登录成功";
    }

    @GetMapping(value = "/logout",produces = {"text/plain;charset=UTF-8"})
    public String logout(HttpSession session){
        session.invalidate();
        return "退出成功";
    }

    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    public String r1(HttpSession session) {
        String fullname = null;
        Object obj = session.getAttribute(UserEntity.SESSION_USER_KEY);
        if (obj == null) {
            fullname = "匿名";
        } else {
            fullname = ((UserEntity) obj).getFullname();
        }
        return fullname + "访问资源r1";
    }

    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    public String r2(HttpSession session) {
        String fullname = null;
        Object obj = session.getAttribute(UserEntity.SESSION_USER_KEY);
        if (obj == null) {
            fullname = "匿名";
        } else {
            fullname = ((UserEntity) obj).getFullname();
        }
        return fullname + " 访问资源2";
    }
}
