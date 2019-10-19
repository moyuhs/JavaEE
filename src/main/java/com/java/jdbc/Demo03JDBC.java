package com.java.jdbc;

import com.java.util.JdbcUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC的基础使用:delete删除
 */
public class Demo03JDBC {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //1.注册驱动
            Class.forName( "com.mysql.jdbc.Driver" );
            //2.获取链接对象
            conn = DriverManager.getConnection( "jdbc:mysql:///bank", "root", "AngelBeats" );
            //3.定义sql
            String sql = "delete from users where uid = '13795725154' ";
            //4.获取执行sql对象
            stmt = conn.createStatement();
            //5.执行sql
            int count = stmt.executeUpdate( sql );
            //6.处理结果
            System.out.println( count );
            if (count > 0) {
                System.out.println( "删除成功!" );
            } else {
                System.out.println( "删除失败！" );
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //7.释放资源
            JdbcUtils.close( stmt,conn );
        }
    }
}
