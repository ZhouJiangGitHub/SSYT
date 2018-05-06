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

</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">个人信息</a></li>
    <li><a href="#">修改密码</a></li>
    </ul>
    </div>
    
<%--     <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    	<form action="<%=request.getContextPath() %>/LoginServlet?command=updatePW&opeartion=update&id=${user.id }" method = "post" target="_top">
		    <ul class="forminfo">
			    <li><label>原密码</label><input name="oldpassword" id="oldPW" type="password" class="dfinput"   onchange="validate(this.value,'validateOldPW');"/><i id="oldPWinfo" style="color:red;"></i></li>
			    <li><label>新密码</label><input name="newpassword" id="newPW" type="password" class="dfinput" disabled='disabled'   onchange="validate(this.value,'validateNewPW');"/><i id="newPWinfo" style="color:red;"></i></li>
			    <li><label>新密码</label><input name="reppassword" id="repPW" type="password" class="dfinput" disabled='disabled'  onchange="validateRepPW()" /><i id="repPWinfo" style="color:red;"></i></li>
			    
			  <!--  onBlur='validateOldPW();' -->
			   <!--  <li><label>电　　话</label><textarea name="" cols="" rows="" class="textinput"></textarea></li> -->
			    <li><label>&nbsp;</label><input id="updateBT" type="submit" class="btn" value="修改" disabled='disabled' onclick="return confirm('确定修改吗？')"/></li>
			</ul>
    	</form>
    </div> --%>
    
  <div class="formbody">  
  	<div class="formtitle"><span>基本信息</span></div>
  	<form id="updatePW"  data-validator-option="{timely:3}" action = "<%=request.getContextPath() %>/LoginServlet?command=updatePW&opeartion=update&id=${user.id }" method ="post" target="_top">
	   
		<fieldset>
		    <div class="form-item">
				<li><label>&nbsp;&nbsp;原密码&nbsp;&nbsp;</label>
				<input id="PW" class="dfinput" type="password" name="oldpassword"  placeholder="原密码" onkeyup="validateOldPW();"/>&nbsp;&nbsp;<i id="oldPWinfo" style="color:red;"></i></li><br/>
			</div>
		    <div class="form-item">
				<li><label>&nbsp;&nbsp;新密码&nbsp;&nbsp;</label>
				<input class="dfinput" id="newPW" type="password" name="newpassword" data-rule="required;password;" placeholder="新密码" onkeyup="validateRepPW();"  maxlength="16"/></li><br/>
			</div>
			 <div class="form-item">
				<li><label>&nbsp;&nbsp;新密码&nbsp;&nbsp;</label>
				<input class="dfinput" id="repPW" type="password" name="reppassword" data-rule="required;password;" placeholder="确认密码" onkeyup="validateRepPW()"  maxlength="16"/>&nbsp;&nbsp;<i id="repPWinfo" style="color:red;" ></li><br/>
			</div>
		</fieldset>
	    <div class="form-item">
	        <label>&nbsp;&nbsp;&nbsp;&nbsp;</label><button id="sub" class="btn" type="submit" disabled='disabled' onclick="return confirm('确定修改吗？')">修改</button>
	    </div>
	</form>
  </div>	
	<script >
	

	function validateOldPW()
	{
		
		var PW = document.getElementById('PW').value;
		var oldPW = "${user.password}";
		
		if(PW != oldPW){
			
			document.getElementById('oldPWinfo').innerHTML="原密码错误！";
		}else{
			//成功（可添加图标）
			document.getElementById('oldPWinfo').innerHTML="";
			
		}
	} 
	
	/*
	 * 验证第二次输入的新密码
	 */
	function validateRepPW()
	{
		 
		var newPW = document.getElementById('newPW').value;
		var repPW = document.getElementById('repPW').value;
		
		if(newPW != repPW && repPW.length > 5){
			
			document.getElementById('repPWinfo').innerHTML='两次输入的密码不一致！';
			document.getElementById('sub').disabled='disabled';
		}else{
			document.getElementById('repPWinfo').innerHTML="";
			document.getElementById('sub').disabled='';
		}
		
	}
	
	
	
	</script>
	


    <script src="<%=request.getContextPath() %>/static/common/plugin/pulg/jquery-1.11.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/static/common/plugin/pulg/jquery.validator.js"></script>
    <script src="<%=request.getContextPath() %>/static/common/plugin/pulg/zh-CN.js"></script>


	  

	
</body>

</html>
