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
    <title><fmt:message key="title.how_to_do_order"/></title>
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
            <a href="${pageContext.request.contextPath}/controller?command=about_us_page"><fmt:message
                    key="link.name.about_us"/></a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=questions_page"><fmt:message
                    key="link.name.faq"/></a>
        </li>
    </ul>
</nav>
<h1 class="static_header"><fmt:message key="title.how_to_do_order"/></h1>
<div class="static_text"><fmt:message key="div.choose_link"/><fmt:message key="div.enter_name"/>
    <fmt:message key="div.click_on_button"/><fmt:message key="div.products_in_table"/>
    <fmt:message key="div.addition_to_the_basket"/><fmt:message key="div.go_to_basket"/>
    <fmt:message key="div.update_if_needed"/><fmt:message key="div.choose_pharmacy"/>
    <fmt:message key="div.all_pharmacies_for_choice"/><fmt:message key="div.choose_pharmacy_in_the_table"/>
    <fmt:message key="div.order_page"/><fmt:message key="div.check_data"/>
    <fmt:message key="div.sending_order"/><fmt:message key="div.email_about_product"/>
    <fmt:message key="div.questions_from_managers"/></div>
</body>
</html>
