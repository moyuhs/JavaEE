package com.java.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * Druid演示
 * 相比c3p0而言，配置文件更加灵活，路径可任意，但配置文件需要手动加载
 */
public class Demo01Druid {
    public static void main(String[] args) throws Exception {
        //1.加载配置文件
        Properties pro = new Properties();
        InputStream is = Demo01Druid.class.getClassLoader().getResourceAsStream( "config/druid/druid.properties" );
        pro.load( is );
        //2.获取连接池对象
        DataSource ds = DruidDataSourceFactory.createDataSource( pro );
        //3.获取链接
        Connection conn = ds.getConnection();
        System.out.println( conn );

    }
}
