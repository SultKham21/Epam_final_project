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
    <title><fmt:message key="title.search_restaurants"/></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"
      method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}"/>
    <input type="submit" value="${sessionScope.secondLocale}" class="lang"/>
</form>
<h1><fmt:message key="msg.hello"/> ${sessionScope.authUser.firstName} ${sessionScope.authUser.lastName}</h1>
<h2><fmt:message key="title.search_restaurants"/></h2>
<form action="${pageContext.request.contextPath}/controller?command=search_pharmacies_by_city" method="post">
    <input type="text" name="cityForSearchPharmacies"
           placeholder="<fmt:message key="placeholder.enter_city_for_search_restaurants_here"/>"><br>
    <input type="submit" value="<fmt:message key="button.search"/>" class="button">
</form>

<c:choose>
    <c:when test="${sessionScope.listPharmaciesByCity.size()>0}">

        <table border="3">
            <thead>
            <th><fmt:message key="column.table.number"/></th>
            <th><fmt:message key="column.table.city"/></th>
            <th><fmt:message key="column.table.street"/></th>
            <th><fmt:message key="column.table.house"/></th>
            <th><fmt:message key="column.table.block"/></th>
            </thead>

            <c:forEach items="${sessionScope.listPharmaciesByCity}" var="restaurant">

                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=all_processing_orders&restaurant_id=${restaurant.restaurantId}">${restaurant.number}</a>
                    </td>
                    <td>
                            ${restaurant.city}
                    </td>
                    <td>
                            ${restaurant.street}
                    </td>
                    <td>
                            ${restaurant.house}
                    </td>
                    <td>
                            ${restaurant.block}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>
<a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
        key="link.logout"/></a>
<ftg:footer/>
</body>
</html>
