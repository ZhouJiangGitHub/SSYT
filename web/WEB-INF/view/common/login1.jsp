<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>欢迎登录上善云图学生测试后台管理系统</title>
    <link href="<%=request.getContextPath() %>/static/common/css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="<%=request.getContextPath() %>/static/common/js/jquery.js"></script>
    <script src="<%=request.getContextPath() %>/static/common/js/cloud.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/static/common/plugin/layer-2.1/layer.js"
            type="text/javascript"></script>

    <script language="javascript">
        $(function () {
            $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            $(window).resize(function () {
                $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            })
        });


    </script>

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
    <span>欢迎上善云图学生测试系统</span>
    <ul>
        <li><a href="#">回首页</a></li>
        <li><a href="#">帮助</a></li>
        <li><a href="#">关于</a></li>
    </ul>
</div>

<div class="loginbody">

    <span class="systemlogo"></span>

    <div class="loginbox">


        <div style="width:300px;height:50px;position:absolute;left:300px;top:0px;">

            <span id="errorInfo" style="font-size: 24px;color: red;">${errorState}</span>
        </div>


        <form action="<%=request.getContextPath() %>/LoginServlet?command=login" method="post">
            <ul>
                <li><input name="code" type="text" class="loginuser" value="${cookie.code.value }"
                           onclick="JavaScript:this.value=''"/></li>
                <li><input name="password" type="password" class="loginpwd" value="${cookie.password.value }"
                           onclick="JavaScript:this.value=''"/></li>
                <li>
                    <input type="text" class="loginuser" style="width:150px;" name="verification" maxlength="4"
                           placeholder="验证码"/>
                    <img src="<%=request.getContextPath() %>/kaptcha.jpg" width="120px" height="48px"
                         style="cursor: pointer;vertical-align:top;" id="pic" onclick="changePic();" title="看不清？换一个"/>

                </li>
                <li>
                    <input name="" type="submit" class="loginbtn" value="登录"/>
                    <label>
                        <input name="rememberLogin" type="checkbox" value="true"
                               checked="checked"/>记住密码 <%-- <c:if test="${cookie.rememberLogin.value=='true' }">checked="checked"</c:if> --%>
                    </label>
                    <label><a href="#">忘记密码？</a></label>
                </li>
            </ul>

        </form>

    </div>

</div>


<div class="loginbm">版权所有 2016 <a href="http://www.mycodes.net"></a>上善云图雷电一班第二组</div>


<script>
    var changePic = function () {
        document.getElementById('pic').src = '<%=request.getContextPath() %>/kaptcha.jpg?time=' + new Date().getTime();
    }

    /*  		if("${loginFailure}" == 'true'){
			document.getElementById('errorInfo').value='会话已关闭！（原因：有可能是长时间没有使用或已在其他设备上登录。）';
		} */


</script>

</body>

</html>
