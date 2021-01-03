package com.app.server.controller;

import com.app.server.model.Illness;
import com.app.server.service.IllnessService;
import com.app.server.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("illness")
public class IllnessController {

    @Resource
    private IllnessService illnessService;

    // 看病
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Result list() {
        return new Result(this.illnessService.list());
    }

    // 看病
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public Result lookIllness(@RequestBody Illness illness) {
        return new Result(this.illnessService.get(illness.getId()));
    }

    // 看病
    @RequestMapping(value = "confirm", method = RequestMethod.POST)
    public Result confirm(@RequestBody Illness illness) {
        this.illnessService.confirm(illness.getId());
        return new Result();
    }

}
