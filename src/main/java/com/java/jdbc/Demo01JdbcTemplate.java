package com.java.jdbc;

import com.java.util.DruidUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Description : JdbcTemplate入门
 * @Author : Haotian
 */
public class Demo01JdbcTemplate {
    public static void main(String[] args) {
        //1.导入jar包
        //2.创建JDBCTemplate对象，需要一个数据源
        JdbcTemplate template = new JdbcTemplate( DruidUtils.getDataSource() );
        //3.调用方法
        String sql = "update users set upwd = 54321 where uid = ?";
        int count = template.update( sql, "admin" );
        System.out.println( count );
    }
}
