package com.jiangyx.soufang.controller.admin;

import com.google.gson.Gson;
import com.jiangyx.soufang.base.ApiResponse;
import com.jiangyx.soufang.dto.QiNiuPutResult;
import com.jiangyx.soufang.service.house.IQiNiuService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 后台管理公职器
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IQiNiuService qiNiuService;

    @Autowired
    private Gson gson;

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
        try {
            InputStream inputStream = file.getInputStream();
            Response response = qiNiuService.uploadFile(inputStream);
            if (response.isOK()) {
                QiNiuPutResult qiNiuPutResult = gson.fromJson(response.bodyString(), QiNiuPutResult.class);
                return ApiResponse.ofSuccess(response);
            } else {
                return ApiResponse.ofMessage(response.statusCode, response.getInfo());
            }
        } catch (QiniuException e) {
          Response response = e.response;
            try {
                return ApiResponse.ofMessage(response.statusCode, response.bodyString());
            } catch (QiniuException e1) {
                e1.printStackTrace();
                return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
