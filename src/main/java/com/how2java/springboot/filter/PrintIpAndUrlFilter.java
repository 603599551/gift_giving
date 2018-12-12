package com.how2java.springboot.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.how2java.springboot.utils.DateUtil.getDateTime;

/**
 * 打印用户访问ip地址和访问的页面url
 * @author CaryZ
 * @date 2018-12-12
 */
public class PrintIpAndUrlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //doFilter()方法中的req参数的类型是ServletRequest，需要转换为HttpServletRequest类型方便调用某些方法
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        //获取来路用户的ip地址
        String ip=request.getRemoteAddr();
        //获取用户访问的页面地址
        String url=request.getRequestURL().toString();
        //当前日期
        String date=getDateTime();

        //在控制台打印出来
        System.out.printf("%s %s 访问了 %s%n", date, ip, url);
        //过滤器放行，表示继续运行下一个过滤器，或者最终访问的某个servlet,jsp,html等等
        //若不放行，则无法访问文件或者运行下一个过滤器
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
