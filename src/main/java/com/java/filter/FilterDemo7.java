package com.java.filter;

import javax.servlet.*;
import java.io.IOException;

//@WebFilter("/*")
public class FilterDemo7 implements Filter {


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println( "filterDemo7执行了..." );

        chain.doFilter( req, resp );

        System.out.println( "filterDemo7回来了..." );
    }

    @Override
    public void init(FilterConfig config) {

    }

    @Override
    public void destroy() {
    }

}
