package com.java.servlet;

import com.java.domain.User;
import com.java.service.UserService;
import com.java.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据id查询信息
 */
@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取id
        String id = request.getParameter( "id" );

        //2.调用service查询
        UserService service = new UserServiceImpl();
        User user = service.findUserById( id );

        //3.将user存入request
        request.setAttribute( "user", user );

        //4.转发到update.jsp
        request.getRequestDispatcher( "/update.jsp" ).forward( request, response );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
