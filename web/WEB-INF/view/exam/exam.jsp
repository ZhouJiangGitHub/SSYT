<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>考试记录新增</title>
    <link href="<%=request.getContextPath() %>/static/common/css/style.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">考试记录新增</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>

    <form action="<%=request.getContextPath() %>/ExamServlet?command=add" method="post">
        <ul class="forminfo">
            <li><label>所属用户</label><input name="user_id" type="text" class="dfinput"/><i></i></li>
            <li><label>考试安排</label><input name="exam_plan_id" type="text" class="dfinput"/></li>
            <li><label>得分数</label><input name="get_point" type="text" class="dfinput"/></li>
            <li><label>提交时间</label><input name="submit_time" type="text" class="dfinput"/></li>
            <li><label>开始时间</label><input name="start_time" type="text" class="dfinput"/></li>
            <li><label>是否通过</label>
                <select name="is_pass" type="text" style="display:inline" class="dfinput"/></li>
            <option value="1" <c:if test="">selected=selected</c:if>>通过</option>
            <option value="2" <c:if test="">selected=selected</c:if>>未通过</option>
            <option value="3" <c:if test="">selected=selected</c:if>>未提交或超时</option>
            </select>

            <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="新增"
                                            onclick="return confirm('确定新增吗？');"/></li>
        </ul>
    </form>

</div>

</body>

</html>