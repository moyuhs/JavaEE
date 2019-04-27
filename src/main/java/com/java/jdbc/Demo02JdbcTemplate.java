package com.java.jdbc;


import com.java.domain.Student;
import com.java.util.DruidUtils;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * JdbcTemplate测试
 */
public class Demo02JdbcTemplate {
    //Junit单元测试，让方法可以独立运行
    //1.获取JdbcTemplate对象
    private JdbcTemplate template = new JdbcTemplate( DruidUtils.getDataSource() );

    /**
     * 进行数据修改
     */
    @Test
    public void test1() {
        //2.定义sql
        String sql = "update users set upwd = 123456 where uid = ?";
        int count = template.update( sql, "13795725154" );
        System.out.println( count );
    }

    /**
     * 进行数据添加
     */
    @Test
    public void test2() {
        String sql = "insert into users values(?,?)";
        int count = template.update( sql, "596459193", 123456 );
        System.out.println( count );
    }

    /**
     * 进行数据删除
     */
    @Test
    public void test3() {
        String sql = "delete from users where uid = ?";
        int count = template.update( sql, "琳琳" );
        System.out.println( count );
    }

    /**
     * 进行数据查询，返回map集合
     * 弊端：只能返回一条记录，列名为key,值为value
     */
    @Test
    public void test4() {
        String sql = "select * from users where uid = ?";
        Map<String, Object> map = template.queryForMap( sql, "596459193" );
        for (String key : map.keySet()) {
            Object value = map.get( key );
            //System.out.println( key  + value );
        }
        System.out.println( map );
    }

    /**
     * 进行数据查询，返回List集合
     */
    @Test
    public void test5() {
        String sql = "select * from users ";
        List<Map<String, Object>> list = template.queryForList( sql );
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println( stringObjectMap );
        }
    }

    /**
     * 进行数据查询，返回student对象集合
     * 原始方式
     */
    @Test
    public void test6() {
        String sql = "select * from students ";
        List<Student> list = template.query( sql, (ResultSet rs, int i) -> {
            Student student = new Student();
            String sno = rs.getString( "sno" );
            String name = rs.getString( "sname" );
            String sex = rs.getString( "ssex" );
            int age = rs.getInt( "sage" );
            String department = rs.getString( "department" );
            String sclass = rs.getString( "sclass" );
            String phone = rs.getString( "sphone" );

            student.setSno( sno );
            student.setSname( name );
            student.setSsex( sex );
            student.setSage( age );
            student.setDepartment( department );
            student.setSclass( sclass );
            student.setSphone( phone );
            return student;
        } );
        for (Student student : list) {
            System.out.println( student );
        }
    }

    /**
     * 进行数据查询，返回student对象集合
     * 使用BeanPropertyRowMapper接口
     */
    @Test
    public void test7() {
        String sql = "select * from students ";
        List<Student> list = template.query( sql, new BeanPropertyRowMapper<Student>( Student.class ) );
        for (Student student : list) {
            System.out.println( student );
            //System.out.println(student.getSno());
        }
    }

    /**
     * 查询总数
     * queryForObject一般用于聚合函数的查询
     */
    @Test
    public void test8() {
        String sql = "select count(sno) from students";
        Long total = template.queryForObject( sql, Long.class );
        System.out.println( total );
    }
}
