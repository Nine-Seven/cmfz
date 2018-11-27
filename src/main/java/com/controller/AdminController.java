package com.controller;

import com.entity.Admin;
import com.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public @ResponseBody
    boolean login(Admin admin, HttpSession session, String code) {
        if (!session.getAttribute("code").equals(code)) {
            return false;
        }
        Admin login = adminService.login(admin);
        if (login != null && login.getId() != 0) {
            session.setAttribute("login", login);
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session) {
        session.removeAttribute("login");
        return "login";
    }
}
