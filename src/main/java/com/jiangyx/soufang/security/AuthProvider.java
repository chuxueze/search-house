package com.jiangyx.soufang.security;

import com.jiangyx.soufang.domain.User;
import com.jiangyx.soufang.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * 自定义认证实现
 */
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private IUserService userService;

    private final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User user = userService.findUserByName(username);
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        }
        if (this.passwordEncoder.isPasswordValid(user.getPassword(), password, user.getId())) {
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorityList());
        }
        throw new BadCredentialsException("认证异常");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
