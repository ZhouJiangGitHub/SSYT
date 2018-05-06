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
          <form id="myForm" action="<%=request.getContextPath()%>/ClassServlet?command=List" method="post">
         班级名:<input type="text" name="name"  value="${cla.name }" class="form-control"  style="width:100px ;display: inline"/>
         <button class="btn btn-default">查询</button>
         </form>
        </li>
        </ul>   
        <ul class="toolbar1">
        <li class="click"><a href="<%=request.getContextPath() %>/ClassServlet?command=classAddPage" target="rightFrame"><span><img src="<%=request.getContextPath() %>/static/common/images/t01.png" /></span>新增班级</a></li>
        </ul>   
    </div>	
      <table  width="100%" boeder="1" type=" font-weigth:16px" >
       <tr >
         <th>编号</th>
         <th>班级名称</th> 
         <th>关联课程</th>
         <th>操作者</th>
         <th>操作时间</th>
         <th>备注</th>
         <th>状态</th> 
         <th></th> 
         <th>操作</th>
        </tr>
             
            <c:forEach items="${pageModel.list }" var="cla" varStatus="v">
	        <tr>
	        <th>${ (pageModel.pageNo-1)*pageModel.pageSize+v.count }</th>
	           <td>${cla.name}</td> 
	           <td>${cla.courseName}</td> 
	           <td>${cla.userName}</td>
	           <td>${cla.operate_time}</td>
	         <td>${cla.memo}</td>
	          <td><c:if test="${cla.state == 0 }">
							<font color="red" id="state${cla.id }">已禁用</font>
						</c:if> <c:if test="${cla.state != 0 }">
							<font color="green" id="state${cla.id }">启用</font>
						</c:if></td>
		
					<td><a id="link${cla.id }"
						href="javascript:updateState('<%=request.getContextPath()%>/ClassServlet?command=updateState&id=${cla.id}&state=${cla.state}','${cla.id }')">
							<c:if test="${cla.state == 1 }">
								禁用
							</c:if> <c:if test="${cla.state != 1 }">
				 				启用
							</c:if>
					</a></td>
	         <td><a href="ClassServlet?command=update&id=${cla.id }">修改</a>|
	         <a href="<%=request.getContextPath()%>/ClassServlet?command=delete&id=${cla.id }">删除</a></td>
	        </tr>
	         </c:forEach>
	         
	         <tr>
				<td colspan="9" align="right"><a
					href="javascript:submitForm('<%=request.getContextPath()%>/ClassServlet?command=List&pageNo=${pageModel.first}')">首页</a>　
					<a
					href="javascript:submitForm('<%=request.getContextPath()%>/ClassServlet?command=List&pageNo=${pageModel.pre}')">上一页</a>　
					<a
					href="javascript:submitForm('<%=request.getContextPath()%>/ClassServlet?command=List&pageNo=${pageModel.next}')">下一页</a>　
					<a
					href="javascript:submitForm('<%=request.getContextPath()%>/ClassServlet?command=List&pageNo=${pageModel.last}')">尾页</a>　
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
					if (preShowInfo == '已禁用') {
						$('#state' + id).html('启用').css('color', 'green');
					} else {
						$('#state' + id).html('已禁用').css('color', 'red');
					}

					var preLinkInfo = $.trim($('#link' + id).html());
					if (preLinkInfo == '启用') {
						$('#link' + id).html('禁用').css('color','blue');
					} else {
						$('#link' + id).html('启用').css('color','blank');
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
			 	$(this).css('background', '#C0D9D9 48')
			 }
		 }).mouseout(function(){
			 var preBackgroundColor = $(this).css('background-color');
			 if (preBackgroundColor != 'rgb(128, 128, 128)') {
				$(this).css('background', 'white')
			 }
		 }) */
	</script>
</body>


</html>