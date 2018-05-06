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
    <li><a href="#">内站信息删除</a></li>
    </ul>
    </div>
    
    <div class="formbody">
     <div class="formtitle"><span>基本信息</span></div>
     <form action="<%=request.getContextPath() %>/StandServlet?command=delete&id=${sta.id}" method="post">
	    <ul class="forminfo">
	      <li><label>编号</label>${sta.id }<input type="hidden" name="id" value="${sta.id }" /></li>
		   <li><label>标题</label><input name="title" id="title" type="text" class="dfinput" value="${sta.title}"/>
	    </li>
	    <li><label>发送者</label><input name="sender_id" type="text" class="dfinput" value="${sta.sender_id}" /></li>
	    <li><label>发送时间</label><input name="send_time" type="Date" class="dfinput" value="${sta.send_time}" /></li>
	    <li><label>过期时间</label><input name="expire_time" type="Date" class="dfinput"  value="${sta.expire_time }"/></li>
	    <li><label>状　　态</label>
	    <select  name="state"  style="display:inline"  class="dfinput" value="${sta.state}"/></li>
	    <option value="1"  <c:if test="">selected=selected</c:if>>生效</option>
	     <option value="2"  <c:if test="">selected=selected</c:if>>待生效</option>
	     <option value="3"  <c:if test="">selected=selected</c:if>>已删除</option>
	    </select>
	    <li><label>&nbsp;</label><input  type="submit" class="btn" value="删除" onclick="return confirm('确定删除吗？');" /></li>
	    </ul>
    </form>
    
    </div>

</body>

</html>