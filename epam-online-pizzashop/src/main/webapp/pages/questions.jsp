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
    <title><fmt:message key="title.faq"/></title>
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
            <a href="${pageContext.request.contextPath}/controller?command=about_us_page"><fmt:message
                    key="link.name.about_us"/></a>
        </li>
    </ul>
</nav>
<h1 class="static_header"><fmt:message key="title.faq"/></h1>
<div class="static_text">
    <ol>
        <li>
            <fmt:message key="div.order_without_registration"/><br>
            <fmt:message key="div.answer_order_without_registration"/>
        </li>
        <br>
        <li>
            <fmt:message key="div.delete_order"/><br>
            <fmt:message key="div.answer_delete_order"/>
        </li>
        <br>
        <li>
            <fmt:message key="div.delivery_to_house"/><br>
            <fmt:message key="div.answer_delivery_to_house"/>
        </li>
        <br>
        <li>
            <fmt:message key="div.time_saving_order"/><br>
            <fmt:message key="div.answer_time_saving_order"/>
        </li>
        <br>
        <li>
            <fmt:message key="div.discount"/><br>
            <fmt:message key="div.answer_discount"/>
        </li>
        <br>
    </ol>
</div>
</body>
</html>
