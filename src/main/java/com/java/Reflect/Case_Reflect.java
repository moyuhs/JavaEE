package com.java.Reflect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 配置文件方式
 * 框架类:不能改变该类的任何代码。可以创建任意类的对象，可以执行任意方法
 */
public class Case_Reflect {
    public static void main(String[] args) throws Exception {
        //可以创建任意类的对象，可以执行任意方法
        /*  Person p = new Person();
        p.eat();*/
        /*
        Student stu = new Student();
        stu.sleep();*/


        //1.加载配置文件
        //1.1创建Properties对象
        Properties pro = new Properties();
        //1.2加载配置文件，转换为一个集合
        //1.2.1获取class目录下的配置文件
       /* ClassLoader classLoader = Case_Reflect.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream( "pro.properties" );
        pro.load( is );*/
        BufferedReader bufferedReader = new BufferedReader( new FileReader( "D:\\javaee\\src\\main\\java\\pro.properties" ) );
        pro.load( bufferedReader );

        //2.获取配置文件中定义的数据
        String className = pro.getProperty( "className" );
        String methodName = pro.getProperty( "methodName" );


        //3.加载该类进内存
        Class cls = Class.forName( className );
        //4.创建对象
        Object obj = cls.newInstance();
        //5.获取方法对象
        Method method = cls.getMethod( methodName );
        //6.执行方法
        method.invoke( obj );

    }
}
