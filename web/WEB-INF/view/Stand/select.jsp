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
        <li>
          <form id="myForm" action="<%=request.getContextPath()%>/StandServlet?command=List" method="post">
             标题:<input type="text" name="title"  value="${sta.title }" class="form-control"  style="width:100px ;display: inline"/>
         <button class="btn btn-default">查询</button>
         </form>
        </li>
        </ul>   
        <ul class="toolbar1">
        <li class="click"><a href="<%=request.getContextPath() %>/StandServlet?command=standAddPage" target="rightFrame"><span><img src="<%=request.getContextPath() %>/static/common/images/t01.png" /></span>站内信息新增</a></li>
        </ul>   
    </div>	
      <table  width="100%" boeder="1" type=" font-weigth:16px" >
       <tr >
         <th>编号</th>
         <th>标题</th> 
         <th>发送者</th>
         <th>发送角色</th>
         <th>发送时间</th>
         <th>过期时间</th>
         <th>状态</th> 
         <th>操作</th>
        </tr>
             
            <c:forEach items="${pageModel.list }" var="sta" varStatus="v">
	        <tr>
	        <th>${ (pageModel.pageNo-1)*pageModel.pageSize+v.count }</th>
	            <td>${sta.title}</td> 
	           <td>${sta.name}</td>
	           <td>${sta.sys_role_name}</td>
	           <td>${sta.send_time}</td>
	           <td>${sta.expire_time}</td>

	          <td><c:if test="${sta.state == 0 }">
							<font color="red" id="state${sta.id }">待生效</font>
						</c:if> <c:if test="${cla.state != 0 }">
							<font color="green" id="state${sta.id }">生效</font>
						</c:if>
		            <a id="link${sta.id }"
						href="javascript:updateState('<%=request.getContextPath()%>/StandServlet?command=updateState&id=${sta.id}&state=${sta.state}','${sta.id }')">
							<c:if test="${sta.state == 1 }">
								待生效
							</c:if> <c:if test="${sta.state != 1 }">
				 				生效
							</c:if>
					</a></td>
	         <td><a href="<%=request.getContextPath()%>/StandServlet?command=update&id=${sta.id }">修改</a>|
	 
	         <a href="<%=request.getContextPath()%>/StandServlet?command=truedelete&id=${sta.id }">删除</a>
	         <%-- <a href="<%=request.getContextPath()%>/StandServlet?command=delete&id=${sta.id }">确定删除</a>  --%>
	         <a href="<%=request.getContextPath()%>/StandServlet?command=details&id=${sta.id }">站内消息详情 </a></td>
	        </tr>
	         </c:forEach>
	         
	         <tr>
				<td colspan="9" align="right"><a
					href="javascript:submitForm('<%=request.getContextPath()%>/StandServlet?command=List&pageNo=${pageModel.first}')">首页</a>　
					<a
					href="javascript:submitForm('<%=request.getContextPath()%>/StandServlet?command=List&pageNo=${pageModel.pre}')">上一页</a>　
					<a
					href="javascript:submitForm('<%=request.getContextPath()%>/StandServlet?command=List&pageNo=${pageModel.next}')">下一页</a>　
					<a
					href="javascript:submitForm('<%=request.getContextPath()%>/StandServlet?command=List&pageNo=${pageModel.last}')">尾页</a>　
					当前为第${pageModel.pageNo }页,共${pageModel.allRecords }条记录。</td>
			</tr>
		 
    </table>
    <script>
		/* var tr = $('tr:gt(0)');
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
			 	$(this).css('background', '#123456')
			 }
		 }).mouseout(function(){
			 var preBackgroundColor = $(this).css('background-color');
			 if (preBackgroundColor != 'rgb(128, 128, 128)') {
				$(this).css('background', 'white')
			 }
		 }) */
     
		var submitForm = function(url) {
			var myForm = document.getElementById('myForm');
			myForm.action = url;
			myForm.submit();
		}

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