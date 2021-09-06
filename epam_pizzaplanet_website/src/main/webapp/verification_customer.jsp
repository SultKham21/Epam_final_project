<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:setBundle basename="properties.pagecontent" />

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/main.css">
    <title><fmt:message key="title.verification.customer"/></title>
</head>
<body>
<p>
    <fmt:message key="msg.finish.registration"/>
</p>
<form action="${pageContext.request.contextPath}/controller?command=verification_customer" method="post">
    <p>
        ${sessionScope.codeVerificationError}
    </p>
    <input type="text" name="code" placeholder="<fmt:message key="placeholder.code"/>"/><br>
    <input type="submit" value="<fmt:message key="button.send"/>"/><br>
</form>
<a href="${pageContext.request.contextPath}/controller?command=login_page"><fmt:message key="link.name.back_to_login_page"/></a>
</body>
</html>
