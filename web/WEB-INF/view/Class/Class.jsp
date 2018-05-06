
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
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/common/plugin/zTree_v3-master/css/demo.css" type="text/css">
	<script  src="<%=request.getContextPath() %>/static/common/plugin/zTree_v3-master/js/jquery-1.4.4.min.js"></script>
	<script  src="<%=request.getContextPath() %>/static/common/plugin/zTree_v3-master/js/jquery.ztree.core.js"></script>

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
    
    <form action="<%=request.getContextPath() %>/ClassServlet?command=add" method="post">
	    <ul class="forminfo">
	    <li><label>班级名称</label><input name="name" id="name" type="text" class="dfinput" onkeyup="validate();"/>
	    <span id="nameinfo"></span>
	    
	    </li>
	    <li><label>关联课程</label><input name="course_id" type="text" class="dfinput"/></li>
	    <li><label>操 作 者</label><input name="operate_user_id" type="text" class="dfinput" /></li>
	    <li><label>操作时间</label><input name="operate_time" type="text" class="dfinput" /></li>
	    <li><label>状　　态</label>
	    <select  name="state"  style="display:inline"  class="dfinput"/></li>
	    <option value="1"  <c:if test="">selected=selected</c:if>>启用</option>
	     <option value="2"  <c:if test="">selected=selected</c:if>>禁用</option><ption>
	    </select>
	    <li><label>备　　注</label><textarea name="memo" rows="" cols="" class="textinput"></textarea></li>

	    <li><label>&nbsp;</label><input  type="submit" class="btn" value="新增" onclick="return confirm('确定修改吗？');" /></li>
	    </ul>
    </form>
    </div>
    <script>
  
var validate = function(){
	
		var className = document.getElementById('name').value;
		
		$.get("ClassServlet?command=validateClass",{className:className},function(validateState){
			if(validateState == 'true'){
				document.getElementById('nameinfo').innerHTML='<img src="WEB-INF/common/images/err.png"/><font color="red">该班级名已存在！</font>';
			}else if(className ==false){
				document.getElementById('nameinfo').innerHTML='<img src="WEB-INF/common/images/err.png"/><font color="green">该班级名不能为空！</font>';
			}else{
				document.getElementById('nameinfo').innerHTML='';
			}
			
		});
			
	}
    </script>

</body>

</html>