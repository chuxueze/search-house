package com.jiangyx.soufang.controller.admin;

import com.jiangyx.soufang.base.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

    @GetMapping("/add/house")
    public String addHousePage() {
        return "admin/house-add";
    }

    @PostMapping(value = "/upload/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ApiResponse uploadPhoto(@RequestParam("file")MultipartFile file) {
        if (file.isEmpty()) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }
        String filename = file.getOriginalFilename();
        File target = new File("/home/jiangyx/IdeaProjects/sou-fang/tmp/" + filename);
        try {
            file.transferTo(target);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
        }
        return ApiResponse.ofSuccess(null);
    }
}
