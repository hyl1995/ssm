package com.hyl.client.controller;

import com.hyl.biz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class OrderController {
    @Autowired
    OrderService orderService;
//    @RequestMapping(value = "addOrder" ,method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
//    public Object addOrder(
//            @RequestParam("u_id") String u_id,
//            @RequestParam("h_id") String h_id,
//            @RequestParam("o_starttime") Date o_starttime,
//            @RequestParam("time2") String time2,
//            @RequestParam("o_number") String o_number,
//            Order order){
//        order.setO_id(UUID.randomUUID()+""+o_starttime.toString());
//        order.setO_starttime(o_starttime);
//        order.setO_status(time2+"申请看房，联系号码为："+o_number);
//        order.setU_id(u_id);
//        order.setH_id(h_id);
//        int s = orderService.orderAdd(order);
//        if (s==1){return 1;}
//        else {return 2;}
//    }
//    @RequestMapping(value = "listOrder", method = {RequestMethod.GET},produces = "application/json;charset=UTF-8")
//    public Object listOrder(Order order){
//        List<Order> orders=orderService.orderListone(order);
//        return orders;
//    }
//        @RequestMapping(value = "updateOrder",method = {RequestMethod.GET})
//    public Object updateOrder(Order order ){
//        int a=orderService.orderUpdate(order);
//        return a;
//    }
//    @RequestMapping(value="deleteOrder",method = RequestMethod.GET)
//    public ModelAndView deleteOrder(Order order){
//        orderService.orderDelete(order);
//        ModelAndView map =new ModelAndView();
//        map.addObject("jg","删除成功");
//        map.setViewName("jg");
//        return map;
//    }
//    @RequestMapping(value = "ZtAdda",method = RequestMethod.GET ,produces = "application/json;charset=UTF-8")
//    public Object ZtAdda(Order order){
//        orderService.orderUpzt(order);
//        ModelAndView map=new ModelAndView();
//        map.addObject("jg","修改成功");
//        map.setViewName("jg");
//        return map;
//    }
}
