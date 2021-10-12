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
    <title>Order</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"
      method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}"/>
    <input type="submit" value="${sessionScope.secondLocale}" class="lang"/>
</form>
<c:choose>
    <c:when test="${sessionScope.basket.size()>0}">

        <div>${sessionScope.basket.get(0).user.firstName} ${sessionScope.basket.get(0).user.lastName}</div>
        <br>
        <div> ${sessionScope.basket.get(0).user.telephone}</div>
        <br>
        <div>${sessionScope.order.orderId}</div>
        <br>
        <div> ${sessionScope.order.dataStarting}</div>
        <br>
        <div>${sessionScope.order.dataEnding}</div>
        <br>
        <div>${sessionScope.order.statusOrder}</div>

        <table border="3">
            <thead>
            <th>Name</th>
            <th>Waight</th>
            <th>Price</th>
            <th>Quantity</th>
            </thead>

            <c:forEach items="${sessionScope.basket}" var="basket">

                <tr>
                    <td>
                            ${basket.product.name}
                    </td>
                    <td>
                            ${basket.product.dose}
                    </td>
                    <td>
                            ${basket.product.price}
                    </td>
                    <td>
                            ${basket.quantity}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose><br>
<form action="${pageContext.request.contextPath}/controller?command=updating_order_status_to_prepared"
      method="post">
    <input type="hidden" name="order_id" value="${sessionScope.order.orderId}"/>
    <input type="hidden" name="order_status_id" value="2"/>
    <input type="submit" value="Prepared" class="button"/>
</form>
<br>
<form action="${pageContext.request.contextPath}/controller?command=updating_order_status_to_deleted"
      method="post">
    <input type="hidden" name="order_id" value="${sessionScope.order.orderId}"/>
    <input type="hidden" name="order_status_id" value="4"/>
    <input type="submit" value="Deleted" class="button"/>
</form>
<br>
<a href="${pageContext.request.contextPath}/controller?command=all_processing_orders&restaurant_id=${sessionScope.order.restaurant.restaurantId}">To
    list of orders</a>
</body>
</html>
