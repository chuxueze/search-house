package com.jiangyx.soufang.config;

import com.jiangyx.soufang.security.AuthProvider;
import com.jiangyx.soufang.security.LoginAuthFailHandler;
import com.jiangyx.soufang.security.LoginUrlEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity  // 允许开启security
@EnableGlobalMethodSecurity // 允许开启方法权限验证
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * HTTP权限控制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置资源访问权限
        http.authorizeRequests()
                .antMatchers("/admin/login").permitAll()    // 管理员登录界面
                .antMatchers("/static/**").permitAll()      // 静态资源
                .antMatchers("/user/login").permitAll()     // 用户登录
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/user/**").hasAnyRole("ADMIN", "USER")
                .and()
                // 登录表单配置
                .formLogin()
                .loginProcessingUrl("/login")   // 配置角色登录处理入口
                .failureHandler(authFailHandler())
                .and()
                // 登出配置
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/page")
                .deleteCookies("JESSIONID") // 删除Cookies
                .invalidateHttpSession(true)    // 使会话失效
                .and()
                // 异常处理配置
                .exceptionHandling()
                .authenticationEntryPoint(urlEntryPoint())
                .accessDeniedPage("/403");

        http.csrf().disable();
        // 开启同源策略，不然iframe会报错
        http.headers().frameOptions().sameOrigin();
    }

    /**
     * 自定义认证策略(只能注入一个)
     * @param managerBuilder
     */
    public void configGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception {
        // 注入内存用户名
//        managerBuilder.inMemoryAuthentication().withUser("root").password("root").roles("ADMIN").and();
        managerBuilder.authenticationProvider(authProvider()).eraseCredentials(true);
    }

    @Bean
    public AuthProvider authProvider() {
        return new AuthProvider();
    }

    @Bean
    public LoginUrlEntryPoint urlEntryPoint() {
        return new LoginUrlEntryPoint("/user/login");
    }

    @Bean
    public LoginAuthFailHandler authFailHandler() {
        return new LoginAuthFailHandler(urlEntryPoint());
    }
}
