package com.controller;

import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;


    @RequestMapping("menu")
    public @ResponseBody
    List selectAll() {
        return menuService.selectAllMenus();
    }
}
