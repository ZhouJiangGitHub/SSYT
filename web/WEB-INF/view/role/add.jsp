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
        <li><a href="#">添加角色页面</a></li>
    </ul>
</div>
<div class="formbody">

    <form action="<%=request.getContextPath()%>/RoleServlet?command=add"
          method="post" onsubmit=" return register();" onreset="clearInfo();">
        <ul class="forminfo">
            <li><label></label><input type="text"
                                      class="dfinput" name="operateUserId" id="operateUserId" style="display:none;"
                                      value="${opread_user_id }"/><span
                    id="password2Info"></span></li>
            <li><label>角色名：</label><input type="text" class="dfinput" name="name" id="name"/><span id="codeInfo"></span>
            </li>
            <li><label>用户名：</label><select name="operateUserId" id="operateUserId">
                <option value="1">admin</option>
                <option value="2">张三</option>
                <option value="3">Runs</option>
            </select></li>
            <li><label>状态：</label><select name="state" id="state">
                <option value="1">启用</option>
                <option value="2">禁用</option>
            </select></li>
            <li><label>备注：</label><input type="text" class="dfinput" name="memo"
                                         id="memo" onblur="validateSlogan();"
                                         onfocus="cleareSloganText();"/><span id="sloganInfo"></span></li>
            <li><label></label><input name="" type="submit" class="btn" value="确认新增"/></li>
        </ul>
    </form>
</div>
<script src="<%=request.getContextPath()%>/static/common/plugin/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/static/common/plugin/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script>
</script>
</body>

</html>


