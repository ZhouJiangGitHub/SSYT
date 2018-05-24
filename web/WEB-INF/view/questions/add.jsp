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
    <link rel="stylesheet"
          href="<%=request.getContextPath() %>/static/common/plugin/nice-validator-0.10.5/dist/jquery.validator.css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/common/js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/common/plugin/jquery.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/static/common/plugin/nice-validator-0.10.5/dist/jquery.validator.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/static/common/plugin/nice-validator-0.10.5/dist/local/zh-CN.js"></script>
    <title>无标题文档</title>
</head>
<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">试卷新增页面</a></li>
    </ul>
</div>
<div class="formbody">

    <form action="<%=request.getContextPath() %>/QuestionServlet?command=add"
          method="post" onsubmit=" return register();" onreset="clearInfo();">
        <ul class="forminfo">
            <li><label></label><input type="text"
                                      class="dfinput" name="operateUserId" id="operateUserId" style="display:none;"
                                      value="${opread_user_id }"/><span
                    id="password2Info"></span></li>
            <li><label>题目：</label><input type="text" class="dfinput" name="question" id="question"
                                         onblur="validateusename();"
                                         onfocus="clearusenameText();"/><span id="codeInfo"></span></li>
            <li><label>附件：</label><input type="text" class="dfinput" name="attachment" id="name"
                                         onblur="validateusename();"
                                         onfocus="clearusenameText();"/><span id="codeInfo"></span></li>
            <li><label>题型：</label><select name="question_type">
                <option value="1">单选题</option>
                <option value="2">多选题</option>
                <option value="3">判断题</option>
            </select></li>
            <li><label>所属课程：</label><select name="courseId">
                <option value="1">ios</option>
                <option value="2">.net</option>
                <option value="3">java</option>
                <option value="4">php</option>
                <option value="5">数字艺术</option>
            </select></li>
            <li><label>选项A：</label><input type="text"
                                          class="dfinput" name="answer_a" id="state"
                                          onblur="validatecompanyname();"
                                          onfocus="companynameText();"/><span id="companynameInfo"></span></li>
            <li><label>选项B：</label><input type="text" class="dfinput" name="answer_b"
                                          id="memo" onblur="validateSlogan();"
                                          onfocus="cleareSloganText();"/><span id="sloganInfo"></span></li>
            <li><label>选项C：</label><input type="text" class="dfinput" name="answer_c"
                                          id="memo" onblur="validateSlogan();"
                                          onfocus="cleareSloganText();"/><span id="sloganInfo"></span></li>
            <li><label>选项D：</label><input type="text" class="dfinput" name="answer_d"
                                          id="memo" onblur="validateSlogan();"
                                          onfocus="cleareSloganText();"/><span id="sloganInfo"></span></li>
            <li><label>答案：</label><input type="text" class="dfinput" name="answer"
                                         id="memo" onblur="validateSlogan();"
                                         onfocus="cleareSloganText();"/><span id="sloganInfo"></span></li>
            <li><label>难度：</label><input type="text" class="dfinput" name="difficulty"
                                         id="memo" onblur="validateSlogan();"
                                         onfocus="cleareSloganText();"/><span id="sloganInfo"></span></li>
            <li><label>分析：</label><input type="text" class="dfinput" name="analysis"
                                         id="memo" onblur="validateSlogan();"
                                         onfocus="cleareSloganText();"/><span id="sloganInfo"></span></li>
            <li><label>关键词：</label><input type="text" class="dfinput" name="keywords"
                                          id="keywords" onblur="validateSlogan();"
                                          onfocus="cleareSloganText();"/><span id="sloganInfo"></span></li>
            <li><label>备注：</label><input type="text" class="dfinput" name="memo"
                                         id="memo" onblur="validateSlogan();"
                                         onfocus="cleareSloganText();"/><span id="sloganInfo"></span></li>
            <li><label></label><input name="submit" type="submit" class="btn" value="确认添加"/></li>
        </ul>
    </form>
</div>
<script src="<%=request.getContextPath()%>/static/common/plugin/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/static/common/plugin/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/static/common/js/addUser.js"/>
</body>

</html>


