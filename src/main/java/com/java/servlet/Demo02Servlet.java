package com.java.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Servlet快速入门
 * 注解配置虚拟路径访问
 */
@WebServlet("/demo2")
public class Demo02Servlet implements Servlet {
    /**
     * 初始化方法
     * 在Servlet被创建时执行，只会执行一次
     *
     * @param servletConfig
     */
    @Override
    public void init(ServletConfig servletConfig) {
        System.out.println( "init......" );
    }

    /**
     * 获取ServletConfig对象
     * ServletConfig：Servlet的配置对象
     *
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 提供服务的方法
     * 每一次Servlet被访问时执行，执行多次
     *
     * @param servletRequest
     * @param servletResponse
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
        System.out.println( "Hello Servlet 3.0" );
    }

    /**
     * 获取Servlet的一些信息，版本，作者等。。
     *
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 销毁方法
     * 在服务器正常关闭时执行，执行一次
     */
    @Override
    public void destroy() {
        System.out.println( "destroy....." );
    }
}
