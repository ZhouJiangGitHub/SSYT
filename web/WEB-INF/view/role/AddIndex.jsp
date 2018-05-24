<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <script src="<%=request.getContextPath()%>/jquery-1.11/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/layer-2.1/layer.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="<%=request.getContextPath() %>/static/common/plugin/bootstrap-3.3.5/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/static/common/plugin/toastr/toastr.css" rel="stylesheet"
          type="text/css"/>
    <link href="<%=request.getContextPath() %>/static/common/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/common/js/jquery.js"></script>
    <title>无标题文档</title>
</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">系统管理页面</a></li>
    </ul>
</div>
<table border="1px" class="table">
    <thead>
    <tr>
        <th>编号</th>
        <th>角色名</th>
        <th>状态</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${empty pageModel.list }">
        <td colspan="6" align="center">暂时无数据显示</td>
    </c:if>
    <c:forEach items="${pageModel.list }" var="role" varStatus="v">
        <tr>
            <th scope="row">${v.count }</th>
            <td>${role.name }</td>
            <td>${role.state }</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="6" align="right"><a
                href="javascript:submitForm('<%=request.getContextPath()%>/RoleServlet?command=listPage&pageNo=${pageModel.first}')">首页</a>
            <a
                    href="javascript:submitForm('<%=request.getContextPath()%>/RoleServlet?command=listPage&pageNo=${pageModel.pre}')">上一页</a>
            <a
                    href="javascript:submitForm('<%=request.getContextPath()%>/RoleServlet?command=listPage&pageNo=${pageModel.next}')">下一页</a>
            <a
                    href="javascript:submitForm('<%=request.getContextPath()%>/RoleServlet?command=listPage&pageNo=${pageModel.last}')">尾页</a>
            当前为第${pageModel.pageNo }页,共${pageModel.totalPage}条记录。
        </td>
    </tr>

    </tbody>
</table>
<script>
    var tr = $('tr:gt(0)');
    tr.click(function () {
        var preBackgroundColor = $(this).css('background-color');
        if (preBackgroundColor == 'rgb(18, 52, 86)') {
            $(this).css('background', 'gray');
        } else {
            $(this).css('background', 'rgba(0, 0, 0, 0)');
        }
    }).mouseover(function () {
        var preBackgroundColor = $(this).css('background-color');
        if (preBackgroundColor != 'rgb(128, 128, 128)') {
            $(this).css('background', '#123456')
        }
    }).mouseout(function () {
        var preBackgroundColor = $(this).css('background-color');
        if (preBackgroundColor != 'rgb(128, 128, 128)') {
            $(this).css('background', 'white')
        }
    })

    var submitForm = function (url) {
        var myForm = document.getElementById('myForm');
        myForm.action = url;
        myForm.submit();
    }

    var updateState = function (url, id, obj) {
        $.get(url + '&time=' + new Date().getTime(), function (result) {
            if (result == 1) {
                var preShowInfo = $('#state' + id).html();
                if (preShowInfo == '已禁用') {
                    $('#state' + id).html('启用').css('color', 'green');
                } else {
                    $('#state' + id).html('已禁用').css('color', 'red');
                }

                var preLinkInfo = $.trim($('#link' + id).html());
                if (preLinkInfo == '启用') {
                    $('#link' + id).html('禁用');
                } else {
                    $('#link' + id).html('启用');
                }

                layer.msg('状态更新成功！', {
                    icon: 1
                });
            } else {
                layer.msg('状态更新失败！', {
                    icon: 0
                });
            }
        });
    }
</script>

</body>
</html>

