package com.app.server.controller;

import com.app.server.model.Illness;
import com.app.server.service.IllnessService;
import com.app.server.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("illness")
public class IllnessController {

    @Autowired
    private IllnessService illnessService;

    // 看病
    @RequestMapping(value = "lookIllness", method = RequestMethod.POST)
    public Result lookIllness(@RequestBody Illness illness) {

        return new Result();
    }

}
