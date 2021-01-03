package com.app.server.controller;

import com.app.server.model.Medicine;
import com.app.server.service.MedicineService;
import com.app.server.util.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("medicine")
public class MedicineController {

    @Resource
    private MedicineService medicineService;

    // 拿药
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Result list() {
        return new Result(this.medicineService.list());
    }

    // 拿药
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public Result get(@RequestBody Medicine medicine) {
        return new Result(this.medicineService.get(medicine.getId()));
    }




}
