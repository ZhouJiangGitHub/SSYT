<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=request.getContextPath() %>/static/common/plugin/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/static/common/plugin/toastr/toastr.css" rel="stylesheet" type="text/css" /> <link href="<%=request.getContextPath() %>/static/common/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/static/common/js/jquery-1.12.2.js"></script>
<script src="<%=request.getContextPath() %>/static/common/plugin/layer-2.1/layer.js"></script> 
<title>无标题文档</title>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">用户数据备份查询页面</a></li>
    </ul>  
    </div>
     <ul class="toolbar1">
        <%-- <button id="backups"><span><img src="<%=request.getContextPath() %>/static/common/images/t01.png" align="right"/></span></button> --%>
         <li class="click"><a id="backups"href="#" target="rightFrame"><span><img src="<%=request.getContextPath() %>/static/common/images/t01.png" align="right"/></span>添加备份</a></li> 
    </ul> 
   <table border="1px" class="table">
   <thead>
		<tr>
		    <th>编号</th>
			<th>存放文件</th>
			<th>备份时间</th>
			<th>备份备注</th>
			<th>还原时间</th>
			<th>还原备注</th>
			<th>操作者</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${assisEntity}" var="assisEntity" varStatus="v">
			<tr>
				<th scope="row">${v.count }</th>
				<td>${assisEntity.bak_url }</td>
				<td>${assisEntity.bak_time}</td>
				<td>${assisEntity.bak_memo }</td>
				<td>${assisEntity.restore_time }</td>
				<td>${assisEntity.restore_memo}</td>
			    <td>${assisEntity.operate_user_id}</td> 			
				<td><a id="restoration${assisEntity.id } "href="javascript:restoration();" target="rightFrame">还原</a></td>
			</tr>
		</c:forEach>
	</tbody>
	
</table>  
<script >
<%-- $('#restoration').on('click', function(){
    layer.open({
        type: 1,
        area: ['600px', '260px'],
        shadeClose: true, //点击遮罩关闭
        content: '\<\div style="padding:20px;"><form action="<%=request.getContextPath() %>/AssistServlet?command=restoration" method="post">还原备注:<input><button type="submit">确认还原</button></form>\<\/div>'
    });
}); --%>

function restoration(){
	layer.open({
        type: 1,
        area: ['600px', '260px'],
        shadeClose: true, //点击遮罩关闭
        content: '\<\div style="padding:20px;"><form action="<%=request.getContextPath() %>/AssistServlet?command=restoration" method="post">还原备注:<input><button type="submit">确认还原</button></form>\<\/div>'
    });
}
</script>
<script >
$('#backups').on('click', function(){
    layer.open({
        type: 1,
        area: ['600px', '260px'],
        shadeClose: true, //点击遮罩关闭
        content: '\<\div style="padding:20px;"><form action="<%=request.getContextPath() %>/AssistServlet?command=addbackups" method="post">添加备注:<input type="text"  name="backupsmemeo" id="backupsmemeo"><button type="submit">确认添加</button></form>\<\/div>'
    });
});
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

