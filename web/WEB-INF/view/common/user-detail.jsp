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

<link href="<%=request.getContextPath() %>/static/common/plugin/pulg/jquery.validator.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath() %>/static/common/plugin/jquery.min.js"></script>

</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">个人信息</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    
    <form data-validator-option="{timely:2}"  action="<%=request.getContextPath() %>/LoginServlet?command=userupdate&id=${user.id }" method="post" target="_top">
	    <fieldset>
	    <ul class="forminfo">
	    <li><label>用户账号</label><input name="code" type="text" class="dfinput" value="${user.code }" disabled="disabled"/><i></i></li>
	    
	    <li><label>用户名称</label><input name="name" type="text" class="dfinput" value="${user.name }" disabled="disabled"/></li>
	    
	    <li><label>性　　别</label><cite><input name="sex" type="radio" value='1' checked="checked" disabled="disabled"/>男&nbsp;&nbsp;&nbsp;&nbsp;<input name="sex" type="radio" value='1' disabled="disabled"/>女</cite></li>
	    
	    <li><label>电　　话</label><input id="phone" name="phone" type="text" class="dfinput" value="${user.phone }"  data-rule="required; mobile" data-rule-mobile="[/^1[3458]\d{9}$/, '请检查手机号格式']" onkeyup="validatePhone(${user.id});"/><i id="phoneInfo" style="color:red;"></i></li>
	    
	    <li><label>邮　　箱</label><input id="email" name="email" type="text" class="dfinput" value="${user.email }" data-rule="required; email" onkeyup="validateEmail(${user.id});"/><i id="emailInfo" style="color:red;"></i></li>
	    
	    <li><label>创建时间</label><input name="createTime" type="text" class="dfinput" value="${user.createTime }" disabled="disabled"/></li>
	    
	    <li><label>所属角色</label><input name="role" type="text" class="dfinput" value="${user.role$name }" disabled="disabled"/></li>
	    
	    <c:if test="${user.classId != 0 }">
	    
	    	 <li><label>所属班级</label><input name="class" type="text" class="dfinput" value="${user.class$name }" disabled="disabled"/></li>
	    </c:if>
	    
	   <!--  <li><label>电　　话</label><textarea name="" cols="" rows="" class="textinput"></textarea></li> -->
	    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="修改" onclick="return confirm('确定修改吗？');"/></li>
	    </ul>
	    </fieldset>
    </form>
    
    </div>
    
    <script>
    

   		var validatePhone = function(userId){
   			
   			var phone = document.getElementById('phone').value;
   			
   			
   			$.get("LoginServlet?command=validatePhone&time="+new Date().getTime(),{phone:phone,userId:userId},function(state){
   				alert(state);
   				if(state == 1){
   					
		    		document.getElementById('phoneInfo').innerHTML='';
		    	}
		    	if(state == -1){
		    		
		    		document.getElementById('phoneInfo').innerHTML='该电话号码已存在';
		    	}
   			});
   			
   		}	
   			

   		
	var validateEmail = function(userId){
   			
   			var email = document.getElementById('email').value;
   			
   			$.get("LoginServlet?command=validateEmail&time="+new Date().getTime(),{email:email,userId:userId},function(state){
   				alert(state);
   				if(state == '1'){
   					
		    		document.getElementById('emailInfo').innerHTML='';
		    	}
		    	if(state == '-1'){
		    		
		    		document.getElementById('emailInfo').innerHTML='该邮箱已存在';
		    	}
   			});
   			
   		}	
    </script>
  

	<script src="<%=request.getContextPath() %>/static/common/plugin/pulg/jquery-1.11.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/static/common/plugin/pulg/jquery.validator.js"></script>
    <script src="<%=request.getContextPath() %>/static/common/plugin/pulg/zh-CN.js"></script>
	
</body>

</html>
