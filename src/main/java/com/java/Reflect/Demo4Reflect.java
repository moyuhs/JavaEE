package com.java.Reflect;

import com.java.domain.Person;

import java.lang.reflect.Method;

public class Demo4Reflect {

    /**
     * Class对象功能：
     * 获取功能：
     * 3. 获取成员方法们：
     * Method[] getMethods()
     * Method getMethod(String name, 类<?>... parameterTypes)
     * <p>
     * Method[] getDeclaredMethods()
     * Method getDeclaredMethod(String name, 类<?>... parameterTypes)
     * <p>
     * 4. 获取类名
     * String getName()
     */
    public static void main(String[] args) throws Exception {

        //0.获取Person的Class对象
        Class personClass = Person.class;

        //获取指定名称的方法
        Method eat_method = personClass.getMethod( "eat" );
        Person p = new Person();
        //执行方法
        eat_method.invoke( p );


        Method eat_method2 = personClass.getMethod( "eat", String.class );
        //执行方法
        eat_method2.invoke( p, "饭" );

        System.out.println( "-----------------" );

        //获取所有public修饰的方法
        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            System.out.println( method );
            String name = method.getName();
            System.out.println( name );
            //method.setAccessible(true);
        }

        //获取类名
        String className = personClass.getName();
        System.out.println( className );//

    }


}
