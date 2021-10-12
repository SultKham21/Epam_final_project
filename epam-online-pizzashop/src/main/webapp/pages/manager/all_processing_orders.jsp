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
    <title>All processing orders</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"
      method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}"/>
    <input type="submit" value="${sessionScope.secondLocale}" class="lang"/>
</form>
<c:choose>
    <c:when test="${sessionScope.listProcessingOrders.size()>0}">

        <table border="3">
            <thead>
            <th>Order id</th>
            <th>Start</th>
            <th>End</th>
            <th>Restaurant</th>
            <th>Status</th>
            </thead>

            <c:forEach items="${sessionScope.listProcessingOrders}" var="order">

                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=basket_for_order&order_id=${order.orderId}">${order.orderId}</a>
                    </td>
                    <td>
                            ${order.dataStarting}
                    </td>
                    <td>
                            ${order.dataEnding}
                    </td>
                    <td>
                            ${order.restaurant.number}
                    </td>
                    <td>
                            ${order.statusOrder}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>
</body>
</html>
