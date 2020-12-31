package com.app.server.controller;

import com.app.server.model.Medicine;
import com.app.server.util.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicine")
public class MedicineController {

    // 拿药
    @RequestMapping(value = "getMedicine", method = RequestMethod.POST)
    public Result getMedicine(@RequestBody Medicine medicine) {

        return new Result();
    }

}
