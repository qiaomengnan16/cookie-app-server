package com.app.server.controller;

import com.app.server.model.User;
import com.app.server.service.UserService;
import com.app.server.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    // 注册
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Result register(@RequestBody User user) {
        this.userService.register(user);
        return new Result();
    }

    // 登录
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(@RequestBody User user) {
        return new Result(this.userService.login(user));
    }

}
