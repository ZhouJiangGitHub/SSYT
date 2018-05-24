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
        <li><a href="#">考试管理页面</a></li>
    </ul>
</div>
<div class="tools">
    <ul class="toolbar">
        <li>
            <form id="conditionForm"
                  action="<%=request.getContextPath() %>/ExamManageServlet?command=listExamManageVerifyByPage"
                  method="post">
                类型：<select name="mold" class="form-control" style="width:100px ; display: inline">
                <option value="">-全部-</option>
                <option value="1" <c:if test="${findSys.exam_paper_id == 1 }">selected=selected</c:if>>平时测试</option>
                <option value="2" <c:if test="${findSys.exam_paper_id == 2 }">selected=selected</c:if>>结业考试</option>
            </select>
                状态：<select name="state" class="form-control" style="width:100px ; display: inline">
                <option value="">-全部-</option>
                <option value="1" <c:if test="${findSys.state == 1 }">selected=selected</c:if>>生效</option>
                <option value="2" <c:if test="${findSys.state == 2 }">selected=selected</c:if>>待生效</option>
                <option value="3" <c:if test="${findSys.state == 3}">selected=selected</c:if>>已删除</option>
            </select>
                是否过期：<select name="date" class="form-control" style="width:100px ; display: inline">
                <option value="">-全部-</option>
                　　　
                <option value="${time }" <c:if test="${findSys.exam_time_start }">selected=selected</c:if>>过期</option>
                <option value="${time }" <c:if test="${findSys.exam_time_start}">selected=selected</c:if>>未过期</option>
            </select>

                <button class="btn btn-default">查询</button>
            </form>
        </li>
    </ul>
    <ul class="toolbar1">
        <li class="click"><a href="<%=request.getContextPath() %>/ExamManageServlet?command=addExamManagePage"
                             target="rightFrame"><span><img
                src="<%=request.getContextPath() %>/static/common/images/t01.png"/></span>添加考试安排</a></li>
    </ul>
</div>
<table border="1px" class="table">
    <thead>
    <tr>
        <th>编号</th>
        <th>考试时间段</th>
        <th>考试教室</th>
        <th>试卷名称</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pageModel.list }" var="MangeMessage" varStatus="v">
        <tr>
            <td>${( pageModel.pageNo - 1 )* pageModel.pageSize + v.count }</td>
            <td>
                <fmt:formatDate value="${MangeMessage.exam_time_start }" pattern="yyyy-MM-dd HH:mm:ss"/> -
                <fmt:formatDate value="${MangeMessage.exam_time_stop }" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>${MangeMessage.exam_classroom}</td>
            <td>${MangeMessage.paperName}</td>
            <td>
                <c:if test="${MangeMessage.state == 1 }">
                    ${MangeMessage.states }
                </c:if>
                <c:if test="${MangeMessage.state == 2 }">
                    ${MangeMessage.states }
                </c:if>
                <c:if test="${MangeMessage.state == 3 }">
                    ${MangeMessage.states }
                </c:if>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/ExamManageServlet?command=getDetailMessege&id=${MangeMessage.id}">详情</a>
                    <%-- <a href="<%=request.getContextPath()%>/ExamManageServlet?command=getMessege&id=${MangeMessage.id}">修改</a> --%>
                <c:if test="${MangeMessage.state == 1}">
                    <a href="javascript:deleteMnage(${MangeMessage.state }, ${MangeMessage.id });"
                       onclick="return confirm('考试安排已执行，是否确定删除？');">删除</a>
                </c:if>
                <c:if test="${MangeMessage.state == 2}">
                    <a href="javascript:verify(${MangeMessage.state }, ${MangeMessage.id });">${MangeMessage.statess}</a>
                    <a href="javascript:deleteMnage(${MangeMessage.state }, ${MangeMessage.id });"
                       onclick="return confirm('考试安排已执行，是否确定删除？');">删除</a>
                </c:if>
                <c:if test="${MangeMessage.state == 3}">
                </c:if>
            </td>

        </tr>
    </c:forEach>
    <tr>
        <td colspan="12" align="right">
            <a href="<%=request.getContextPath()%>/ExamManageServlet?command=listExamManageByPage&pageNo=${pageModel.first}">首页</a>&nbsp;
            <c:if test="${pageModel.pageNo > 1}">
                <a href="<%=request.getContextPath()%>/ExamManageServlet?command=listExamManageByPage&pageNo=${pageModel.pre}">上一页</a>&nbsp;
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
                <a href="<%=request.getContextPath()%>/ExamManageServlet?command=listExamManageByPage&pageNo=${pageModel.next}">下一页</a>&nbsp;
            </c:if>
            <a href="<%=request.getContextPath()%>/ExamManageServlet?command=listExamManageByPage&pageNo=${pageModel.last}">尾页</a>&nbsp;
            共${pageModel.allRecords }条记录
        </td>
    </tr>
    </tbody>
</table>
</body>


</html>
<script>
    /**
     * 跳转到指定的页数
     */
    function goPage(obj) {
        submitForm('<%=request.getContextPath()%>/ExamManageServlet?command=listExamManageByPage&pageNo=' + obj.value);
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
<script>
    /**
     * change
     */
    function verify(state, id) {
        if (state == 1) {
            state = 2;
        } else {
            state = 1;
        }
        location = '<%=request.getContextPath()%>/ExamManageServlet?command=change&status=' + state + '&Id=' + id;
    }
</script>
<script>
    /**
     * change
     */
    function deleteMnage(state, id) {
        if (state != 3) {
            state = 3;
        }
        location = '<%=request.getContextPath()%>/ExamManageServlet?command=deleteMnage&status=' + state + '&Id=' + id;
    }
</script>
<script>
    var tr = $('tr:gt(0)');
    tr.click(function () {
        var preBackgroundColor = $(this).css('background-color');
        if (preBackgroundColor == 'rgb(18, 52, 86)') {
            $(this).css('background', 'gray');
        } else {
            $(this).css('background', 'rgba(0, 0, 0, 0)');
        }
    }).mouseover(function () {
        var preBackgroundColor = $(this).css('background-color');
        if (preBackgroundColor != 'rgb(128, 128, 128)') {
            $(this).css('background', '#C0D9D9 48')
        }
    }).mouseout(function () {
        var preBackgroundColor = $(this).css('background-color');
        if (preBackgroundColor != 'rgb(128, 128, 128)') {
            $(this).css('background', 'white')
        }
    })

    function disptime() {
        var time = new Date(); //获得当前时间
    }
</script>

