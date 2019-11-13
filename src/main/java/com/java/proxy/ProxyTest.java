package com.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理对象增强测试测试
 */
public class ProxyTest {
    public static void main(String[] args) {
        //1.创建真实对象
        Alienware alienware = new Alienware();

        //2.动态代理增强alienware对象
        /*
            三个参数：
                1. 类加载器：真实对象.getClass().getClassLoader()
                2. 接口数组：真实对象.getClass().getInterfaces()
                3. 处理器：new InvocationHandler()
         */
        Computer proxy_alienware = (Computer) Proxy.newProxyInstance( alienware.getClass().getClassLoader(), alienware.getClass().getInterfaces(), new InvocationHandler() {
            /*
                代理逻辑编写的方法：代理对象调用的所有方法都会触发该方法执行
                    参数：
                        1. proxy:代理对象
                        2. method：代理对象调用的方法，被封装为的对象
                        3. args:代理对象调用的方法时，传递的实际参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /*System.out.println( "该方法执行了。。。" );
                System.out.println( method.getName() );
                System.out.println(args[0]);*/

                //判断是否是sale方法
                if ("sale".equals( method.getName() )) {
                    //1.增强参数
                    double money = (double) args[0];
                    money = money * 0.85;
                    System.out.println( "专车接你...." );
                    //使用真实对象调用该方法
                    String obj = (String) method.invoke( alienware, money );
                    System.out.println( "免费送货..." );
                    //2.增强返回值
                    return obj + " 鼠标垫";
                } else {
                    return method.invoke( alienware, args );
                }
            }
        } );

        //3.调用方法
        //当调用sale方法时，进行动态代理
        String computer = proxy_alienware.sale( 12000 );
        System.out.println( computer );
        //进行其他操作，直接原有方式
        //proxy_alienware.show();
    }
}
