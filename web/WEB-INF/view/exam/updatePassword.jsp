 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=request.getContextPath() %>/static/common/plugin/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/static/common/plugin/toastr/toastr.css" rel="stylesheet" type="text/css" /> <link href="<%=request.getContextPath() %>/static/common/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/static/common/js/jquery.js"></script> 
<title>无标题文档</title>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">用户信息修改页面</a></li>
    </ul>
    </div> 
    <c:if test="${!empty errorInfo }">
		<div class="alert alert-danger alert-dismissible fade in" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${errorInfo }</strong>
		</div>
	</c:if>
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    
    <ul class="forminfo">
    <form action="<%=request.getContextPath() %>/SysServlet?command=changePassword&Id=${sysUserEntity.id}&oldPassword=${sysUserEntity.password}" method="post">
	<li><label>新   密    码 ：</label><input type="text"  class="form-control" name="newPassword" id="newPassword" value="" /></li>
    <li><label>重复新密码 ：</label><input type="text"  class="form-control" name="newPassword" id="newPassword" value="" /></li>
    <li><label>旧   密    码 ：</label><input type="text"  class="form-control" name="inPutpassword" id="inPutpassword" value=""/></li>
    <input type="submit" class="btn btn-default" value="修改">
    </form> 
    </ul>
   </body>
</html>
