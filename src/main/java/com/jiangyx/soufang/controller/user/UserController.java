package com.jiangyx.soufang.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户控制器
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/center")
    public String userCenterPage() {
        return "user/center";
    }
}
