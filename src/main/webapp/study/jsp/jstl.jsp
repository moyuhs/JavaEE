<%@ page import="com.java.domain.Person" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>jstl学习</title>
</head>
<body>
<%
    //判断request域中的一个list集合是否为空，如果不为null则显示遍历集合
    List list = new ArrayList();
    list.add( "滴滴滴" );
    request.setAttribute( "list", list );
    request.setAttribute( "number", 3 );
%>
<pre>c:if标签
1. 属性：
    * test为必须属性，接收boolean表达式
    * 如果表达式为true，则显示if标签体内容，如果为false，则不显示标签体内容
    * 一般情况下，test属性值会结合el表达式一起使用
2. 注意：c:if标签没有else情况，想要else情况，则可以再定义一个c:if标签</pre>

<c:if test="true">
    <h3>我是真的...</h3>
</c:if>
<br>
<c:if test="${not empty list}">
    遍历集合...
</c:if>
<br>
<c:if test="${number % 2 != 0}">
    ${number}为奇数
</c:if>

<c:if test="${number % 2 == 0}">
    ${number}为偶数
</c:if>
<hr>
<pre>完成数字编号对应星期几案例
    1.域中存储一数字
    2.使用choose标签取出数字         相当于switch声明
    3.使用when标签做数字判断         相当于case
    4.otherwise标签做其他情况的声明  相当于default</pre>
<%
    request.setAttribute( "number", 7 );
%>
<c:choose>
    <c:when test="${number==1}">星期一</c:when>
    <c:when test="${number==2}">星期二</c:when>
    <c:when test="${number==3}">星期三</c:when>
    <c:when test="${number==4}">星期四</c:when>
    <c:when test="${number==5}">星期五</c:when>
    <c:when test="${number==6}">星期六</c:when>
    <c:when test="${number==7}">星期天</c:when>
    <c:otherwise>输入数字有误</c:otherwise>
</c:choose>
<hr>
<pre>foreach:相当于java代码的for语句
    1. 完成重复的操作
    for(int i = 0; i < 10; i ++){
    }
    * 属性：
        begin：开始值
        end：结束值
        var：临时变量
        step：步长
        varStatus:循环状态对象
        index:容器中元素的索引，从0开始
        count:循环次数，从1开始
    2. 遍历容器
    List<> list;
    for(User user : list){
    }
    * 属性：
        items:容器对象
        var:容器中元素的临时变量
        varStatus:循环状态对象
        index:容器中元素的索引，从0开始
        count:循环次数，从1开始</pre>
<c:forEach begin="1" end="10" var="i" step="2" varStatus="s">
    ${i}${s.index}${s.count} </c:forEach>
<hr>
<%
    List lis = new ArrayList();
    lis.add( "aaa" );
    lis.add( "bbb" );
    lis.add( "ccc" );

    request.setAttribute( "lis", lis );


%>
<c:forEach items="${lis}" var="str" varStatus="s">

    ${s.index} ${s.count} ${str}<br>

</c:forEach>
<hr>
<h3>数据展示进表格</h3>
<%
    List lisp = new ArrayList();
    lisp.add( new Person( "薇尔莉特", 18, new Date() ) );
    lisp.add( new Person( "艾斯德斯", 24, new Date() ) );
    lisp.add( new Person( "蕾姆", 19, new Date() ) );
    request.setAttribute( "lisp", lisp );
%>
<table border="1">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>生日</th>
    </tr>
    <c:forEach items="${lisp}" var="p" varStatus="s">
        <c:if test="${s.count % 2 != 0}">
            <tr bgcolor="#99CCFF">
                <td>${s.count}</td>
                <td>${p.name}</td>
                <td>${p.age}</td>
                <td>${p.birStr}</td>
            </tr>
        </c:if>
        <c:if test="${s.count % 2 == 0}">
            <tr bgcolor="#CCFF99">
                <td>${s.count}</td>
                <td>${p.name}</td>
                <td>${p.age}</td>
                <td>${p.birStr}</td>
            </tr>
        </c:if>
    </c:forEach>
</table>
</body>
</html>
