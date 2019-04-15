package com.java.annotation;

import java.lang.reflect.Method;

/**
 * 注解方式
 * 框架类:不能改变该类的任何代码。可以创建任意类的对象，可以执行任意方法
 */

@Pro(className = "com.java.domain.Person", methodName = "eat")
public class Case_Reflect {
    public static void main(String[] args) throws Exception {
        //1.解析注解
        //1.1获取该类的字节码文件对象
        Class<Case_Reflect> reflectTestClass = Case_Reflect.class;
        //2.获取上边的注解对象
        //其实就是在内存中生成了一个该注解接口的子类实现对象
        /*
            public class ProImpl implements Pro{
                public String className(){
                    return "cn.itcast.annotation.Demo1";
                }
                public String methodName(){
                    return "show";
                }
            }
 */
        Pro an = reflectTestClass.getAnnotation( Pro.class );
        //3.调用注解对象中定义的抽象方法，获取返回值
        String className = an.className();
        String methodName = an.methodName();
        System.out.println( className );
        System.out.println( methodName );

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
