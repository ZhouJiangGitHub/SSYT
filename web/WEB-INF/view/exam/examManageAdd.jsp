<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
        <li><a href="#">添加考试安排页面</a></li>
    </ul>
</div>
<c:if test="${!empty errorInfo }">
    <div class="alert alert-danger alert-dismissible fade in" role="alert">
        <button type="button" class="close" data-dismiss="alert"
                aria-label="Close">
            <span aria-hidden="true">×</span>
        </button>
        <strong>${errorInfo }</strong>
    </div>
</c:if>
<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form
            action="<%=request.getContextPath()%>/ExamManageServlet?command=addExamManage"
            method="post" onreset="clearInfo();">
        <ul class="forminfo">
            <li><label>考试时间段：</label><input type="text" class="dfinput" name="starttime" id="starttime"
                                            onblur="validatestarttime();" placeholder="请您输开始时间"
                                            onfocus="clearusestarttimeText();"/>-<input type="text" class="dfinput"
                                                                                        name="endtime" id="" endtime""
                onblur="validateEndtime();" placeholder="请您输截止时间"
                onfocus="clearuseEndtimeText();" /><span id="dateInfo"></span></li>
            <li><label>考试教室：</label><input type="text" class="dfinput" name="classroom"
                                           id="classroom" onblur="validateclassroom();" placeholder="请您输入教室名"/><span
                    id="classroomInfo"></span></li>
            <!--  <li><label>试卷名称：</label><input type="text"
                             class="dfinput" name="paperName" id="paperName"
                             onblur="validatepaperName();" placeholder="请您输入试卷名称"/><span
                             id="paperNameInfo"></span></li> -->
            <li><label>试卷名称：</label><select name="paperName" class="form-control" style="width:120px ; display: inline">
                <c:forEach items="${ExaminationPaperEntity }" var="ExaminationPaperEntity">
                    <option value="${ExaminationPaperEntity.id }"
                            <c:if test="">selected=selected</c:if>>${ExaminationPaperEntity.name }</option>
                </c:forEach>
            </select>
            </li>
            <li><label>接收人员：</label><input type="text"
                                           class="dfinput" name="recetpeople" id="recetpeople"
                                           onblur="validateRecetpeople();" placeholder="请您输接受人员入名字"
                                           onfocus="RecetpeopleText();"/><span id="RecetpeopleInfo"></span></li>
            <!-- <li><label>接收班级：</label><input type="text" class="dfinput" id="classs" name="classs"
                            onblur="validateClasss();" placeholder="请您输入接受班级" onfocus="clearClasssText();" />
                             <span id="classsInfo" name="span"></span></li> -->
            <li><label>班 级：</label><select name="classid" class="form-control" style="width:120px ; display: inline">
                <c:forEach items="${classNameList }" var="className">
                    <option value="${className.id }" <c:if test="">selected=selected</c:if>>${className.name }</option>
                </c:forEach>
            </select>
            </li>
            <li><label>操作者：</label><input type="text" class="dfinput" name="operate_user" id="operate_user"
                                          onblur="validateOperate_user();" value="${opreadname }"
                                          onfocus="clearOperate_userText();"/><span id="operate_userInfo"></span></li>
            <li><label>状态：</label><select name="state" class="form-control" style="width:100px ; display: inline">
                <option value="1" <c:if test="">selected=selected</c:if>>有效</option>
                <option value="2" <c:if test="">selected=selected</c:if>>无效</option>
            </select>
            </li>
            <li><label>描述：</label><input type="text" class="dfinput" name="memo"
                                         id="memo" onblur="validateMemo();" placeholder="请您输入个人描述"
                                         onfocus="cleareMemoText();"/><span id="memoInfo"></span></li>
            <li><label>验证码：</label><input type="text" class="dfinput" id="vrafyPassworf" name="vrafyPassworf"
                                          placeholder="请输验证码" style="width:10%; height:50px; display: inline;"
                                          maxlength="4"/>
                <img src="<%=request.getContextPath() %>/kaptcha.jpg" width="120px" height="50px" style="cursor:pointer"
                     ; id="pic" onclick="changePic()"/></li>
            <li><label></label>
                <button type="submit" class="btn btn-primary">确认添加</button>
            </li>
        </ul>
    </form>
</div>
<script src="<%=request.getContextPath()%>/static/common/plugin/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/static/common/plugin/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/static/common/js/addUser.js"/>
</script>
<
script >
var changePic = function () {
    document.getElementById('pic').src = '<%=request.getContextPath() %>/kaptcha.jpg?time=' + new Date().getTime();
}
</script>
</body>

</html>


