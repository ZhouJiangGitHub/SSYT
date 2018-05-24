<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    if (request.getSession().getAttribute("user") == null) {
        response.sendRedirect("LoginServlet?command=loginFailure");
    }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>信息管理系统界面</title>
</head>

<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="<%=request.getContextPath() %>/ApplicationServlet?command=top" name="topFrame" scrolling="no"
           noresize="noresize" id="topFrame" title="topFrame"/>
    <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
        <frame src="<%=request.getContextPath() %>/ApplicationServlet?command=initMenu" name="leftFrame" scrolling="no"
               noresize="noresize" id="leftFrame" title="leftFrame"/>
        <frame src="<%=request.getContextPath() %>/ApplicationServlet?command=index" name="rightFrame" id="rightFrame"
               title="rightFrame"/>
    </frameset>
</frameset>
<noframes>
    <body>
    </body>
</noframes>
</html> 
