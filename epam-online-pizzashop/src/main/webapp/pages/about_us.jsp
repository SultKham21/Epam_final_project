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
    <link rel="stylesheet" href="/styles/reg.css">
    <link rel="stylesheet" href="/styles/common_menu.css">
    <title><fmt:message key="title.about_us"/></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"
      method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}">
    <input type="submit" class="lang"
           value="${sessionScope.secondLocale}">
</form>

<c:if test="${sessionScope.authUser == null}">
    <a href="${pageContext.request.contextPath}/controller?command=login_page"><fmt:message
            key="link.name.back_to_login_page"/></a>
</c:if>

<c:if test="${sessionScope.authUser != null}">
    <a href="${pageContext.request.contextPath}/controller?command=main_customer"><fmt:message
            key="link.customer_main"/></a><br>
</c:if>

<nav class="menu">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=how_to_do_order_page"><fmt:message
                    key="link.name.how_to_do_order"/></a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=questions_page"><fmt:message
                    key="link.name.faq"/></a>
        </li>
    </ul>
</nav>
<h1 class="static_header"><fmt:message key="title.about_us"/></h1>
<div class="static_text"><fmt:message key="div.history"/><fmt:message key="div.pharmacies_quantity"/>
    <fmt:message key="div.assortment"/><fmt:message key="div.our_clients"/>
    <fmt:message key="div.our_employees"/><fmt:message key="div.our_studies"/>
    <ul>
        <li>
            <fmt:message key="div.our_knowledge"/>
        </li>
        <li>
            <fmt:message key="div.trainings"/>
        </li>
        <li>
            <fmt:message key="div.seminars"/>
        </li>
        <li>
            <fmt:message key="div.courses"/>
        </li>
        <li>
            <fmt:message key="div.sessions"/>
        </li>
    </ul>
</div>
</body>
</html>
