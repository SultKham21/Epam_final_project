<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftg" uri="/WEB-INF/tld/footer.tld" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title><fmt:message key="title.about_product"/></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"
      method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}"/>
    <input type="submit" value="${sessionScope.secondLocale}" class="lang"/>
</form>

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
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=questions_page"><fmt:message
                    key="link.name.faq"/></a>
        </li>
    </ul>
</nav>

<h1><fmt:message key="title.about_product"/></h1>
<img id="account_image" class="pic"
     src="/addImage?product_id=${sessionScope.productId}" alt="no img"/><br>
<label class="product"><fmt:message key="label.product_name"/></label>
<div>${sessionScope.product.name}</div>
<br>
<br>
<label class="product"><fmt:message key="label.product_waight"/></label>
<div>${sessionScope.product.dose}</div>
<br>
<label class="product"><fmt:message key="label.product_group"/></label>
<div>${sessionScope.product.group}</div>
<br>
<label class="product"><fmt:message key="label.product_price"/></label>
<div>${sessionScope.product.price}</div>
<br>
<br>
<label class="product"><fmt:message key="label.product_instruction"/></label>
<div>${sessionScope.product.instruction}</div>

<a href="${pageContext.request.contextPath}/controller?command=main_customer" class="common_link"><fmt:message
        key="link.customer_main"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
        key="link.logout"/></a>
<ftg:footer/>
</body>
</html>
