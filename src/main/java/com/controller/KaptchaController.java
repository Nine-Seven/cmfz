package com.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class KaptchaController {

    @Autowired
    private Producer producer;

    @RequestMapping("/code")
    public void getCode(HttpSession session, HttpServletResponse response) throws IOException {
        //创建文本
        String text = producer.createText();
        //放入session作用域
        session.setAttribute("code", text);
        //创建图片
        BufferedImage image = producer.createImage(text);
        //放入响应输出流
        ImageIO.write(image, "jpg", response.getOutputStream());

    }
}
