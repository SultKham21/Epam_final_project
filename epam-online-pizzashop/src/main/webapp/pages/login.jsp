<%@page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftg" uri="/WEB-INF/tld/footer.tld" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>


<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/main.css">
    <link rel="stylesheet" href="/styles/common_menu.css">
    <title><fmt:message key="company.name"/></title>
</head>

<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"
      method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}">
    <input type="submit" class="lang"
           value="${sessionScope.secondLocale}">
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

    <h2><fmt:message key="page.name.auth"/></h2>
    <div class="auth">
        <form action="${pageContext.request.contextPath}/controller?command=login" method="post">
            <p>
                <c:if test="${requestScope.loginError != null}">
            <div class="error"><fmt:message key="error.login_user"/></div>
            </c:if>
            <c:if test="${requestScope.verificationError != null}">
                <div class="error"><fmt:message key="error.verification"/></div>
            </c:if>
            </p>
            <input type="text" name="login" placeholder="<fmt:message key="placeholder.name.login"/>"/><br>
            <input type="password" name="password" placeholder="<fmt:message key="placeholder.name.password"/>"/><br>
            <input type="submit" value="<fmt:message key="button.name.enter"/>"/><br>
        </form>
        <a href="${pageContext.request.contextPath}/controller?command=registration_page"><fmt:message
                key="page.registration"/></a><br>
        <a href="${pageContext.request.contextPath}/controller?command=verification_customer_page"><fmt:message
                key="link.name.verification_customer"/></a><br>
    </div>
    <div class="titles">
        <div class="title_first">
            <fmt:message key="upper.title.page.login"/>
        </div>
        <h1>
            <fmt:message key="company.name"/>
        </h1>
    </div>
</body>
</html>

