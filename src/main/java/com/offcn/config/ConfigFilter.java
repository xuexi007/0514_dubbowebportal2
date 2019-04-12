package com.offcn.config;

import com.offcn.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigFilter {

    @Bean
    public FilterRegistrationBean someFilterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(getLoginFilter());
        filterRegistrationBean.addUrlPatterns("/api/order/*");
        filterRegistrationBean.addUrlPatterns("/api/user/*");

        return filterRegistrationBean;

    }

    @Bean
    public LoginFilter getLoginFilter(){
        return  new LoginFilter();
    }
}
