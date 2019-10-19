package com.java.util;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * @Author : Haotian
 * @Description : JDBC工具类
 */
public class JdbcUtils {
    private static String url;
    private static String user;
    private static String password;

    /**
     * 文件的读取，只需要读取一次即可拿到这些值。使用静态代码块
     */
    static {
        //读取资源文件，获取值
        try {
            //1.基于ResourceBundle读取配置文件
            ResourceBundle resourceBundle = ResourceBundle.getBundle( "config/jdbc/jdbc" );
            //2.获取数据，赋值
            url = resourceBundle.getString( "url" );
            user = resourceBundle.getString( "user" );
            password = resourceBundle.getString( "password" );
            String driver = resourceBundle.getString( "driver" );
            //3.注册驱动
            Class.forName( driver );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取链接对象
     *
     * @return 连接对象
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection( url, user, password );
    }

    /**
     * 释放资源
     *
     * @param stmt sql执行对象
     * @param conn 数据库连接对象
     */
    public static void close(Statement stmt, Connection conn) {
        //避免空指针异常
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放资源
     *
     * @param rs   查询结果集
     * @param stmt sql执行对象
     * @param conn 数据库连接对象
     */
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close( stmt, conn );
    }
}
