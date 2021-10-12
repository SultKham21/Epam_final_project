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
    <title><fmt:message key="title.about_product"/></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"
      method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}"/>
    <input type="submit" value="${sessionScope.secondLocale}" class="lang"/>
</form>

<h1><fmt:message key="title.about_product"/></h1>
<img id="account_image" class="pic"
     src="/addImage?product_id=${sessionScope.productId}" alt="no img"/><br>
<label class="product"><fmt:message key="label.product_name"/></label>
<div>${sessionScope.product.name}</div>
<br>
<label class="product"><fmt:message key="label.product_non_proprietary_name"/></label>
<div>${sessionScope.product.nonProprietaryName}</div>
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
<label class="product"><fmt:message key="label.product_instruction"/></label>
<div>${sessionScope.product.instruction}</div>
<a href="${pageContext.request.contextPath}/controller?command=all_products" class="common_link"><fmt:message
        key="link.all_products"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=main_admin" class="common_link"><fmt:message
        key="link.admin_main"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
        key="link.logout"/></a>
<ftg:footer/>
</body>
</html>
