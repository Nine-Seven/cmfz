package com.controller;


import com.entity.Carousel;
import com.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;
import java.util.UUID;

@RestController
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @RequestMapping("selectCarousels")
    public Map selectCarousels(int page, int rows) {
        return carouselService.selectAll(page, rows);
    }

    @RequestMapping("updateCarousel")
    public boolean updateCarousel(Carousel record) {
        try {
            carouselService.updateStatus(record);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String rename(String picFileName) {
        String uuid = UUID.randomUUID().toString();
        String subFile = picFileName.substring(picFileName.lastIndexOf('.'));
        return uuid + subFile;
    }


    @RequestMapping("addCarousel")
    public boolean addCarousel(Carousel carousel, MultipartFile pic, HttpServletRequest request) {
        //获取文件名
        String picFileName = pic.getOriginalFilename();
        //获取images绝对路径
        String path = request.getServletContext().getRealPath("img");
        //文件重命名
        String newFileName = rename(picFileName);
        //创建File对象
        File destFile = new File(path + "/" + newFileName);
        try {
            pic.transferTo(destFile);
            //最后添加,防止拷贝文件出错
            carousel.setImgPath(newFileName);
            carouselService.insert(carousel);
        } catch (Exception e) {
            //添加出错后删除拷贝的文件
            destFile.delete();
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
