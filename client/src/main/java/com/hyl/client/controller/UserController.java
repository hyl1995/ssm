package com.hyl.client.controller;

import com.hyl.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

//    @RequestMapping(value = "loginUser", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
//    public Object loginUser(HttpServletRequest request, User user, @RequestParam("u_id") String u_id, @RequestParam("u_pass") String u_pass) {
//        user.setU_pass(u_pass);
//        user.setU_id(u_id);
//        List<User> users = userService.userListOne(user);
//        return users;
//    }
//
//    @RequestMapping(value = "registeredUser", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
//    public Object registeredUser(User user,
//                                 @RequestParam("u_id") String u_id,
//                                 @RequestParam("u_pass") String u_pass,
//                                 @RequestParam("u_number") String u_number,
//                                 @RequestParam("u_name") String u_name,
//                                 @RequestParam("u_birthday") Date u_birthday,
//                                 @RequestParam("u_cardID") String u_cardID,
//                                 @RequestParam("u_me") String u_me,
//                                 @RequestParam("u_sex") String u_sex) {
//        user.setU_id(u_id);
//        user.setU_pass(u_pass);
//        user.setU_birthday(u_birthday);
//        user.setU_idcard(u_cardID);
//        user.setU_name(u_me);
//        user.setU_nickname(u_name);
//        user.setU_number(u_number);
//        user.setU_sex(u_sex);
//        user.setU_status("正常");
//        int s = userService.userAdd(user);
//        return s;
//    }
//
//    @RequestMapping(value = "listUser", method = RequestMethod.GET)
//    public Object listUser(User user) {
//        List<User> users = userService.userListOne(user);
//        return users;
//    }
//
//    @RequestMapping(value = "updateUser", method = RequestMethod.GET)
//    public Object updateUser(User user) {
//        int s = userService.userUpdate(user);
//        return s;
//    }
}
