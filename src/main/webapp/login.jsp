<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X_UA_Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <title>登录</title>
    <style type="text/css">
        body {
            background: #1d598e;
        }

        .layui-input {
            height: 45px;
            width: 87%;
            padding-left: 5px;
            font-size: 16px;
            display: inline-block;
        }

        .layui-btn {
            margin-top: -350px;
            height: 45px;
        }

        .captcha-input {
            height: 45px;
            padding-left: 5px;
            font-size: 16px;
            margin-left: 45px;
        }

        .layui-form {
            width: 20%;
            height: 400px;
            margin: 0 auto;
            margin-top: 10%;
            padding: 15px 28px 0px;
            background: #fff;
        }

        .layui-input-block {
            margin-left: 0;
        }

        .layui-input-block a {
            color: blue;
            float: right;
            line-height: 30px;
            font-size: 14px;
        }

        h1 {
            text-align: center;
            color: #1d598e;
        }
    </style>
</head>
<body>
<form class="layui-form" action="${pageContext.request.contextPath}/loginServlet" method="post">
    <div class="layui-form-item">
        <h1>后台登录</h1>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <span class="decrib">账号：</span>
            <input type="text" name="username" placeholder="请输入账号" autocomplete="off" class="layui-input"
                   autofocus="true" required>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <span class="decrib">密码：</span>
            <input type="password" name="password" placeholder="请输入密码" autocomplete="off" class="layui-input" required>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <input type="text" name="captcha" placeholder="验证码" autocomplete="off" class="captcha-input" required>
            <img id="verify_img" src="${pageContext.request.contextPath}/checkCodeServlet" alt="验证码" title="看不清楚换一张？"><br>
            <strong style="color:red">${login_msg}</strong>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <input type="checkbox" name="rememberMe" title="记住密码" lay-skin="primary">
            <a href="#">忘记密码？</a>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-fluid" type="submit">登 录</button>
        </div>
    </div>
</form>
</body>
<script src="layui/layui.js"></script>
<script>
    layui.use(['layer', 'form'], function () {
        const layer = layui.layer,
            form = layui.form,
            $ = layui.jquery;
        $(document).on('click', "#verify_img", function () {
            $("#verify_img").attr("src", "${pageContext.request.contextPath}/checkCodeServlet?id=" + Math.random());
        });
    });
</script>
</html>