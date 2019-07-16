package com.hyl.client.controller;

import com.hyl.biz.model.House;
import com.hyl.biz.model.query.HouseQuery;
import com.hyl.biz.service.HouseService;
import com.hyl.core.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("house/")
public class HouseController {
    @Autowired
    HouseService houseService;

    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public Result list(HouseQuery query) {
        Result result = new Result(true);
        List<House> houses = houseService.selectByList(query);
        result.setData(houses);
        return result;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result add(@RequestParam("file") MultipartFile file, House house) throws IOException {
        Result result = new Result(false);
        if (file != null) {
//            String filePath = request.getServletContext().getRealPath("/image");//保存图片的路径
            String filePath = "F:\\毕设相关\\毕设前端\\user\\img\\houses";
            String originalFilename = file.getOriginalFilename();
            //新的文件名字
            String newFileName = UUID.randomUUID() + originalFilename;
            //封装上传文件位置的全路径
            File targetFile = new File(filePath, newFileName);
            //把本地文件上传到封装上传文件位置的全路径
            file.transferTo(targetFile);
        }
        if (houseService.add(house) == 1) {
            result.setSuccess(true);
        }
        return result;
    }


//    @RequestMapping(value = "listHouseOne", method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
//    public Object listHouseOne(House house, @RequestParam("h_id") String h_id) {
//        System.out.println(h_id);
//        house.setH_id(h_id);
//        List<House> houses = houseService.List(house);
//        return houses;
//    }
//
//    @RequestMapping(value = "ZtAdd", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public Object ZtAdd(House house,
//                        String time2,
//                        String o_number,
//                        int ztm) {
//        if (time2 != "" && o_number != "") {
//            String h_zt = time2 + "看房，联系号码为：" + o_number;
//            house.setH_zt(h_zt);
//        }
//        int s = houseService.houseZtUpdate(house);
//        if (ztm == 123) {
//            return "修改成功";
//        }
//        return s;
//    }
//
//    @RequestMapping(value = "updateHouse", method = RequestMethod.POST)
//    public ModelAndView updateHouse(
////            @RequestParam("file") MultipartFile file,
//            House house) throws IOException {
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
//        int add = houseService.Update(house);
//        ModelAndView map = new ModelAndView();
//        if (add == 1) {
//            map.addObject("jg", "修改成功");
//        } else {
//            map.addObject("jg", "修改失败");
//        }
//        map.setViewName("jg");
//        return map;
//    }
//
//    @RequestMapping(value = "delete", method = RequestMethod.GET)
//    public ModelAndView deleteHouse(HouseQuery query) {
//        houseService.delete("23");
//        ModelAndView map = new ModelAndView();
//        map.addObject("jg", "删除成功");
//        map.setViewName("jg");
//        return map;
//    }
}
