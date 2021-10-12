<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftg" uri="/WEB-INF/tld/footer.tld" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title><fmt:message key="title.admin_main"/></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"
      method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}"/>
    <input type="submit" value="${sessionScope.secondLocale}" class="lang"/>
</form>

<h1><fmt:message key="msg.hello"/> ${sessionScope.authUser.firstName} ${sessionScope.authUser.lastName}</h1>
<ul>
    <li>
        <a href="${pageContext.request.contextPath}/controller?command=all_managers" class="common_link"><fmt:message
                key="link.all_managers"/></a><br>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/controller?command=all_pharmacies" class="common_link"><fmt:message
                key="header.all_restaurants"/></a><br>
    </li>
    <li>
    <a href="${pageContext.request.contextPath}/controller?command=all_products" class="common_link"><fmt:message
            key="link.all_products"/></a><br>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/controller?command=inactive_managers_page"
           class="common_link"><fmt:message key="link.all_inactive_managers"/></a><br>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
                key="link.logout"/></a>
    </li>
</ul>
<ftg:footer/>
</body>
</html>
