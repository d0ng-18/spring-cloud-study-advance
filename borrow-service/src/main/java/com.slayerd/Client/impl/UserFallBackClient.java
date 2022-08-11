package com.slayerd.Client.impl;

import com.slayerd.Client.UserClient;
import com.slayerd.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserFallBackClient implements UserClient {
    @Override
    public User findUserById(int uid) {
        User user = new User();
        user.setName("补救措施");
        return user;
    }
}
