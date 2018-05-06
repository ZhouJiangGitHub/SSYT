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
     <form id="myForm" action="<%=request.getContextPath()%>/ExamServlet?command=List" method="post">
         学员名:<input type="text" name="name"  value="${exam.name }" class="form-control"  style="width:100px ;display: inline"/>
         <button class="btn btn-default">查询</button>
     </form>
        </li>
         </ul>   
        <ul class="toolbar1">
        <li class="click"><a href="<%=request.getContextPath() %>/ExamServlet?command=examAddPage" target="rightFrame"><span><img src="<%=request.getContextPath() %>/static/common/images/t01.png" /></span>添加考试记录</a></li>
        </ul>  
   </div>	
   <form action="ExamServlet?command=List" method="post">
     <table  width="100%" boeder="1">
       <tr>
         <th>编号</th>
         <th>学员名称</th> 
          <th>班级名称</th> 
         <th>试卷名称</th>
         <th>开始时间</th>
         <th>提交时间</th>
          <th>总分数</th>
          <th>得分数</th>
         <th>是否通过</th>
        </tr>
             
            <c:forEach items="${pageModel.list }" var="exam" varStatus="v">
	        <tr>
	        <th>${ (pageModel.pageNo-1)*pageModel.pageSize+v.count }</th>
	           <td>${exam.name }</td>   
	           <td>${exam.sys_class_name }</td>
               <td>${exam.qes_exam_paper_name}</td> 
	           <td>${exam.start_time }</td>
	           <td>${exam.submit_time }</td>
	           <td>${exam.total_point}</td> 
	           <td>${exam.get_point }</td> 
	           
	        
	          <td>
	          	<c:if test="${exam.is_pass == 1}">通过</c:if>
	          	<c:if test="${exam.is_pass == 2}">未通过</c:if>
	          	<c:if test="${exam.is_pass == 3}">未提交或超时</c:if>
	          </td>
	        </tr>
	         </c:forEach>
	         
	         <tr>
				<td colspan="9" align="right"><a
					href="javascript:submitForm('<%=request.getContextPath()%>/ExamServlet?command=List&pageNo=${pageModel.first}')">首页</a>　
					<a
					href="javascript:submitForm('<%=request.getContextPath()%>/ExamServlet?command=List&pageNo=${pageModel.pre}')">上一页</a>　
					<a
					href="javascript:submitForm('<%=request.getContextPath()%>/ExamServlet?command=List&pageNo=${pageModel.next}')">下一页</a>　
					<a
					href="javascript:submitForm('<%=request.getContextPath()%>/ExamServlet?command=List&pageNo=${pageModel.last}')">尾页</a>　
					当前为第${pageModel.pageNo }页,共${pageModel.allRecords }条记录。</td>
			</tr>
		 
    </table>
    <script>

	var submitForm = function(url) {
		var myForm = document.getElementById('myForm');
		myForm.action = url;
		myForm.submit();
	}
    
    </script>
    </form>
</body>


</html>