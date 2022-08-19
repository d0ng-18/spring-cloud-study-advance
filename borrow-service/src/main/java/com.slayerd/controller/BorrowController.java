package com.slayerd.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.slayerd.entity.UserBorrowDetail;
import com.slayerd.service.BorrowService;
import netscape.javascript.JSObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;

@RestController
public class BorrowController {

    @Resource
    BorrowService service;

    @RequestMapping("/borrow/{uid}")
    UserBorrowDetail findUserBorrows(@PathVariable("uid") int uid, HttpServletRequest request) {
        return service.getUserBorrowDetailByUid(uid);
    }

    @RequestMapping("/borrow/take/{uid}/{bid}")
    JSONObject borrow(@PathVariable("uid") int uid,
                      @PathVariable("bid") int bid){
        service.doBorrow(uid, bid);

        JSONObject object = new JSONObject();
        object.put("code", "200");
        object.put("success", false);
        object.put("message", "借阅成功！");
        return object;
    }

    @RequestMapping("/blocked")
    JSONObject blocked() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 403);
        jsonObject.put("ErrMsg", "SB，慢点请求");
        jsonObject.put("success", false);
        return jsonObject;
    }

    //如果对这个链路进行限流，也是会执行except方法，总归到底，限流的最终都是抛出限流异常BlockException
    //如果blockhandler和fallback同时存在，那么出现限流异常会优先blockhandler，其他异常则会执行fallback
    @RequestMapping("/test")
    @SentinelResource(value = "test",
            fallback = "except",    //fallback指定出现异常时的替代方案
            exceptionsToIgnore = IOException.class)
    //忽略那些异常，也就是说这些异常出现时不使用替代方案
    String test() {
        throw new RuntimeException("HelloWorld！");
    }

    //替代方法必须和原方法返回值和参数一致，最后可以添加一个Throwable作为参数接受异常
    String except(Throwable t) {
        return t.getMessage();
    }


    //热点参数限流,对携带某一个参数的请求进行限流，也可以对这个参数进行除了某个特定值之外的值进行限流
    @RequestMapping("/test1")
    @SentinelResource("test1")
    //注意这里需要添加@SentinelResource才可以，用户资源名称就使用这里定义的资源名称
    String findUserBorrows2(@RequestParam(value = "a", required = false) String a,
                            @RequestParam(value = "b", required = false) String b,
                            @RequestParam(value = "c", required = false) String c) {
        return "请求成功！a = " + a + ", b = " + b + ", c = " + c;
    }


}