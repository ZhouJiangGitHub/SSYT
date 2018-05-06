 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=request.getContextPath() %>/static/common/plugin/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/static/common/plugin/toastr/toastr.css" rel="stylesheet" type="text/css" /> 
<link href="<%=request.getContextPath() %>/static/common/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/static/common/js/jquery.js"></script> 
<title>无标题文档</title>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">角色信息修改页面</a></li>
    </ul>
    </div>
    <div  align="left">
    <form action="<%=request.getContextPath() %>/RoleServlet?command=update&id=${sysRoleEntity.id}" method="post" onsubmit="return roleUpdate();">
			<li><label>角色名称：</label><input type="text" class="dfinput" name="name" id="name" onblur="validateName();" value="${sysRoleEntity.name }" /><span id="nameInfo"></span></li>
			<li><label>状　　态：</label><select name="state" id ="state" >
			   <option value="1">启用</option>
			  <option value="2">禁用</option>
			</select>
			</li>
			<li><label>备　　注：</label><input type="text" class="dfinput" name="memo" id="memo"  value="${sysRoleEntity.memo }" /></li>
			<input type="submit" class="btn btn-default" id="submit" value="修改">
   </form>
   </div>
   <script>
   function validateName() {
		var name = document.getElementById("name").value;
		if (name == '') {
			
		}
		
		var nameInfoDOM = document.getElementById("nameInfo");
		nameInfoDOM.innerHTML = "<font color='red'>检测中...</font>"
			
		// 1、创建XMLHttpRequest对象（找到一个人做事）
		var xmlhttp;
		if (window.XMLHttpRequest) {
		   // code for IE7+, Firefox, Chrome, Opera, Safari
		   xmlhttp = new XMLHttpRequest();
		} else {
		   // code for IE6, IE5
		   xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		// 2、指定XMLhttpRequest的到达地址及到达方式（告诉他做什么？去哪里做？怎么做？）
		xmlhttp.open("GET","<%=request.getContextPath()%>/AjaxServlet?name=" + encodeURI(name) + "&date=" + new Date().getTime(), true);
		
		// 3、XMLhttpRequest发送（派他出去）
		xmlhttp.send();
		
		// 4、响应结果（得到他做事的结果）
		xmlhttp.onreadystatechange = function() {
		  if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var submitDOM = document.getElementById('submit');
		    if (xmlhttp.responseText == 1) {
		    	nameInfoDOM.innerHTML = "<font color='red'>该角色不可为空！</font>"
		    	submitDOM.disabled = true;
		    } else {
		    	nameInfoDOM.innerHTML = "<font color='green'>该角色可以使用！</font>"
		    	submitDOM.disabled = false;
		    }
		   }
		}
	}
     
	</script>
<script src="<%=request.getContextPath() %>/static/common/plugin/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/static/common/js/RoleUpdate.js"></script>
</html>

