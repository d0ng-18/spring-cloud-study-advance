package com.slayerd.controller;

import com.slayerd.entity.User;
import com.slayerd.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RefreshScope
public class UserController {

    @Resource
    private UserService userService;

    @Value("${test.syd}")
    private String value;

    //这里以RESTFul风格为例
    @RequestMapping("/user/{uid}")
    public User findUserById(@PathVariable("uid") int uid) {
        System.out.println("我被调用了");
        System.out.println(value);
        return userService.getUserById(uid);
    }
}
