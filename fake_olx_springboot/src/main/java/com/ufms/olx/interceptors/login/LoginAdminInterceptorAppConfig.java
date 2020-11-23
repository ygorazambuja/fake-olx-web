package com.ufms.olx.interceptors.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class LoginAdminInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    LoginAdminInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
            .addInterceptor(interceptor)
            .addPathPatterns("/api/pessoa", "/api/pessoa/*");
    }
}
