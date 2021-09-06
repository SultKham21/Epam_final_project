<%@page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:setBundle basename="properties.pagecontent" />


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/main.css">
    <link rel="stylesheet" href="styles/common_menu.css">
    <title><fmt:message key="company.name"/></title>
</head>

<body>

<nav class="menu">
    <ul>
        <li>
            <a href="#"><fmt:message key="link.name.how_to_do_order"/></a>
        </li>
        <li>
            <a href="#"><fmt:message key="link.name.about_us"/></a>
        </li>
        <li>
            <a href="#"><fmt:message key="link.name.questions"/></a>
        </li>
    </ul>
</nav>
<h2><fmt:message key="page.name.auth"/></h2>
<div class="auth">
    <form action="${pageContext.request.contextPath}/controller?command=login" method="post">
        <p>
        <div class="error">${sessionScope.loginError}</div>
        </p>
        <input type="text" name="login" placeholder="<fmt:message key="placeholder.name.login"/>"/><br>
        <input type="password" name="password" placeholder="<fmt:message key="placeholder.name.password"/>"/><br>
        <input type="submit" value="<fmt:message key="button.name.enter"/>"/><br>
    </form>
    <a href="${pageContext.request.contextPath}/controller?command=registration_page"><fmt:message key="page.registration"/></a><br>
    <a href="${pageContext.request.contextPath}/controller?command=verification_customer_page"><fmt:message key="link.name.verification_customer"/></a><br>
</div>
<div class="titles">
    <div class="title_first">
        <fmt:message key="upper.title.page.login"/>
    </div>
    <h1>
        <fmt:message key="company.name"/>
    </h1>
</div>
</body>
</html>

