<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>cookie</title>
</head>
<body>
<%
    //1.获取cookie
    Cookie[] cookies = request.getCookies();
    boolean flag = false;//没有cookie为lastTime
    //2.遍历cookies数组
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            //3.获取cookie的名称
            String name = cookie.getName();
            //4.判断是否是：lastTime
            if ("lastTime".equals( name )) {
                //有该cookie,不是第一次访问
                flag = true;

                //响应数据
                //获取cookie的value，时间
                String value = cookie.getValue();
                //URL解码
                value = URLDecoder.decode( value, "utf-8" );
%>
<h1>欢迎回来，您上次访问时间为: <%=value %>
</h1>
<%
                //重新设置cookie的value
                //获取当前时间的字符串，重新设置cookie的值，重新发送cookie
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat( "yyyy年MM月dd日 HH:mm:ss" );
                String str_date = sdf.format( date );
                //URL编码解决特殊字符
                str_date = URLEncoder.encode( str_date, "utf-8" );
                cookie.setValue( str_date );

                //设置cookie的存活时间
                cookie.setMaxAge( 60 * 60 * 24 * 30 );//一个月
                response.addCookie( cookie );
                break;
            }
        }
    }
    if (cookies == null || cookies.length == 0 || flag == false) {
        //没有cookie,第一次访问

        //获取当前时间的字符串，重新设置cookie的值，重新发送cookie
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy年MM月dd日 HH:mm:ss" );
        String str_date = sdf.format( date );
        str_date = URLEncoder.encode( str_date, "utf-8" );
        Cookie cookie = new Cookie( "lastTime", str_date );
        //设置cookie的存活时间
        cookie.setMaxAge( 60 * 60 * 24 * 30 );//一个月
        response.addCookie( cookie );
%>

<h1>您好，欢迎您首次访问</h1>
<%
    }
%>
</body>
</html>
