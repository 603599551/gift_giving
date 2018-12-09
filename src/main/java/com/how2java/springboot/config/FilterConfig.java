package com.how2java.springboot.config;


import com.how2java.springboot.filter.CharactorFilter;
import com.how2java.springboot.filter.CrossDomainAccessFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置过滤器顺序
 * @Author CaryZ
 * @Date 2018-12-09
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean buildCharactorFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new CrossDomainAccessFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setName("CharactorFilter");
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean buildCrossDomainAccessFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new CharactorFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.setName("CrossDomainAccessFilter");
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
