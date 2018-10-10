<%@page language="java"  pageEncoding="utf8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
    <title>教师诚信系统</title>
    <link href="<%=request.getContextPath()%>/resources/css/common/base.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/resources/css/login/login.css" rel="stylesheet" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/common/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/login/login.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/boot.js"></script>
    <style type="text/css">
        .login_mistake
            color:#fb5900;
            margin-left:120px;
            margin-top:10px;
            font-size:15px;
            font-family:Microsoft YaHei;}
    </style>
    <script type="text/javascript">
    </script>
</head>
<body>
<div class="pg-center">
    <div class="line"></div>
    <div class="left-logo"></div>
    <form id="loginForm" action="login.do" method="post">
        <div class="right-input">
            <div class="user-wrap wrap">
                <span class="account"></span>
                <span class="split"></span>
       <!--         <input type="text" id="loginName" name="loginName" value="${loginName}" placeholder="输入用户名" onblur="" onchange=""/>  -->
                         <input type="text" id="loginName" name="loginName" value="" placeholder="输入用户名" onblur="" onchange=""/>

            </div>

            <div class="pwd-wrap wrap">
                <span class="pwd"></span>
                <span class="split"></span>
                <input type="password" name="password" placeholder="输入密码" />
            </div>
            <input type="submit" class="login-btn" value="登录"></input>
            <div class="login_mistake" >
                <spring:message code="${message}"/>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    mini.parse();
    $("#loginName").focus();
</script>
</body>
</html>
