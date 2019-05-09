package com.java.proxy;

/**
 * 真实类
 */
public class Alienware implements Computer {

    @Override
    public String sale(double money) {
        System.out.println( "花了" + money + "元买了一台外星人电脑。。。" );
        return "外星人笔记本";
    }

    @Override
    public void show() {
        System.out.println( "展示电脑....." );
    }
}
