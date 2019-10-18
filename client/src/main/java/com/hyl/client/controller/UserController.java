package com.hyl.client.controller;

import com.hyl.biz.model.User;
import com.hyl.biz.model.query.UserQuery;
import com.hyl.biz.service.UserService;
import com.hyl.core.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("user/")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "loginUser", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public Result loginUser(HttpServletRequest request, UserQuery query) {
        Result result = new Result(true);
        User user = userService.selectBySingle(query);
        result.setData(user);
        return result;
    }

    @RequestMapping(value = "add", method = {RequestMethod.POST})
    public Result add(User user) {
        Result result = new Result(false);
        if (userService.add(user)) {
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public Result list(UserQuery query) {
        Result result = new Result(true);
        List<User> users = userService.selectByList(query);
        result.setData(users);
        return result;
    }

    @RequestMapping(value = "update", method = {RequestMethod.POST})
    public Result update(User user) {
        Result result = new Result(false);
        if (userService.update(user)) {
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(Long id) {
        Result result = new Result(false);
        if (userService.delete(id)) {
            result.setSuccess(true);
        }
        return result;
    }
}
