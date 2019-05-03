<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>index</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.4.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<p>Hello word</p>
<form action="/loginServlet" method="post">
    <label for="username">用户姓名：</label>
    <input type="text" placeholder="请输入用户密码" id="username" name="uid">
    <br>
    <label for="pwd">用户密码：</label>
    <input type="password" placeholder="请输入密码" id="pwd" name="upwd">
    <br>
    <input type="checkbox" name="hobby" value="game">游戏
    <input type="checkbox" name="hobby" value="study">学习
    <br>
    <input type="submit" value="提交">
</form>
</body>
</html>
