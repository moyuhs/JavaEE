<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script>
        //页面加载完后执行
        $(function () {
            //给username绑定blur事件
            $("#username").blur(function () {
                //获取文本框输入的值
                var username = $(this).val();
                //发送Ajax请求
                //期望服务器响应回的数据格式：{"userExsit":true,"msg":"此用户名太受欢迎,请更换一个"}
                //                         {"userExsit":false,"msg":"用户名可用"}
                $.get("findUserServletName", {username: username}, function (data) {
                    //判断userExsit键的值是否是true
                    var span = $("#m_username");
                    if (data.userExsit) {
                        //用户名存在
                        span.css("color", "red");
                        span.html(data.msg);
                    } else {
                        //用户名不存在
                        span.css("color", "green");
                        span.html(data.msg);
                    }
                });//设置接收的数据类型
            });
        });
    </script>
</head>
<body>
<form>
    <input type="text" id="username" name="username" placeholder="请输入用户名"><br>
    <span id="m_username"></span>
    <input type="password" name="password" placeholder="请输入用户密码"><br>
    <input type="submit" value="注册"><br>
</form>
</body>
</html>
