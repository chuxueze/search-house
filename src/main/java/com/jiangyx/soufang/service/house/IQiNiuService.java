package com.jiangyx.soufang.service.house;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;
import java.io.InputStream;

public interface IQiNiuService {

    /**
     * 以文件方式上传到七牛云
     * @param file                  文件对象
     * @return                      七牛响应对象
     * @throws QiniuException       七牛异常
     */
    Response uploadFile(File file) throws QiniuException;
    /**
     * 以文件流方式上传到七牛云
     * @param inputStream           文件流
     * @return                      七牛响应对象
     * @throws QiniuException       七牛异常
     */
    Response uploadFile(InputStream inputStream) throws QiniuException;
    Response delete(String key) throws QiniuException;
}
