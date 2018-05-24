<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="<%=request.getContextPath() %>/static/common/plugin/bootstrap-3.3.5/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/static/common/plugin/toastr/toastr.css" rel="stylesheet"
          type="text/css"/>
    <link href="<%=request.getContextPath() %>/static/common/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/common/js/jquery.js"></script>
    <title>无标题文档</title>
</head>
<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">角色信息修改页面</a></li>
    </ul>
</div>
<div>
    <form action="<%=request.getContextPath() %>/QuestionServlet?command=updateMessage&id=${questionEntity.id}"
          method="post">
        <li><label>题　　目：</label><input type="text" class="dfinput" name="question" id="question"
                                       value="${questionEntity.question }"/><span id="codeInfo"></span></li>
        <li><label>附　　件：</label><input type="text" class="dfinput" name="attachment" id="attachment"
                                       value="${questionEntity.attachment }"/><span id="codeInfo"></span></li>
        <li><label>题　　型 ：</label><select name="questionType" id="questionType" value="${questionEntity.questionType }">
            <option value="1">单选题</option>
            <option value="2">多选题</option>
            <option value="3">判断题</option>
        </select></li>
        <!--所属课程：<input type="text"  class="form-control" name="sysCourse$Name" id="sysCourse$Name" /><br> -->
        <li><label>选项　A：</label><input type="text" class="dfinput" name="answerA" id="answerA"
                                       value="${questionEntity.answerA }"/><span id="codeInfo"></span></li>
        <li><label>选项　B：</label><input type="text" class="dfinput" name="answerB" id="answerB"
                                       value="${questionEntity.answerB }"/><span id="codeInfo"></span></li>
        <li><label>选项　C：</label><input type="text" class="dfinput" name="answerC" id="answerC"
                                       value="${questionEntity.answerC }"/><span id="codeInfo"></span></li>
        <li><label>选项　D：</label><input type="text" class="dfinput" name="answerD" id="answerD"
                                       value="${questionEntity.answerD }"/><span id="codeInfo"></span></li>
        <li><label>答　　案：</label><input type="text" class="dfinput" name="answer" id="answer"
                                       value="${questionEntity.answer }"/><span id="codeInfo"></span></li>
        <li><label>难　　度：</label><select name="difficulty" id="difficulty" value="${questionEntity.difficulty }">
            <option value="1">低</option>
            <option value="2">中</option>
            <option value="3">较高</option>
            <option value="4">高</option>
        </select></li>
        <li><label>分　　析：</label><input type="text" class="dfinput" name="analysis" id="analysis"
                                       value="${questionEntity.analysis }"/><span id="codeInfo"></span></li>
        <li><label>关键　词：</label><input type="text" class="dfinput" name="keywords" id="keywords"
                                       value="${questionEntity.keywords }"/><span id="codeInfo"></span></li>
        <li><label>状　　态：</label><select name="state" id="state" value="${questionEntity.state }">
            <option value="1">启用</option>
            <option value="2">禁用</option>
        </select></li>
        <li><label>备　　注：</label><input type="text" class="dfinput" name="memo" id="memo"/><span id="codeInfo"></span>
        </li>
        <input type="submit" class="btn btn-default" value="修改">
    </form>
</div>
</html>

