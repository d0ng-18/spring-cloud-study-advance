package com.slayerd.service.impl;

import com.slayerd.entity.User;
import com.slayerd.mapper.UserMapper;
import com.slayerd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(int uid) {
        return userMapper.getUserById(uid);
    }
}
