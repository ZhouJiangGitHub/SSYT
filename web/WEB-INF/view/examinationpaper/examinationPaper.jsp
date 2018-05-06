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
<title>试卷管理</title>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">试卷管理页面</a></li>
    </ul>
    </div>
    <div class="tools">  
     <ul class="toolbar">
        <li>
         <form id="myForm"
		  action="<%=request.getContextPath()%>/ExaminationPaperServlet?command=list"
		  method="post">
		  试卷名称：<input type="text" name="name" value="${examinationPaperEntity.name }" />
		   状态：<select name="state">
			<option value="1"
				<c:if test="${examinationPaperEntity.state == 1}">selected="selected"</c:if>>有效</option>
			<option value="2"
				<c:if test="${examinationPaperEntity.state == 2}">selected="selected"</c:if>>无效</option>
		    </select> <input type="submit" class="btn btn-default" value="查询" />
	      </form>
        </li>
        </ul>   
           
    </div>	
   <table border="1px" class="table">
   <tr>
			<th>序号</th>			
			<th>总分分数</th>
			<th>通过分数</th>
			<th>总时间（分）</th>
			<th>操作</th>
		</tr>
		<c:if test="${empty pageModel.list }">
			<td colspan="6" align="center">暂无显示数据！</td>
		</c:if>
		<c:if test="${!empty pageModel.list }">
			<c:forEach items="${pageModel.list }" var="examinationPaperEntity" varStatus="v">
				<tr>
					<td>${v.count }</td>
					<td>${examinationPaperEntity.totalPoint }</td>
					<td>${examinationPaperEntity.passPoint }</td>
					<td>${examinationPaperEntity.totalMinutes }</td>
					<td>详情</td>					
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6" align="right"><a
					href="javascript:submitForm('<%=request.getContextPath()%>/ExaminationPaperServlet?command=list&pageNo=${pageModel.first}')">首页</a>
					<c:if test="${pageModel.pageNo > 1}">
					<a href="javascript:submitForm('<%=request.getContextPath()%>/ExaminationPaperServlet?command=list&pageNo=${pageModel.pre}')">上一页</a>
					</c:if>
					第<select onchange="goPage(this)">
				 	<script>
				 		var pageNo = '${pageModel.pageNo }';
				 		var totalPage = '${pageModel.totalPage }';
				 		for (var i = 1; i <= totalPage; i++) {
				 			if (pageNo == i) {
				 				document.write('<option value="' + i + '" selected="selected">' + i + '</option>');
				 			} else {
				 				document.write('<option value="' + i + '">' + i + '</option>');
				 			}
				 		}
				 	</script>
				 </select>页
				,共${pageModel.totalPage }页&nbsp;
				    <c:if test="${pageModel.pageNo <  pageModel.totalPage }">
					<a href="javascript:submitForm('<%=request.getContextPath()%>/ExaminationPaperServlet?command=list&pageNo=${pageModel.next}')">下一页</a>
					</c:if>
					<a href="javascript:submitForm('<%=request.getContextPath()%>/ExaminationPaperServlet?command=list&pageNo=${pageModel.last}')">尾页</a>
					当前为第${pageModel.pageNo }页,共${pageModel.allRecords }条记录。</td>
			</tr>
		</c:if>
		</table>  
</body>


</html>
<script>
   /**
	 * 跳转到指定的页数
	 */
	function goPage(obj) {
		submitForm('<%=request.getContextPath()%>/SysServlet?command=listManageVerifyByPage&pageNo=' + obj.value);
	}
	
	/**
	 * 分页时，使用JS使用表单
	 */
	function submitForm(url) {
		var conditionForm = document.getElementById('conditionForm');
		conditionForm.action = url;
		conditionForm.submit();
	}
</script>


