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
<script type="text/javascript" src="<%=request.getContextPath() %>/static/common/plugin/jquery.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/static/common/plugin/layer-2.1/layer.js"></script>
<title>无标题文档</title>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">系统管理页面</a></li>
    </ul>
    </div>
    
    	<br/>
		<form action="QuestionServlet?command=file" enctype="multipart/form-data" method="post">
		
			<span style="font-size:24px;display: inline;">Excel批量导入试题:
			
			</span><input type="file" name = "excelFile" style="display: inline;height:30px;vertical-align:middle;" />
			
			<input class="btn btn-default" type="submit" value="导入" onclick="layeload();"/>
			
		</form>
    <br />
    <div align="right"><li class="click"><a href="<%=request.getContextPath() %>/QuestionServlet?command=addTest" target="rightFrame"><span><img src="<%=request.getContextPath() %>/static/common/images/t05.png" />试题新增</span></a></li></div>
    <form id="conditionForm" action="<%=request.getContextPath() %>/QuestionServlet?command=listParameterPage" method="post">
					 题目：<input type="text" class="form-control" name="question"  style="width:100px ; display: inline" > 
					 题型：<select name="questionType" >
					 <option value="0">-全部-</option>
						 <option value="1"
						 <c:if test="${questionEn.questionType == 1}">selected="selected"</c:if>>单选题</option>
						 <option value="2"
						 <c:if test="${questionEn.questionType == 2}">selected="selected"</c:if>>多选题</option>
						 <option value="3"
						 <c:if test="${questionEn.questionType == 3}">selected="selected"</c:if>>判断题</option>
					 </select>
					   所属课程：<select name="courseId" >
					   <option value="0">-全部-</option>
						<option value="1"
						 <c:if test="${questionEn.courseId == 1}">selected="selected"</c:if>>PHP</option>
						<option value="2"
						  <c:if test="${questionEn.courseId == 2}">selected="selected"</c:if>>java</option>
						<option value="3"
						  <c:if test="${questionEn.courseId == 3}">selected="selected"</c:if>>数字艺术</option>
						<option value="4"
						 <c:if test="${questionEn.courseId == 4}">selected="selected"</c:if>>.net</option>
						<option value="5"
						 <c:if test="${questionEn.courseId ==5}">selected="selected"</c:if>>android</option>
						<option value="6"
						 <c:if test="${questionEn.courseId == 6}">selected="selected"</c:if>>ios</option>
		             </select>
					    难度：<select name="difficulty" >
					    <option value="0">-全部-</option>
					 <option value="1"
					 <c:if test="${questionEn.difficulty == 1}">selected="selected"</c:if>>低</option>
					 <option value="2"
					 <c:if test="${questionEn.difficulty == 2}">selected="selected"</c:if>>中</option>
					 <option value="3"
					 <c:if test="${questionEn.difficulty == 3}">selected="selected"</c:if>>较高</option>
					  <option value="4"
					  <c:if test="${questionEn.difficulty == 4}">selected="selected"</c:if>>高</option>
					 </select>
					    状态：<select name="state">
					    <option value="0">-全部-</option>
					 <option value="1"
					 <c:if test="${questionEn.state == 1}">selected="selected"</c:if>>有效</option>
					 <option value="2"
					 <c:if test="${questionEn.state == 2}">selected="selected"</c:if>>无效</option>
					 </select>
					<button class="btn btn-default">查询</button>
	</form>
	
	<br/>
   <table border="1px" class="table">
   <thead>
		<tr><th>编号</th>
			<th>题目</th>
			<th>题型</th>
			<th>所属课程</th>
			<th>难度</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
	  <c:if test="${empty pageModel.list }">
	  <td colspan = "7" align="center">暂时无数据显示</td>
	  </c:if>
		<c:forEach items="${pageModel.list }" var="question" varStatus="v">
			<tr>
				<th scope="row">${v.count }</th>
				<td>${question.question }</td>
				<td>${question.questionTypeStr}</td>
				<td>${question.courseIdStr}</td>
				<td>${question.difficultyStr}</td>
				<td><c:if test="${question.state == 2 }">
							<font color="red" id="state${question.id }">无效</font>
						</c:if> <c:if test="${question.state != 2 }">
							<font color="green" id="state${question.id }">有效</font>
						</c:if></td>
				<td><a id="link${question.id }"
						href="javascript:updateState('<%=request.getContextPath()%>/QuestionServlet?command=updateState&id=${question.id}&state=${question.state}','${question.id }')">
							<c:if test="${question.state == 1 }">
								无效
							</c:if> <c:if test="${question.state != 1 }">
								有效
							</c:if>
					</a><a href="<%=request.getContextPath()%>/QuestionServlet?command=details&id=${question.id}">详情</a> | <a href = "<%=request.getContextPath() %>/QuestionServlet?command=getMessage&id=${question.id}">修改</a></td>
			</tr>
		</c:forEach>
		<tr>
		<td colspan="12" align="right"><a
					href="<%=request.getContextPath()%>/QuestionServlet?command=listPage&pageNo=${pageModel.first }">首页</a>
					<a
					href="<%=request.getContextPath()%>/QuestionServlet?command=listPage&pageNo=${pageModel.pre }">上一页</a>
					<a
					href="<%=request.getContextPath()%>/QuestionServlet?command=listPage&pageNo=${pageModel.next }">下一页</a>
					<a
					href="<%=request.getContextPath()%>/QuestionServlet?command=listPage&pageNo=${pageModel.last }">尾页</a>
					当前为第${pageModel.pageNo }页,共${pageModel.totalPage }页,共${pageModel.allRecords }条记录。
					</td>
		</tr>
		 
	</tbody>
</table>  
<script> 

var submitForm = function(url) {
	var myForm = document.getElementById('myForm');
	myForm.action = url;
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
 
 var updateState = function(url, id, obj) {
			$.get(url + '&time=' + new Date().getTime(), function(result) {
				if (result == 1) {
					var preShowInfo = $('#state' + id).html();
					if (preShowInfo == '无效') {
						$('#state' + id).html('有效').css('color', 'green');
					} else {
						$('#state' + id).html('无效').css('color', 'red');
					}

					var preLinkInfo = $.trim($('#link' + id).html());
					if (preLinkInfo == '有效') {
						$('#link' + id).html('无效');
					} else {
						$('#link' + id).html('有效');
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
		
		
	function layeload(){
		
		var index = layer.load(1, {
		  shade: [0.3,'#fff'] 
		});
		
	}
</script>
</body>
</html>
