package com.example.mapStudy.config;

import com.example.mapStudy.interceptor.LogCostInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.config
 * @Author: Noah
 * @CreateTime: 2022-11-10  20:44
 * @Description: 注册拦截器
 * @Version: 1.0
 */
//@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

//    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogCostInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}

