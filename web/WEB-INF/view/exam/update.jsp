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
        <li><a href="#">考试安排信息修改页面</a></li>
    </ul>
</div>
<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form action="<%=request.getContextPath() %>/ExamManageServlet?command=changManageMessege&Id=${examDetailEntity.id}"
          method="post">
        <ul class="forminfo">
            <li><label>考试时间段：</label><input type="text" class="dfinput" name="starttime" id="starttime"
                                            onblur="validatestarttime();" value="${examDetailEntity.exam_time_start }"
                                            onfocus="clearusestarttimeText();"/>-<input type="text" class="dfinput"
                                                                                        name="endtime" id="" endtime""
                onblur="validateEndtime();" value="${examDetailEntity.exam_time_stop }"
                onfocus="clearuseEndtimeText();" /><span id="dateInfo"></span></li>
            <li><label>考试教室：</label><input type="text" class="dfinput" name="classroom"
                                           id="classroom" onblur="validateclassroom();"
                                           value="${examDetailEntity.exam_classroom }"/><span
                    id="classroomInfo"></span></li>
            <%-- <li><label>试卷名称：</label><input type="text"
                            class="dfinput" name="paperName" id="paperName"
                            onblur="validatepaperName();" value="${examDetailEntity.exam_paper_id }"/><span
                            id="paperNameInfo"></span></li> --%>
            <li><label>试卷名称：</label><select name="paperName" class="form-control" style="width:120px ; display: inline">
                <c:forEach items="${ExaminationPaperEntity }" var="ExaminationPaperEntity">
                    <option value="${ExaminationPaperEntity.id }"
                            <c:if test="">selected=selected</c:if>>${ExaminationPaperEntity.name }</option>
                </c:forEach>
            </select>
            </li>
            <li><label>接收人员：</label><input type="text"
                                           class="dfinput" name="recetpeople" id="recetpeople"
                                           onblur="validateRecetpeople();" value="${examDetailEntity.operate_user_id }"
                                           onfocus="RecetpeopleText();"/><span id="RecetpeopleInfo"></span></li>
            <%-- <li><label>接收班级：</label><input type="text" class="dfinput" id="classs" name="classs"
                            onblur="validateClasss();" value="${examDetailEntity.to_class_id }" onfocus="clearClasssText();" />
                             <span id="classsInfo" name="span"></span></li> --%>
            <li><label>班 级：</label><select name="classid" class="form-control" style="width:120px ; display: inline">
                <c:forEach items="${classNameList }" var="className">
                    <option value="${className.id }" <c:if test="">selected=selected</c:if>>${className.name }</option>
                </c:forEach>
            </select>
            </li>
            <li><label>操作者：</label><input type="text" class="dfinput" name="operate_user" id="operate_user"
                                          onblur="validateOperate_user();" value="${examDetailEntity.operate_user_id }"
                                          onfocus="clearOperate_userText();"/><span id="operate_userInfo"></span></li>
            <%--  <li><label>状态：</label><input type="text" class="dfinput" name="state"
                             id="state" onblur="validateState();" value="${examDetailEntity.state }"
                             onfocus="cleareStateText();" /><span id="stateInfo"></span></li> --%>
            <li><label>状态：</label><select name="state" class="form-control" style="width:100px ; display: inline">
                <option value="1" <c:if test="${examDetailEntity.state ==1 }">selected=selected</c:if>>有效</option>
                <option value="2" <c:if test="${examDetailEntity.state ==2  }">selected=selected</c:if>>无效</option>
                <option value="3" <c:if test="${examDetailEntity.state ==3 }">selected=selected</c:if>>已删除</option>
            </select>
            </li>
            <li><label>描述：</label><input type="text" class="dfinput" name="memo"
                                         id="memo" onblur="validateMemo();" value="${examDetailEntity.memo}"
                                         onfocus="cleareMemoText();"/><span id="memoInfo"></span></li>
            <li><label>验证码：</label><input type="text" class="dfinput" id="vrafyPassworf" name="vrafyPassworf"
                                          placeholder="请输验证码" style="width:10%; height:50px; display: inline;"
                                          maxlength="4"/>
                <img src="<%=request.getContextPath() %>/kaptcha.jpg" width="120px" height="50px" style="cursor:pointer"
                     ; id="pic" onclick="changePic()"/></li>
            <li><label></label>
                <button type="submit" class="btn btn-primary">确认修改</button>
            </li>
        </ul>
    </form>
</div>
</html>


