package com.java.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.domain.Person;
import org.junit.Test;

import java.io.FileWriter;
import java.util.*;

/**
 * 使用Jackson进行java对象和json数据格式的互相转换
 */
public class JacksonTest {

    //Java对象转为JSON字符串
    @Test
    public void test1() throws Exception {
        //1.创建Person对象
        Person p = new Person();
        p.setName( "妲己" );
        p.setAge( 24 );
        p.setBirthday( new Date() );

        //2.创建Jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //3.转换
        /*
            转换方法：
                writeValue(参数1，obj):
                    参数1：
                        File：将obj对象转换为JSON字符串，并保存到指定的文件中
                        Writer：将obj对象转换为JSON字符串，并将json数据填充到字符输出流中
                        OutputStream：将obj对象转换为JSON字符串，并将json数据填充到字节输出流中
                writeValueAsString(obj):将对象转为json字符串
         */
        String json = mapper.writeValueAsString( p );
        System.out.println( json );//{"name":"妲己","age":24,"birthday":1557645385599,"a":null,"birStr":"2019-05-12 15:16:25"}

        //writeValue.将数据关联到Writer中
        mapper.writeValue( new FileWriter( "d://b.txt" ), p );

    }

    @Test
    public void test2() throws Exception {
        //1.创建Person对象
        Person p = new Person();
        p.setName( "艾莉丝" );
        p.setAge( 23 );
        p.setBirthday( new Date() );

        //2.转换
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString( p );

        System.out.println( json );//注解忽略后：{"name":"艾莉丝","age":23,"birthday":1557645824002,"birStr":"2019-05-12 15:23:44"}
        //格式化注解后：{"name":"艾莉丝","age":23,"birthday":"2019-05-12","birStr":"2019-05-12 15:26:45"}
    }

    @Test
    public void test3() throws Exception {
        //1.创建Person对象
        Person p = new Person();
        p.setName( "艾斯德斯" );
        p.setAge( 23 );
        p.setBirthday( new Date() );

        Person p1 = new Person();
        p1.setName( "樱子" );
        p1.setAge( 24 );
        p1.setBirthday( new Date() );

        Person p2 = new Person();
        p2.setName( "梦梦" );
        p2.setAge( 18 );
        p2.setBirthday( new Date() );

        //2.创建List集合
        List<Person> list = new ArrayList<Person>();
        list.add( p );
        list.add( p1 );
        list.add( p2 );

        //3.转换
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString( list );
        // [{},{},{}]
        System.out.println( json );
    }

    @Test
    public void test4() throws Exception {
        //1.创建map对象
        Map<String, Object> map = new HashMap<String, Object>();
        map.put( "name", "娜娜" );
        map.put( "age", 18 );
        map.put( "sex", "男" );

        //2.转换
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString( map );

        System.out.println( json );
    }

    //演示 JSON字符串转为Java对象
    @Test
    public void test5() throws Exception {
        //1.初始化JSON字符串
        String json = "{\"name\":\"张三\",\"age\":23,\"birthday\":\"2019-05-12\"}";

        //2.创建ObjectMapper对象
        ObjectMapper mapper = new ObjectMapper();
        //3.转换为Java对象 Person对象
        Person person = mapper.readValue( json, Person.class );

        System.out.println( person );
    }

}