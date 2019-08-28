package com.rnzhiw.ibicycle.configuration;

import com.rnzhiw.ibicycle.interceptor.AuthInterceptor;
import com.rnzhiw.ibicycle.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 系统配置器
 *
 * @author fuenhui
 * @date 2018/11/29
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //登录注册拦截器
        InterceptorRegistration loginReg = registry.addInterceptor(new LoginInterceptor());
        loginReg.addPathPatterns("/**");
        //不受登录拦截控制的请求
        loginReg.excludePathPatterns("/login", "/error").excludePathPatterns("/static/**");

        //权限拦截器
        InterceptorRegistration authReg = registry.addInterceptor((HandlerInterceptor)new AuthInterceptor());
        authReg.addPathPatterns("/static/**").excludePathPatterns("/static/**");
        //不受权限控制的请求
        authReg.excludePathPatterns("/", "/login","/error");

        super.addInterceptors(registry);
    }
}
