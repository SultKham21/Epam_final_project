<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftg" uri="/WEB-INF/tld/footer.tld" %>
<%@ page import="by.epam.pizzashop.entity.Status" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title><fmt:message key="header.all_managers"/></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"
      method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}"/>
    <input type="submit" value="${sessionScope.secondLocale}" class="lang"/>
</form>

<h1><fmt:message key="header.list_all_managers"/></h1>
<h2><fmt:message key="header.manager_waiting_verification"/></h2>


<c:if test="${sessionScope.allmanagers.size()>0}">
    <label><fmt:message key="msg.verify.managers"/></label>
    <table border="3">
        <thead>
        <th><fmt:message key="column.table.id"/></th>
        <th><fmt:message key="column.table.first_name"/></th>
        <th><fmt:message key="column.table.last_name"/></th>
        <th><fmt:message key="column.table.status"/></th>
        </thead>

        <c:forEach items="${sessionScope.allmanagers}" var="manager">
            <c:choose>
                <c:when test="${manager.status.equals(Status.IN_REGISTR)}">
                    <tr>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=verification_manager&id=${manager.userId}">${manager.userId}</a>
                        </td>
                        <td>
                                ${manager.firstName}
                        </td>
                        <td>
                                ${manager.lastName}
                        </td>
                        <td>
                                ${manager.status}
                        </td>
                    </tr>
                </c:when>
            </c:choose>
        </c:forEach>
    </table>

    <h2><fmt:message key="header.active_managers"/></h2>
    <label><fmt:message key="msg.click_on_parameter"/></label>
    <table border="3">
        <thead>
        <th><fmt:message key="column.table.id"/></th>
        <th><fmt:message key="column.table.login"/></th>
        <th><fmt:message key="column.table.first_name"/></th>
        <th><fmt:message key="column.table.last_name"/></th>
        <th><fmt:message key="column.table.telephone"/></th>
        <th><fmt:message key="column.table.email"/></th>
        <th><fmt:message key="column.table.status"/></th>
        </thead>

        <c:forEach items="${sessionScope.allmanagers}" var="manager">
            <c:choose>
                <c:when test="${manager.status.equals(Status.ACTIVE)}">
                    <tr>
                        <td>
                                ${manager.userId}
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=updating_manager_login_page&id=${manager.userId}">${manager.login}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=updating_manager_first_name_page&id=${manager.userId}">${manager.firstName}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=updating_manager_last_name_page&id=${manager.userId}">${manager.lastName}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=updating_manager_telephone_page&id=${manager.userId}">${manager.telephone}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=updating_manager_email_page&id=${manager.userId}">${manager.email}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=inactivation_manager&id=${manager.userId}">${manager.status}</a>
                        </td>
                    </tr>
                </c:when>
            </c:choose>
        </c:forEach>
    </table>
</c:if>
<a href="${pageContext.request.contextPath}/controller?command=main_admin" class="common_link"><fmt:message
        key="link.admin_main"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
        key="link.logout"/></a>


</div>
</body>
</html>
