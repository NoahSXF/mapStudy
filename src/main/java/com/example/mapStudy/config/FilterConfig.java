package com.example.mapStudy.config;

import com.example.mapStudy.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.config
 * @Author: Noah
 * @CreateTime: 2022-11-10  20:02
 * @Description: 注册自定义Filter
 * @Version: 1.0
 */
//@Configuration
public class FilterConfig {
//    @Bean
    public FilterRegistrationBean registrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new MyFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}

