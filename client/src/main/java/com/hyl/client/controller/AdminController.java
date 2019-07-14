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
        Result result = new Result();
//        List<Admin> admins = adminService.sele(query);
        Admin admin = adminService.selectBySingle(query);
        result.setData(admin);
        return result;
    }

    @RequestMapping(value = "update",method = RequestMethod.GET)
    public Object update(Admin admin){
        int s=adminService.update(admin);
        return s;
    }

    @RequestMapping(value = "registered",method = RequestMethod.POST)
    public Object add(Admin admin){
        int s =adminService.add(admin);
        return s;
    }
}
