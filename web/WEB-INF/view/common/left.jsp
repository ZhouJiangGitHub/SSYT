<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=request.getContextPath() %>/static/common/css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath() %>/static/common/js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})		
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>管理目录</div>
    

 	<dl class="leftmenu">
      <c:forEach items="${mainMenus }" var="mainMenu">   
		<dd>
		   	<div class="title">
		    	<span><img src="<%=request.getContextPath() %>/static/common/images/leftico01.png" /></span>${mainMenu.rightname }
		    </div>
	    	<ul class="menuson">
	    		<c:forEach items="${childMenus }" var="childMenu">
	    			<c:if test="${childMenu.parentId == mainMenu.rightId}">
	    			
	        			<li><cite></cite><a href="${childMenu.righturl }" target="rightFrame">${childMenu.rightname }</a><i></i></li>
	        			
	        		</c:if>
	       		</c:forEach>
	        </ul>    
	    </dd> 
	 </c:forEach>
   </dl> 
   
</body>
</html>
