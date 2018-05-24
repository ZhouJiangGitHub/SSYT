<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        ul {
            list-style: none;
        }
    </style>
</head>
<body>
<span><font color="red" size="-1">欢迎[${sessionScope.code }]进入该网站</font></span>
<br/>
<ul>
    <c:forEach items="${mainMenus }" var="mainMenu">
        <li>${mainMenu.code }
            <ul>
                <c:forEach items="${childMenus }" var="childMenu">
                    <c:if test="${childMenu.parentId == mainMenu.rightId}">
                        <li>${childMenu.code }</li>
                    </c:if>
                </c:forEach>
            </ul>
        </li>
    </c:forEach>
</ul>
</body>
</html>