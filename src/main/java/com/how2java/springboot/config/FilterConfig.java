package com.how2java.springboot.config;


import com.how2java.springboot.filter.AFilter;
import com.how2java.springboot.filter.BFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author zjq
 * @Date 2018/10/27 18:47
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean buildLoginFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new BFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setName("BFilter");
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean buildAFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new AFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.setName("AFilter");
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
