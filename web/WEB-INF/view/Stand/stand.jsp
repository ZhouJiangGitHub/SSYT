<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="<%=request.getContextPath() %>/static/common/css/style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/common/plugin/zTree_v3-master/css/demo.css"
          type="text/css">
    <script src="<%=request.getContextPath() %>/static/common/plugin/zTree_v3-master/js/jquery-1.4.4.min.js"></script>
    <script src="<%=request.getContextPath() %>/static/common/plugin/zTree_v3-master/js/jquery.ztree.core.js"></script>

</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">内站信息新增</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form action="<%=request.getContextPath() %>/StandServlet?command=add" method="post">
        <ul class="forminfo">
            <li><label>标题</label><input name="title" id="title" type="text" class="dfinput" onkeyup="validate();"/>
                <span id="titleinfo"></span>

            </li>
            <li><label>内容</label><input name="content" type="text" class="dfinput"/></li>
            <li><label>发送时间</label><input name="send_time" type="text" class="dfinput"/></li>
            <li><label>所属用户表的ID</label><input name="sender_id" type="text" class="dfinput"/></li>
            <li><label>指定接受者(个人)</label><input name="receiver_user_ids" type="text" class="dfinput"/></li>
            <li><label>指定接受者(班级)</label><input name="receiver_class_ids" type="text" class="dfinput"/></li>
            <li><label>过期时间</label><input name="expire_time" type="Date" class="dfinput"/></li>
            <li><label>状　　态</label>
                <div class="vocation">
                    <select class="select1" name="state" id="state">
                        <option value="">--请选择--</option>
                        <option value="1">生效</option>
                        <option value="2">待生效</option>
                        <option value="3">已删除</option>
                    </select>
                </div>
            <li><label>&nbsp;</label><input type="submit" class="btn" value="新增" onclick="return confirm('确定修改吗？');"/>
            </li>
        </ul>
    </form>
</div>
<script>

    var validate = function () {

        var standtitle = document.getElementById('title').value;

        $.get("StandServlet?command=validateClass", {standtitle: standtitle}, function (validateState) {
            if (validateState == 'true') {
                document.getElementById('titleinfo').innerHTML = '<img src="WEB-INF/common/images/err.png"/><font color="red">该标题已存在！</font>';
            } else if (standtitle == false) {
                document.getElementById('titleinfo').innerHTML = '<img src="WEB-INF/common/images/err.png"/><font color="green">该标题不能为空！</font>';
            } else {
                document.getElementById('titleinfo').innerHTML = '';
            }

        });

    }
</script>

</body>

</html>