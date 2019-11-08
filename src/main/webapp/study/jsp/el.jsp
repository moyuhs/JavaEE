<%@ page import="com.java.domain.Person" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el获取域中的数据</title>
</head>
<body>
<%
    //在域中储存数据
    session.setAttribute( "name", "楪祈" );
    request.setAttribute( "name", "艾莉丝" );
    session.setAttribute( "age", "18" );

    //将对象中的数据存放在域中
    Person person = new Person();
    person.setName( "Sakura" );
    person.setAge( 20 );
    person.setBirthday( new Date() );
    request.setAttribute( "u", person );

    //将链表数据放入域中
    ArrayList<Object> list = new ArrayList<>();
    list.add( "莎堤莱萨·艾尔·布丽姬" );
    list.add( "Pi~pa~Pi~pa" );
    list.add( person );
    request.setAttribute( "list", list );

    Map<String, Object> map = new HashMap<>();
    map.put( "user", person );
    map.put( "nowName", "拉娜·林沁" );
    map.put( "gender", "女" );
    request.setAttribute( "map", map );

%>

<h3>El获取值,若没有数据，打印空字符串</h3>
${requestScope.name}
${sessionScope.age}
${name}<%--表示依次从最小的域中查找是否有该键对应的值，直到找到为止--%>
<hr>
<h3>El获取对象中的值</h3>
<pre>通过对象的属性来获取
    *setter或getter方法，去掉set或get，在将剩余部分，首字母变小写
    *setName --> Name --> name</pre>
用户名：${requestScope.u.name}<br>
用户名：${u.name}<br>
年龄：${u.age}<br>
生日：${u.birStr}<br>
<hr>
<h3>El获取List的值</h3>
${requestScope.list}<br>
${list[0]}<br>
${list[2]}<br>
${list[2].name}<br>
<hr>
<h3>El获取Map的值</h3>
${map.gender}<br>
${map["gender"]}<br>
${map.user.name}<br>
${map.nowName}
</body>
</html>
