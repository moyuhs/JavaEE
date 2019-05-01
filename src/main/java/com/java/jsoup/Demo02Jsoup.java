package com.java.jsoup;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Jsoup对象
 */
public class Demo02Jsoup {

    /*public static void sendMsg(String first, String keyword1, String keyword2, String keyword3, String remark) throws IOException {
        // 创建一个httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建一个post对象
        HttpPost post = new HttpPost( "https://u.ifeige.cn/api/message/send" );
        // 创建一个Entity，模拟表单数据
        List<NameValuePair> formList = new ArrayList<NameValuePair>();
        // 添加表单数据
        formList.add( new BasicNameValuePair( "secret", "e865671cb4e8a0faf7d47881803da94a" ) );
        formList.add( new BasicNameValuePair( "app_key", "69389b37df1a6cd50e198fcd6ef7cc4a" ) );
        formList.add( new BasicNameValuePair( "template_id", "5uZIvSm5GAdUR1X25HNpjuOp6jSiL88v4opn4a4GLa0" ) );
        formList.add( new BasicNameValuePair( "first", first ) );
        formList.add( new BasicNameValuePair( "keyword1", keyword1 ) );
        formList.add( new BasicNameValuePair( "keyword2", keyword2 ) );
        formList.add( new BasicNameValuePair( "keyword3", keyword3 ) );
        formList.add( new BasicNameValuePair( "remark", remark ) );

        // 包装成一个Entity对象
        StringEntity entity = new UrlEncodedFormEntity( formList, "utf-8" );
        // 设置请求的内容
        post.setEntity( entity );
        // 设置请求的报文头部的编码
        // post.setHeader(new BasicHeader("Content-Type",
        // "application/x-www-form-urlencoded; charset=utf-8"));
        // 设置期望服务端返回的编码
        // post.setHeader(new BasicHeader("Accept",
        // "text/plain;charset=utf-8"));
        // 执行post请求
        CloseableHttpResponse response = client.execute( post );
        // 获取响应码
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            // 获取数据
            String resStr = EntityUtils.toString( response.getEntity() );
            // 输出
            System.out.println( resStr );
        } else {
            // 输出
            System.out.println( statusCode );
        }
    }*/

    public static void main(String[] args) throws IOException {

        /*//1.1获取student.xml的path
        String path = Demo02Jsoup.class.getClassLoader().getResource( "jsoup/student.xml" ).getPath();

        //1.2解析xml文档，加载文档进内存，获取dom树--->Document
        Document document = Jsoup.parse( new File( path ), "utf-8" );
        System.out.println( document );*/

        //2.parse​(String html)：解析xml或html字符串
        /*String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "\n" +
                "<students>\n" +
                "\t<student number=\"heima_0001\">\n" +
                "\t\t<name>tom</name>\n" +
                "\t\t<age>18</age>\n" +
                "\t\t<sex>male</sex>\n" +
                "\t</student>\n" +
                "\t<student number=\"heima_0002\">\n" +
                "\t\t<name>jack</name>\n" +
                "\t\t<age>18</age>\n" +
                "\t\t<sex>female</sex>\n" +
                "\t</student>\n" +
                "\n" +
                "</students>";
        Document document = Jsoup.parse(str);
        System.out.println(document);*/

        //3.parse​(URL url, int timeoutMillis)：通过网络路径获取指定的html或xml的文档对象
        //System.out.println( document );
       /* Elements elements = document.getElementsByAttributeValue( "class", "co_content2" );
        for (Element element : elements) {
            System.out.println(element);
        }*/
        System.out.println( "----------设定电影推送系统----------" );
        Scanner scanner = new Scanner( System.in );
        System.out.println( "请输入电影名：" );
        String s = scanner.next();
        while (true) {
            URL url = new URL( "https://www.dytt8.net" );//代表网络中的一个资源路径
            Document document = Jsoup.parse( url, 10000 );

            Element element = document.getElementsByAttributeValue( "class", "co_content2" ).get( 1 );
            Elements elements = element.getElementsByTag( "a" );
            String text = elements.html();
            //System.out.println(text);
            boolean contains = text.contains( s );
            if (contains) {
                System.out.println( "电影已发布" );
                break;
            } else {
                System.out.println( "电影未发布" );
            }
        }


    }
}
