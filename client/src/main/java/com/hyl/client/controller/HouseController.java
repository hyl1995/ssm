package com.hyl.client.controller;

import com.hyl.biz.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HouseController {
    @Autowired
    HouseService houseService;
//    @RequestMapping(value = "listHouse",method = {RequestMethod.GET},produces = "application/json;charset=UTF-8")
//    public Object listHouse(House house){
//        List<House> houses =houseService.List(house);
//        return houses;
//    }
//    @RequestMapping(value = "addHouse",method = RequestMethod.POST)
//    public ModelAndView addHouse(@RequestParam("file") MultipartFile file,
//                                 @RequestParam("h_feature") String h_feature,
//                                 @RequestParam("h_type") String h_type,
//                                 @RequestParam("h_area") String h_area,
//                                 @RequestParam("h_size") Integer h_size,
//                                 @RequestParam("h_orientation") String h_orientation,
//                                 @RequestParam("h_price") Integer h_price,
//                                 @RequestParam("h_way") String h_way,
//                                 House house) throws IOException {
//
//        if (file!=null){
////            String filePath = request.getServletContext().getRealPath("/image");//保存图片的路径
//            String filePath="F:\\毕设相关\\毕设前端\\user\\img\\houses";
//            String originalFilename=file.getOriginalFilename();
//            //新的文件名字
//            String newFileName = UUID.randomUUID()+originalFilename;
//            //封装上传文件位置的全路径
//            File targetFile = new File(filePath,newFileName);
//            //把本地文件上传到封装上传文件位置的全路径
//            file.transferTo(targetFile);
//            house.setH_vacancy(newFileName);
//        }
//        house.setH_way(h_way);
//        house.setH_type(h_type);
//        house.setH_size(h_size);
//        house.setH_price(h_price);
//        house.setH_orientation(h_orientation);
//        house.setH_id(UUID.randomUUID().toString()+System.currentTimeMillis());
//        house.setH_area(h_area);
//        house.setH_feature(h_feature);
//        house.setH_zt("待租中");
//        int add = houseService.Add(house);
//        ModelAndView map =new ModelAndView();
//        if (add==1){
//            houseService.houseZtUpdate(house);
//            map.addObject("jg","添加成功");}
//        else {map.addObject("jg","添加失败");}
//        map.setViewName("jg");
//        return  map;
//        }
//
//
//    @RequestMapping(value = "listHouseOne",method = {RequestMethod.GET},produces = "application/json;charset=UTF-8")
//    public Object listHouseOne(House house,@RequestParam("h_id") String h_id){
//        System.out.println(h_id);
//        house.setH_id(h_id);
//        List<House> houses =houseService.List(house);
//        return houses;
//    }
//
//    @RequestMapping(value = "ZtAdd",method = RequestMethod.GET ,produces = "application/json;charset=UTF-8")
//    public Object ZtAdd(House house,
//                        String time2,
//                         String o_number,
//                        int ztm){
//        if (time2!=""&&o_number!=""){
//            String h_zt=time2+"看房，联系号码为："+o_number;
//            house.setH_zt(h_zt);
//        }
//        int s = houseService.houseZtUpdate(house);
//        if (ztm==123){
//            return "修改成功";
//        }
//        return s;
//    }
//    @RequestMapping(value = "updateHouse",method = RequestMethod.POST)
//    public ModelAndView updateHouse(
////            @RequestParam("file") MultipartFile file,
//            @RequestParam("h_vacancy") String h_vacancy,
//                                 @RequestParam("h_feature") String h_feature,
//                                 @RequestParam("h_type") String h_type,
//                                    @RequestParam("h_id") String h_id,
//                                 @RequestParam("h_area") String h_area,
//                                 @RequestParam("h_size") Integer h_size,
//                                 @RequestParam("h_orientation") String h_orientation,
//                                 @RequestParam("h_price") Integer h_price,
//                                 @RequestParam("h_way") String h_way,
//                                 House house) throws IOException {
//
////        if (file!=null){
//////            String filePath = request.getServletContext().getRealPath("/image");//保存图片的路径
////            String filePath="F:\\毕设相关\\毕设前端\\user\\img\\houses";
////            String originalFilename=file.getOriginalFilename();
////            //新的文件名字
////            String newFileName = UUID.randomUUID()+originalFilename;
////            //封装上传文件位置的全路径
////            File targetFile = new File(filePath,newFileName);
////            //把本地文件上传到封装上传文件位置的全路径
////            file.transferTo(targetFile);
////            house.setH_vacancy(newFileName);
////        }
//        house.setH_way(h_way);
//        house.setH_type(h_type);
//        house.setH_size(h_size);
//        house.setH_price(h_price);
//        house.setH_orientation(h_orientation);
//        house.setH_id(h_id);
//        house.setH_area(h_area);
//        house.setH_feature(h_feature);
//        int add = houseService.Update(house);
//        ModelAndView map =new ModelAndView();
//        if (add==1){map.addObject("jg","修改成功");}
//        else {map.addObject("jg","修改失败");}
//        map.setViewName("jg");
//        return  map;
//    }
//    @RequestMapping(value = "deleteHouse",method = RequestMethod.GET)
//    public ModelAndView deleteHouse(House house){
//        houseService.Delete(house);
//        ModelAndView map=new ModelAndView();
//        map.addObject("jg","删除成功");
//        map.setViewName("jg");
//        return map;
//    }
}
