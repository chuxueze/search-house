package com.jiangyx.soufang.service;

import com.jiangyx.soufang.domain.User;

/**
 * 用户服务
 */
public interface IUserService {

    User findUserByName(String username);
}
