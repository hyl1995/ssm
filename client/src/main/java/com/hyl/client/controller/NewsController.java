package com.hyl.client.controller;

import com.hyl.biz.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //restful
@RequestMapping("")
public class NewsController {
    @Autowired
    NewsService newsService;
//    @RequestMapping(value = "/listnews", method = RequestMethod.GET)
//    public Object listNews(News news){
//        List<News> newsList = newsService.listone(news);
//        return newsList;
//    }
//    @RequestMapping(value = "/addnews",method = RequestMethod.POST)
//    public ModelAndView addnews(MultipartFile file, News news,
//                                @RequestParam("n_title") String n_title,
//                                @RequestParam("n_content") String n_content,
//                                @RequestParam("n_author") String n_author,
//                                @RequestParam("n_time") Date n_time
//    ) throws IOException {
//        if (file!=null){
//            String filePath="F:\\毕设相关\\毕设前端\\user\\img\\news";
////            String filePath = request.getServletContext().getRealPath("/image");//保存图片的路径
//            String originalFilename=file.getOriginalFilename();
//            //新的文件名字
//            String newFileName = UUID.randomUUID()+originalFilename;
//            //封装上传文件位置的全路径
//            File targetFile = new File(filePath,newFileName);
//            //把本地文件上传到封装上传文件位置的全路径
//            file.transferTo(targetFile);
//            news.setN_i_address(newFileName);
//        }
//        news.setN_author(n_author);
//        news.setN_content(n_content);
//        news.setN_time(n_time);
//        news.setN_titie(n_title);
//        news.setN_id(UUID.randomUUID().toString()+System.currentTimeMillis());
//        int addnews = newsService.add(news);
//        ModelAndView newmap =new ModelAndView();
//        if (addnews==1){
//            newmap.addObject("jg","添加成功");
//        }else {
//            newmap.addObject("jg","添加失败");
//        }
//        newmap.setViewName("jg");
//        return newmap;
//    }
//    @RequestMapping(value = "/updatenews",method = RequestMethod.POST)
//    public ModelAndView updatenews(MultipartFile file, News news,
//                                   @RequestParam("n_id") String n_id,
//                                   @RequestParam("n_title") String n_title,
//                                   @RequestParam("n_content") String n_content,
//                                   @RequestParam("n_author") String n_author,
//                                   @RequestParam("n_time") Date n_time
//    ) throws IOException {
//        if (file!=null){
//            String filePath="F:\\毕设相关\\毕设前端\\user\\img\\news";
////            String filePath = request.getServletContext().getRealPath("/image");//保存图片的路径
//            String originalFilename=file.getOriginalFilename();
//            //新的文件名字
//            String newFileName = UUID.randomUUID()+originalFilename;
//            //封装上传文件位置的全路径
//            File targetFile = new File(filePath,newFileName);
//            //把本地文件上传到封装上传文件位置的全路径
//            file.transferTo(targetFile);
//            news.setN_i_address(newFileName);
//        }
//        news.setN_author(n_author);
//        news.setN_content(n_content);
//        news.setN_time(n_time);
//        news.setN_titie(n_title);
//        news.setN_id(n_id);
//        int addnews = newsService.update(news);
//        ModelAndView newmap =new ModelAndView();
//        if (addnews==1){
//            newmap.addObject("jg","修改成功");
//        }else {
//            newmap.addObject("jg","修改失败");
//        }
//        newmap.setViewName("jg");
//        return newmap;
//    }
//
//    @RequestMapping(value = "/deletenews",method = RequestMethod.GET)
//    public ModelAndView delete(News news)
//    {
//        newsService.delete(news);
//        ModelAndView map =new ModelAndView();
//        map.setViewName("jg");
//        map.addObject("jg","删除成功");
//        return map;
//    }

}
