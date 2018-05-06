<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=request.getContextPath() %>/static/common/plugin/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/static/common/plugin/toastr/toastr.css" rel="stylesheet" type="text/css" /> <link href="<%=request.getContextPath() %>/static/common/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/static/common/js/jquery.js"></script> 
<title>无标题文档</title>
<script src="<%=request.getContextPath()%>/jquery-1.11/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/layer-2.1/layer.js"></script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">系统管理页面</a></li>
    </ul>
    </div>
    <div class="tools">  
     <ul class="toolbar">
        <li >
          <font  color="green" size="50">站内信息详情</font>
        </li>
        </ul>   
        <ul class="toolbar1">
        </ul>   
    </div>
      <table  width="100%" boeder="1" type=" font-weigth:16px" >
       <tr >
       
         <th>编号</th>
         <th>标题</th> 
         <th>内容</th> 
         <th>发送者</th>
         <th>发送角色</th>
         <th>发送时间</th>
         <th>过期时间</th> 
         <th>接收人员</th>
         <th>状态</th> 
         <th>操作</th>
        </tr>
             
          
	        <tr>
	              <th>1</th>
	              <%-- <td>${sta.id}</td>  --%>
                  <td>${sta.title}</td> 
	              <td>${sta.content}</td> 
	              <td>${use.name}</td>
	              <td>${use.rolename}</td>
	              <td>${sta.send_time}</td>
	              <td>${sta.expire_time}</td>
	              <td>${sta.receiver_user_name}</td>

	          <td><c:if test="${sta.state == 0 }">
							<font color="red" id="state${sta.id }">待生效</font>
						   </c:if> <c:if test="${cla.state != 0 }">
							<font color="green" id="state${sta.id }">生效</font>
						</c:if></td>
		
					<td><a id="link${sta.id }"
						href="javascript:updateState('<%=request.getContextPath()%>/StandServlet?command=updateState&id=${sta.id}&state=${sta.state}','${sta.id }')">
							<c:if test="${sta.state == 1 }">
								待生效
							</c:if> <c:if test="${sta.state != 1 }">
				 				生效
							</c:if>
					</a></td>
	        </tr>

		 
    </table>
    <script>

		var updateState = function(url, id, obj) {
			$.get(url + '&time=' + new Date().getTime(), function(result) {
				if (result == 1) {
					var preShowInfo = $('#state' + id).html();
					if (preShowInfo == '待生效') {
						$('#state' + id).html('生效').css('color', 'green');
					} else {
						$('#state' + id).html('待生效').css('color', 'red');
					}

					var preLinkInfo = $.trim($('#link' + id).html());
					if (preLinkInfo == '生效') {
						$('#link' + id).html('待生效').css('color','blue');
					} else {
						$('#link' + id).html('生效').css('color','blank');
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