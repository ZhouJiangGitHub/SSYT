 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html >
<head>
<script src="<%=request.getContextPath()%>/jquery-1.11/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/layer-2.1/layer.js"></script>
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
    <li><a href="#">系统管理页面</a></li>
    </ul>
    </div>
    <!--  <div align="right"><li class="click"><a href="<%=request.getContextPath() %>/JurisdictionServlet?command=initMenu" target="rightFrame"><span><img src="<%=request.getContextPath() %>/static/common/images/t05.png" />角色授权</span></a></li></div>-->
    <div align="right"><li class="click"><a href="<%=request.getContextPath() %>/RoleServlet?command=addTest" target="rightFrame"><span><img src="<%=request.getContextPath() %>/static/common/images/t05.png" />新增角色</span></a></li></div>
    <form id="myForm" action="<%=request.getContextPath() %>/RoleServlet?command=list" method="post">
		     状态：<select name="state" onchange="submit();">
			<option value="0"
			     <c:if test="${sysRoleEntity.state == 0}">selected="selected"</c:if>>-全部-</option>
			<option value="1"
				<c:if test="${sysRoleEntity.state == 1}">selected="selected"</c:if>>已启用角色</option>
			<option value="2"
				<c:if test="${sysRoleEntity.state == 2}">selected="selected"</c:if>>已禁用角色</option>
		</select>
	</form>
   <table border="1px" class="table">
   <thead>
		<tr>
			<th><span><img src="<%=request.getContextPath() %>/static/common/images/t05.png" />编号</span></th>
			<th><span><img src="<%=request.getContextPath() %>/static/common/images/t05.png" />角色名</span></th>
			<th><span><img src="<%=request.getContextPath() %>/static/common/images/t05.png" />操作者</span></th>
			<th><span><img src="<%=request.getContextPath() %>/static/common/images/t05.png" />操作时间</span></th>
			<th><span><img src="<%=request.getContextPath() %>/static/common/images/t05.png" />备注</span></th>
			<th><span><img src="<%=request.getContextPath() %>/static/common/images/t05.png" />状态</span></th>
			<th><span><img src="<%=request.getContextPath() %>/static/common/images/t05.png" />操作</span></th>
		</tr>
	</thead>
	<tbody>
	  <c:if test="${empty pageModel.list }">
	  <td colspan = "6" align="center">暂时无数据显示</td>
	  </c:if>
		<c:forEach items="${pageModel.list }" var="role" varStatus="v">
			<tr>
				<th scope="row">${v.count }</th>
				<td>${role.name }</td>
				<td>${role.operateUserIdStr}</td>
				<td><fmt:formatDate value="${role.operateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${role.memo }</td>
				<td><c:if test="${role.state == 2 }">
							<font color="red" id="state${role.id }">禁用</font>
						</c:if> <c:if test="${role.state != 2 }">
							<font color="green" id="state${role.id }">启用</font>
						</c:if></td>
				<td><a id="link${role.id }"
						href="javascript:updateState('<%=request.getContextPath()%>/RoleServlet?command=updateState&id=${role.id}&state=${role.state}','${role.id }')">
							<c:if test="${role.state == 1 }">
								禁用
							</c:if> <c:if test="${role.state != 1 }">
								启用
							</c:if>
					</a>　|　<a href = "<%=request.getContextPath() %>/RoleServlet?command=updateMessage&id=${role.id}" onclick="return confirm('是否修改角色？');">角色修改</a></td>
				
			</tr>
		</c:forEach>
	</tbody>
</table> 
<script>

var form = function() {
	var myForm = document.getElementById('myForm');
	myForm.submit();
}

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

		var submitForm = function(url) {
			var myForm = document.getElementById('myForm');
			myForm.action = url;
			myForm.submit();
		}

		var updateState = function(url, id, obj) {
			$.get(url + '&time=' + new Date().getTime(), function(result) {
				if (result == 1) {
					var preShowInfo = $('#state' + id).html();
					if (preShowInfo == '禁用') {
						$('#state' + id).html('启用').css('color', 'green');
					} else {
						$('#state' + id).html('禁用').css('color', 'red');
					}

					var preLinkInfo = $.trim($('#link' + id).html());
					if (preLinkInfo == '启用') {
						$('#link' + id).html('禁用');
					} else {
						$('#link' + id).html('启用');
					}

					layer.msg('状态更新成功！', {
						icon : 1
					});
				} else {
					layer.msg('状态更新失败！', {
						icon : 0
					});
				}
			});
		}
	</script>
 
</body>
</html>

