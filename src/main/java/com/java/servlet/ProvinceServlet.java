package com.java.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.domain.Province;
import com.java.service.ProvinceService;
import com.java.service.impl.ProvinceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 省份返回方法
 */
@WebServlet("/provinceServlet")
public class ProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* //1.调用service查询
        ProvinceService service = new ProvinceServiceImpl();
        List<Province> list = service.finAll();

        //2.序列化List为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString( list );*/

        //1.调用service查询
        ProvinceService service = new ProvinceServiceImpl();
        String json = service.findAllJson();

        System.out.println( json );
        //2.响应结果
        response.setContentType( "application/json;charset=utf-8" );
        response.getWriter().write( json );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
