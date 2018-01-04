package com.jiangyx.soufang.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理公职器
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping(value = "/center")
    public String adminCenterPage() {
        return "admin/center";
    }

    @GetMapping(value = "/welcome")
    public String welcomePage() {
        return "admin/welcome";
    }

    @GetMapping(value = "/login")
    public String adminLoginPage() {
        return "admin/login";
    }
}
