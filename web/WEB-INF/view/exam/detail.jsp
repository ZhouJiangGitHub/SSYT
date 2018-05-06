 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html >
<head>
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
    <li><a href="#">考试详情页面</a></li>
    </ul>
    </div>
   <table border="1px" class="table">
   <thead>
		<tr>
			<th>考试时间段</th>
			<th>考试教室</th>
			<th>试卷名称</th>
            <th>接收人员</th>
            <th>接收班级</th>
            <th>操作者</th>
            <th>操作时间</th>
			<th>状态</th>
			<th>备注</th>
		</tr>
	</thead>
	<tbody>
			<tr>
				<td>
				    <fmt:formatDate value="${examDetailEntity.exam_time_start }" pattern="yyyy-MM-dd HH:mm:ss"/> -
				    <fmt:formatDate value="${examDetailEntity.exam_time_stop }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${examDetailEntity.exam_classroom}</td>
				<td>${examDetailEntity.paperName}</td>
				<td>${examDetailEntity.recetName}</td>
				<td>${examDetailEntity.className}</td>
				<td>${opread_id}</td>
				<td> <fmt:formatDate value="${examDetailEntity.operate_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
				<td>
					<c:if test="${examDetailEntity.state == 1 }">
						${examDetailEntity.states }
					</c:if>
					<c:if test="${examDetailEntity.state == 2 }">
						${examDetailEntity.states }
					</c:if>
					<c:if test="${examDetailEntity.state == 3 }">
						${examDetailEntity.states }
					</c:if>
				</td>		
				<td>${examDetailEntity.memo}</td>			
			    <td><a href="<%=request.getContextPath()%>/ExamManageServlet?command=getUpdateMessege&id=${examDetailEntity.id}"/>修改</a></td>
			</tr>
	</tbody>
</table>  
</body>
</html>


