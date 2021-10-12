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
    <title><fmt:message key="title.basket"/></title>
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

<a href="${pageContext.request.contextPath}/controller?command=choose_restaurant"><fmt:message
        key="link.choose_restaurant"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=order"><fmt:message key="link.go_to_order_page"/></a>

<h1><fmt:message key="title.basket"/></h1>

<c:if test="${requestScope.productQuantityError != null}">
    <div class="error"><fmt:message key="error.product_quantity"/></div>
</c:if>

<c:choose>
    <c:when test="${sessionScope.listProductsInBasket.size()>0}">

        <table border="3">
            <thead>
            <th><fmt:message key="column.table_name"/></th>
            <th><fmt:message key="column.table_waight"/></th>
            <th><fmt:message key="column.table_price"/></th>
            <th><fmt:message key="column.table_quantity"/></th>
            </thead>

            <c:forEach items="${sessionScope.listProductsInBasket}" var="product">

            <tr>
                <td>
                        ${product.key.name}
                </td>
                <td>
                        ${product.key.dose}
                </td>
                <td>
                        ${product.key.price}
                </td>
                <td>
                        ${product.value}
                    <form action="${pageContext.request.contextPath}/controller?command=updating_product_quantity&product_id=${product.key.productId}"
                          method="post">
                        <input type="text" name="quantity"
                               placeholder="<fmt:message key="placeholder.update_quantity_here"/>"/>
                    </form>

                </td>
                </c:forEach>
        </table>
    </c:when>
</c:choose>
<a href="${pageContext.request.contextPath}/controller?command=main_customer" class="common_link"><fmt:message
        key="link.customer_main"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
        key="link.logout"/></a>
<ftg:footer/>
</body>
</html>