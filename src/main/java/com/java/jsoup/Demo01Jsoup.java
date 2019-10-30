package com.java.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @Description : Jsoup快速入门:可以解析网页，所以能用做爬虫
 * @Author : Haotian
 */
public class Demo01Jsoup {
    public static void main(String[] args) throws IOException {
        //1.获取Document对象，根据xml文档获取
        //1.2获取student.xml的path
        String path = Objects.requireNonNull( Demo01Jsoup.class.getClassLoader().getResource( "jsoup/student.xml" ) ).getPath();

        //1.2解析xml文档，加载文档进内存，获取Dom树--->Document
        Document document = Jsoup.parse( new File( path ), "utf-8" );

        //2.获取元素对象Element
        Elements elements = document.getElementsByTag( "name" );
        System.out.println( elements.size() );

        //3.1获取第一个name的Element对象
        Element element = elements.get( 0 );

        //3.2获取数据
        String name = element.text();
        System.out.println( name );

    }
}
