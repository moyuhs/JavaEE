package com.java.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;

/**
 * Druid连接池的工具类
 */
public class DruidUtils_before {

    /**
     * 定义成员变量 DataSource
     */
    private static DataSource ds;

    static {
        try {
            //1.加载配置文件
            Properties pro = new Properties();
            //通过当前的类加载器，以流的形式加载classes下的配置文件
            pro.load( Objects.requireNonNull( DruidUtils.class.getClassLoader().getResourceAsStream( "config/druid/druid.properties" ) ) );
            //2.获取DataSource
            ds = DruidDataSourceFactory.createDataSource( pro );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池对象
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 释放资源
     */
    public static void close(Statement stmt, Connection conn) {
        JdbcUtils.close( stmt, conn );
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        JdbcUtils.close( rs, stmt, conn );
    }

    /**
     * 获取连接池资源
     */
    public static DataSource getDataSource() {
        return ds;
    }

}
