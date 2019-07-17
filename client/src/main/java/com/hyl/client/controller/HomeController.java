package com.hyl.client.controller;

import com.hyl.core.model.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("homeController")
@RequestMapping("/home")
public class HomeController extends BaseController {

    @RequestMapping(value = "/listnews",method = RequestMethod.GET)
    public Object listnews( String o) {
        System.out.println(o);
        String s = "home";
        Integer  i = 34;
        List list = new ArrayList();
        list.add(23);
        return i;
    }
}
