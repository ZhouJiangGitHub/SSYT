<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
         正在跳转...
    <script>
        setTimeout(function(){
        	
        location="<%=request.getContextPath()%>/StandServlet?command= List";	
        }, 0) ;
         
        var tr = $('tr:gt(0)');
        tr.click(function() {
        	var preBackgroundColor = $(this).css('background-color');
        	if (preBackgroundColor == 'rgb(18, 52, 86)') {
        		$(this).css('background', 'gray');
        	} else {
        		$(this).css('background', 'rgba(0, 0, 0, 0)');
        	}
        }).mouseover(function() {
        	 var preBackgroundColor = $(this).css('background-color');
        	 if (preBackgroundColor != 'rgb(128, 128, 128)') {
        	 	$(this).css('background', '#C0D9D9 48')
        	 }
         }).mouseout(function(){
        	 var preBackgroundColor = $(this).css('background-color');
        	 if (preBackgroundColor != 'rgb(128, 128, 128)') {
        		$(this).css('background', 'white')
        	 }
         })
         
    </script>
</body>
</html>