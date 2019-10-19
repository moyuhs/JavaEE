package com.java.jdbc;

import com.java.util.JdbcUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.System.out;

/**
 * JDBC的基础使用:insert添加
 */
public class Demo01Jdbc {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        try {
            //1. 注册驱动
            Class.forName( "com.mysql.jdbc.Driver" );
            //2. 定义sql
            String insertSql = "insert into Student values(null,'琳琳',18)";
            String updateSql = "update Student set name = '椎名真白' where id = 4  ";
            //3.获取Connection对象
            conn = DriverManager.getConnection( "jdbc:mysql://47.97.185.117:3306/study?useUnicode=true&characterEncoding=utf-8&useSSL=false", "admin", "admin" );
            //4.获取执行sql的对象 Statement
            stmt = conn.createStatement();
            //5.执行sql,返回受影响的行数
            int count = stmt.executeUpdate( updateSql );
            //6.处理结果
            out.println( count );
            if (count > 0) {
                out.println( "添加成功！" );
            } else {
                out.println( "添加失败！" );
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //7. 释放资源
            JdbcUtils.close( stmt, conn );
        }
    }


}
