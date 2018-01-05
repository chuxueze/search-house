package com.jiangyx.soufang.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于角色的登录入口控制器
 */
public class LoginUrlEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private PathMatcher pathMatcher = new AntPathMatcher();
    private final Map<String, String> authEntrPointMap;

    public LoginUrlEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
        authEntrPointMap = new HashMap<>();
        // 普通用户登录入口
        authEntrPointMap.put("/user/**", "/user/login");
        // 管理员登录入口
        authEntrPointMap.put("/admin/**", "/admin/login");
    }

    /**
     * 根据请求跳转到指定页面, 父类默认是跳转到loginFormUrl
     * @param request
     * @param response
     * @param exception
     * @return
     */
    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        // 获取访问的路径
        String url = request.getRequestURI().replace(request.getContextPath(), "");
        // 遍历映射，如果符合要求则跳转到指定登录界面
        for (Map.Entry<String, String> entry : this.authEntrPointMap.entrySet()) {
            if (this.pathMatcher.match(entry.getKey(), url)) {
                return entry.getValue();
            }
        }
        return super.determineUrlToUseForThisRequest(request, response, exception);
    }
}
