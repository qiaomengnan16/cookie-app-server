package com.app.server.controller;

import com.app.server.model.User;
import com.app.server.model.UserMedicine;
import com.app.server.service.UserService;
import com.app.server.util.Result;
import com.app.server.util.SessionUtils;
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

    @Resource
    private SessionUtils sessionUtils;

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

    // 获取当前登录用户
    @RequestMapping(value = "current", method = RequestMethod.POST)
    public Result current() {
        return new Result(sessionUtils.getAuth());
    }

    // 用户领药历史
    @RequestMapping(value = "/illness/list", method = RequestMethod.POST)
    public Result userIllnessList() {
        return new Result(this.userService.userIllnessList());
    }

    // 用户领药历史
    @RequestMapping(value = "/medicine/list", method = RequestMethod.POST)
    public Result userMedicineList() {
        return new Result(this.userService.userMedicineList());
    }

    // 用户确认领取药物
    @RequestMapping(value = "/medicine/confirm", method = RequestMethod.POST)
    public Result userMedicineConfirm(@RequestBody UserMedicine userMedicine) {
        this.userService.userMedicineConfirm(userMedicine.getId());
        return new Result();
    }

}
