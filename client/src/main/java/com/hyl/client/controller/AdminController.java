package com.hyl.client.controller;

import com.hyl.biz.model.Admin;
import com.hyl.biz.model.query.AdminQuery;
import com.hyl.biz.service.AdminService;
import com.hyl.core.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public Result login(AdminQuery query){
        Result result = new Result(true);
        query.setFilter("phone='1234567891'");
        Admin admin = adminService.selectBySingle(query);
        result.setData(admin);
        return result;
    }

    @RequestMapping(value = "update",method = RequestMethod.GET)
    public Result update(Admin admin){
        Result result = new Result(false);
        if (adminService.update(admin) == 1) {
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public Result add(Admin admin){
        Result result = new Result(false);
        if (adminService.add(admin) == 1) {
            result.setSuccess(true);
        }
        return result;
    }
}
