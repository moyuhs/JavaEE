package com.java.jdbc;

import com.java.domain.Student;
import com.java.util.DruidUtils;
import org.junit.Test;
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

    @Test
    public void test() {
        Student admin = Student.builder()
                .sno( "001" )
                .sname( "admin" )
                .sage( 18 )
                .sphone( "110" ).sphone( "112" )
                .build();
        Student admin2 = admin.toBuilder()
                .sno( "001" )
                .sname( "admin" )
                .sage( 18 )
                .sphone( "110" ).sphone( "112" )
                .build();
        System.out.println( admin == admin2 );
//        Student(sno=001, sname=admin, ssex=null, sage=18, department=null, sclass=null, sphone=112)
    }
}
