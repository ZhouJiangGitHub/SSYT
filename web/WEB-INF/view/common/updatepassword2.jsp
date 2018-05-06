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
    <li><a href="#">个人信息</a></li>
    <li><a href="#">修改密码</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
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
    </div>
    
    
  
    
    
<script type="text/javascript">

/**
 * 
 *密码修改
 * 
 */
function updatePW()
{
	var newPW = document.getElementById('newPW').value;
	var id = "${user.id}";
	
	location="<%=request.getContextPath() %>/LoginServlet?command=updatePW&opeartion=update&id="+id+"&newPW="+newPW;
}


/**
 * 
 *验证密码
 * 
 */
function validate(PW,opeartion)
{
	var xmlhttp;
	
	var id = "${user.id}";
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	      //执行操作
		  
	      //验证原名密码
	      if(xmlhttp.responseText == 0){
	    	  
			  document.getElementById('oldPWinfo').innerHTML="原密码错误！";
			  document.getElementById('updateBT').disabled='disabled';
			  document.getElementById('newPW').disabled='disabled';
			  document.getElementById('repPW').disabled='disabled';
	      }
	      if(xmlhttp.responseText == 1){
	    	  document.getElementById('oldPWinfo').innerHTML="";
	    	  document.getElementById('newPW').disabled='';
	      }
	      
	      //验证新密码
	      
	      
		  if(xmlhttp.responseText == -1){
	    	  
			  document.getElementById('newPWinfo').innerHTML="密码不能小于5位！";
			  document.getElementById('repPW').disabled='disabled';
	      }
		 if(xmlhttp.responseText == -2){
	    	  document.getElementById('newPWinfo').innerHTML="密码不能大于12位！";
	    	  document.getElementById('repPW').disabled='disabled';
	      }
		 if(xmlhttp.responseText == 2){
	    	  document.getElementById('newPWinfo').innerHTML="";
	    	  document.getElementById('repPW').disabled='';
	      }
	    }
	  }
	xmlhttp.open("POST","<%=request.getContextPath() %>/LoginServlet?command=updatePW&opeartion="+opeartion+"&id="+id+"&PW="+PW,true);
	
	xmlhttp.send();
}

/**
 * 
 *验证旧密码
 * 

function validateOldPW()
{
	
	var newPW = document.getElementById('oldPW').value;
	var oldPW = "${user.password}";
	if(newPW == "" || newPW == null){
		document.getElementById('oldPWinfo').innerHTML="原密码不能为空！";
	}else if(newPW != oldPW){
		document.getElementById('oldPWinfo').innerHTML="原密码错误！";
	}else{
		//成功（可添加图标）
	}
} 

/*
 * 验证新密码

function validateNewPW()
{
	
	var newPW = document.getElementById('newPW').innerHTML;
	if(newPW == "" || newPW == null){
		document.getElementById('newPWinfo').innerHTML='密码不能为空！';
	}else if(newPW.length < 5){
		document.getElementById('newPWinfo').innerHTML='密码不能小于五位！';
	}
}

 */

/*
 * 验证第二次输入的新密码
 */
function validateRepPW()
{
	 
	var newPW = document.getElementById('newPW').value;
	var repPW = document.getElementById('repPW').value;
	
	if(newPW != repPW){
		
		document.getElementById('repPWinfo').innerHTML='两次输入的密码不一致！';
		document.getElementById('updateBT').disabled='disabled';
	}else{
		document.getElementById('repPWinfo').innerHTML="";
		document.getElementById('updateBT').disabled='';
	}
	
}
  

</script>


</body>

</html>
