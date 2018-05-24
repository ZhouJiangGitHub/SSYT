<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="<%=request.getContextPath() %>/static/common/css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="<%=request.getContextPath() %>/static/common/js/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            //顶部导航切换
            $(".nav li a").click(function () {
                $(".nav li a.selected").removeClass("selected")
                $(this).addClass("selected");
            })
        })
    </script>


</head>

<body style="background:url(<%=request.getContextPath() %>/static/common/images/topbg.gif) repeat-x;">

<div class="topleft">
    <a href="<%=request.getContextPath() %>/ApplicationServlet?command=main" target="_parent"><img
            src="<%=request.getContextPath() %>/static/common/images/header-logo.png" title="系统首页"/></a>
</div>

<%--     <ul class="nav">
    <li><a href="<%=request.getContextPath() %>/ApplicationServlet?command=defaults" target="rightFrame" class="selected"><img src="<%=request.getContextPath() %>/static/common/images/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
    <li><a href="<%=request.getContextPath() %>/ApplicationServlet?command=imgtable" target="rightFrame"><img src="<%=request.getContextPath() %>/static/common/images/icon02.png" title="模型管理" /><h2>模型管理</h2></a></li>
    <li><a href="<%=request.getContextPath() %>/ApplicationServlet?command=imglist"  target="rightFrame"><img src="<%=request.getContextPath() %>/static/common/images/icon03.png" title="模块设计" /><h2>模块设计</h2></a></li>
    <li><a href="<%=request.getContextPath() %>/ApplicationServlet?command=tools"  target="rightFrame"><img src="<%=request.getContextPath() %>/static/common/images/icon04.png" title="常用工具" /><h2>常用工具</h2></a></li>
    <li><a href="<%=request.getContextPath() %>/ApplicationServlet?command=computer" target="rightFrame"><img src="<%=request.getContextPath() %>/static/common/images/icon05.png" title="文件管理" /><h2>文件管理</h2></a></li>
    <li><a href="<%=request.getContextPath() %>/ApplicationServlet?command=tab"  target="rightFrame"><img src="<%=request.getContextPath() %>/static/common/images/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li>
    </ul> --%>

<div class="topright">
    <ul>
        <li><span><img src="<%=request.getContextPath() %>/static/common/images/help.png" title="帮助"
                       class="helpimg"/></span><a href="<%=request.getContextPath() %>/LoginServlet?command=testpage"
                                                  target="_top">帮助</a></li>
        <li><a href="<%=request.getContextPath() %>/LoginServlet?command=updatepasswordpage"
               target="rightFrame">修改密码</a></li>
        <li><a href="<%=request.getContextPath() %>/LoginServlet?command=loginout" target="_parent">退出</a></li>
    </ul>

    <div class="user">
        <span><a href="<%=request.getContextPath() %>/LoginServlet?command=user-detail"
                 target="rightFrame">${user.name }</a></span>
        <i>消息</i>
        <b>5</b>
    </div>

</div>

</body>
</html>
