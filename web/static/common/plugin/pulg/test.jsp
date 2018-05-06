<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet"  href="css/login.css">
<title>欢迎登录后台管理系统</title>


</head>

<body >


	<form data-validator-option="{timely:2}">
	    <div class="form-item">
			<input type="text" name="user[email]" data-rule="required;email" placeholder="Email">
		</div>
	    <div class="form-item">
			<input type="password" name="password" data-rule="required;" placeholder="Password">
		</div>
	    <div class="form-item">
	        <button type="submit">Submit</button>
	    </div>
	</form>
    
    <script src="jquery-1.11.1.min.js"></script>
	<script src="jquery.validator.js"></script>
	<script src="zh-CN.js"></script>
</body>

</html>