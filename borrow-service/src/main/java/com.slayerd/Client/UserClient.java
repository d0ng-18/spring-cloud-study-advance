package com.slayerd.Client;

import com.slayerd.Client.impl.UserFallBackClient;
import com.slayerd.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "userservice",fallback = UserFallBackClient.class)
public interface UserClient {
    @RequestMapping("/user/{uid}")
    User findUserById(@PathVariable("uid") int uid);
}
