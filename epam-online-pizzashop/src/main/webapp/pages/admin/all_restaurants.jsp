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
<%--    <link rel="stylesheet" href="/styles/common.css">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <title><fmt:message key="header.all_restaurants"/></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"
      method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}"/>
    <input type="submit" value="${sessionScope.secondLocale}" class="lang"/>
</form>

<h1><fmt:message key="header.all_restaurants"/></h1>
<h2><fmt:message key="header.add_restaurant"/></h2>
<form action="${pageContext.request.contextPath}/controller?command=addition_restaurant" method="post">

    <c:if test="${requestScope.numberError!=null}">
        <div class="error"><fmt:message key="error.restaurant_number"/></div>
    </c:if>
    <c:if test="${requestScope.stringParametersError!=null}">
        <div class="error"><fmt:message key="error.restaurant_string_parameter_error"/></div>
    </c:if>
    <c:if test="${requestScope.blockError!=null}">
        <div class="error"><fmt:message key="error.restaurant_block_error"/></div>
    </c:if>

    <label class="field"><fmt:message key="label.number"/></label><br>
    <input type="text" name="number" value="${requestScope.mapData.get("number")}"
           placeholder="<fmt:message key="placeholder.number"/>" size="35px"/><br>
    <label class="field"><fmt:message key="label.city"/></label><br>
    <input type="text" name="city" value="${requestScope.mapData.get("city")}"
           placeholder="<fmt:message key="placeholder.city"/>" size="35px"/><br>
    <label class="field"><fmt:message key="label.street"/></label><br>
    <input type="text" name="street" value="${requestScope.mapData.get("street")}"
           placeholder="<fmt:message key="placeholder.street"/>" size="35px"/><br>
    <label class="field"><fmt:message key="label.house"/></label><br>
    <input type="text" name="house" value="${requestScope.mapData.get("house")}"
           placeholder="<fmt:message key="placeholder.house"/>" size="35px"/><br>
    <label class="field"><fmt:message key="label.block"/></label><br>
    <input type="text" name="block" value="${requestScope.mapData.get("block")}"
           placeholder="<fmt:message key="placeholder.block"/>" size="35px"/><br>
    <input type="submit" value="<fmt:message key="button.input_restaurant"/>" class="button">
</form>

<c:choose>
    <c:when test="${sessionScope.currentPharmacies.size()>0}">
        <label><fmt:message key="msg.click_on_parameter"/></label>
        <table class="table">
            <thead class="thead-inverse">
            <th><fmt:message key="column.table.id"/></th>
            <th><fmt:message key="column.table.number"/></th>
            <th><fmt:message key="column.table.city"/></th>
            <th><fmt:message key="column.table.street"/></th>
            <th><fmt:message key="column.table.house"/></th>
            <th><fmt:message key="column.table.block"/></th>
            </thead>

            <c:forEach items="${sessionScope.currentPharmacies}" var="restaurant">
            <tbody>
                <tr>
                    <th scope="row">
                            ${restaurant.restaurantId}
                    </th>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=updating_restaurant_number_page&restaurant_id=${restaurant.restaurantId}&currentPage=${requestScope.currentPage}">${restaurant.number}</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=updating_restaurant_city_page&restaurant_id=${restaurant.restaurantId}">${restaurant.city}</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=updating_restaurant_street_page&restaurant_id=${restaurant.restaurantId}">${restaurant.street}</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=updating_restaurant_house_page&restaurant_id=${restaurant.restaurantId}">${restaurant.house}</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=updating_restaurant_block_page&restaurant_id=${restaurant.restaurantId}">${restaurant.block}</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
</c:choose>
<c:if test="${sessionScope.previousRestaurants.size() > 0}">
    <a href="${pageContext.request.contextPath}/controller?command=all_pharmacies&count_back=true&current_page=${sessionScope.currentPage}"
       style="color: #800000"><fmt:message key="link.previous_restaurants"/> </a>
</c:if>
<c:if test="${sessionScope.nextRestaurants.size() > 0}">
    <a href="${pageContext.request.contextPath}/controller?command=all_restaurants&count_forward=true&current_page=${sessionScope.currentPage}"
       style="color: #800000"><fmt:message key="link.next_restaurants"/></a>
</c:if><br>

<a href="${pageContext.request.contextPath}/controller?command=main_admin" class="common_link"><fmt:message
        key="link.admin_main"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
        key="link.logout"/></a>
<ftg:footer/>
</body>
</html>
