<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=request.getContextPath() %>/static/common/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">班级添加</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    
    <form action="<%=request.getContextPath() %>/ClassServlet?command=last" method="post">
	    <ul class="forminfo">
	    <li><label>ID</label>${cla.id }<input type="hidden" name="id" value="${cla.id }" /></li>
		 <li><label>班级名称</label><input name="name" type="text" class="dfinput"  value="${cla.name }"/><i></i></li>
	    <li><label>关联课程</label><input name="course_id" type="text" class="dfinput"  value="${cla.id }"/></li>
	    <li><label>操 作 者</label><input name="operate_user_id" type="text" class="dfinput"  value="${cla.course_id}" /></li>
	    <li><label>操作时间</label><input name="operate_time" type="text" class="dfinput"  value="${cla.operate_time }"/></li>
	    <li><label>状　　态</label><input name="state" type="text" class="dfinput"  value="${cla.state }"/></li>
	    <li><label>备　　注</label><textarea name="memo" rows="" cols="" class="textinput" value="${cla.memo }"></textarea></li>

	    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="修改"  onclick="return confirm('确定修改吗？');"/></li>
	    </ul>
    </form>
    
    </div>

</body>

</html>