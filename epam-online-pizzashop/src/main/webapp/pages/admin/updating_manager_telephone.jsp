<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title><fmt:message key="title.update_manager_telephone"/></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"
      method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}"/>
    <input type="submit" value="${sessionScope.secondLocale}" class="lang"/>
</form>

<h1><fmt:message key="title.update_manager_telephone"/></h1>
<form action="${pageContext.request.contextPath}/controller?command=updating_manager_telephone" method="post">
    <c:if test="${requestScope.updatingmanagerTelephoneError != null}">
        <div class="error"><fmt:message key="error.update_user_telephone"/></div>
    </c:if>
    <input type="tel" name="newTelephone" placeholder="<fmt:message key="placeholder.new_telephone"/>"
           class="change_data"/><br>
    <input type="submit" value="<fmt:message key="button.update_telephone"/>" class="button"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=all_managers" class="common_link"><fmt:message
        key="link.all_managers"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=main_admin" class="common_link"><fmt:message
        key="link.admin_main"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
        key="link.logout"/></a>
<ftg:footer/>
</body>
</html>