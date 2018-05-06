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
    <li><a href="#">系统管理页面</a></li>
    </ul>
    </div>
   <table border="1px" class="table">
   <thead>
		<tr>
		    <th>标号</th>
			<th>题目</th>
			<th>附件</th>
			<th>题型</th>
			<th>所属课程</th>
			<th>选项A</th>
			<th>选项B</th>
			<th>选项C</th>
			<th>选项D</th>
			<th>答案</th>
			<th>难度</th>
			<th>分析</th>
			<th>关键词</th>
			<th>操作者</th>
			<th>操作时间</th>
			<th>状态</th>
			<th>备注</th>
		</tr>
	</thead>
	<tbody>
	  <c:if test="${empty questionList }">
	  <td colspan = "7" align="center">暂时无数据显示</td>
	  </c:if>
	  <c:forEach items="${questionList }" var="question" varStatus="v">
			<tr>
				<th scope="row">${v.count }</th>
				<td>${question.question }</td>
				<td>${question.attachment}</td>
				<td>${question.questionTypeStr}</td>
				<td>${question.courseIdStr}</td>
				<td>${question.answerA}</td>
				<td>${question.answerB}</td>
				<td>${question.answerC}</td>
				<td>${question.answerD}</td>
				<td>${question.answer}</td>
				<td>${question.difficultyStr}</td>
				<td>${question.analysis}</td>
				<td>${question.keywords}</td>
				<td>${question.user$Name}</td>
				<td><fmt:formatDate value="${question.operateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${question.stateStr}</td>
				<td>${question.memo}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>  
<script> 
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
	 	$(this).css('background', '#123456')
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
