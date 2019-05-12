package com.java.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.domain.User;
import com.java.service.UserService;
import com.java.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ajax数据处理类
 */
@WebServlet("/findUserServletName")
public class FindUserServletName extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应的数据格式为json
        response.setContentType( "application/json; charset=utf-8" );

        //1.获取用户名
        String username = request.getParameter( "username" );

        //2.调用service层查询用户名
        UserService service = new UserServiceImpl();
        List<User> users = service.findAll();

        //3.创建一个map用于存放返回的数据
        //期望服务器响应回的数据格式：{"userExsit":true,"msg":"此用户名太受欢迎,请更换一个"}
        //                         {"userExsit":false,"msg":"用户名可用"}
        Map<String, Object> map = new HashMap<String, Object>();

        for (User user : users) {
            String name = user.getUsername();
            //System.out.println( name );
            if (name.equals( username )) {
                //存在
                map.put( "userExsit", true );
                map.put( "msg", "此用户名太受欢迎,请更换一个" );
            } else {
                map.put( "userExsit", false );
                map.put( "msg", "用户名可用" );
            }
        }

        //4.将map转为json，并且传递给客户端
        //将map转为json
        ObjectMapper mapper = new ObjectMapper();
        //并且传递给客户端
        mapper.writeValue( response.getWriter(), map );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
