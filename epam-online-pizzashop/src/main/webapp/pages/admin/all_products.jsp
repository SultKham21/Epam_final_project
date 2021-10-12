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
    <title><fmt:message key="title.all_products"/></title>
</head>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"
      method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}"/>
    <input type="submit" value="${sessionScope.secondLocale}" class="lang"/>
</form>

<h1><fmt:message key="header.list_all_products"/></h1>
<h2><fmt:message key="header.add_new_product_in_form"/></h2>
<form action="${pageContext.request.contextPath}/controller?command=addition_product" method="post">
    <c:if test="${requestScope.productNameError!=null}">
        <div class="error"><fmt:message key="error.product_name"/></div>
    </c:if>
    <c:if test="${requestScope.productDoseError!=null}">
        <div class="error"><fmt:message key="error.product_waight"/></div>
    </c:if>
    <c:if test="${requestScope.productGroupError!=null}">
        <div class="error"><fmt:message key="error.product_group"/></div>
    </c:if>
    <c:if test="${requestScope.productPriceError!=null}">
        <div class="error"><fmt:message key="error.product_price"/></div>
    </c:if>
    <c:if test="${requestScope.productInstructionError!=null}">
        <div class="error"><fmt:message key="error.product_instruction"/></div>
    </c:if>
    <label class="field"><fmt:message key="label.name"/></label><br>
    <input type="text" name="name" value="${requestScope.mapData.get("name")}"
           placeholder="<fmt:message key="placeholder.product_name"/>"/><br>
    <label class="field"><fmt:message key="label.waight"/></label><br>
    <input type="text" name="dose" value="${requestScope.mapData.get("dose")}"
           placeholder="<fmt:message key="placeholder.product_waight"/>"/><br>
    <label class="field"><fmt:message key="label.group"/></><br>
    <input type="text" name="group" value="${requestScope.mapData.get("group")}"
           placeholder="<fmt:message key="placeholder.product_group"/>"/><br>
    <label class="field"><fmt:message key="label.price"/></label><br>
    <input type="text" name="price" value="${requestScope.mapData.get("price")}"
           placeholder="<fmt:message key="placeholder.product_price"/>"/><br>
    <label class="field"><fmt:message key="label.instruction"/></label><br>
    <textarea name="instruction" class="input_instruction"></textarea><br><br>
    <input type="submit" value="<fmt:message key="button.input_product"/>" class="button_product_update">
</form>
<br>
<label><fmt:message key="msg.click_on_parameter"/></label><br>
<label><fmt:message key="msg.click_on_id_if_need_change_picture"/></label><br>

<c:if test="${sessionScope.previousProducts.size() > 0}">
    <a href="${pageContext.request.contextPath}/controller?command=all_products&count_back=true&current_page=${sessionScope.currentPage}"
       style="color: #800000"><fmt:message key="link.previous_products"/> </a>
</c:if>
<c:if test="${sessionScope.nextProducts.size() > 0}">
    <a href="${pageContext.request.contextPath}/controller?command=all_products&count_forward=true&current_page=${sessionScope.currentPage}"
       style="color: #800000"><fmt:message key="link.next_products"/></a>
</c:if><br>

<c:choose>
    <c:when test="${sessionScope.currentProducts.size()>0}">

        <table border="3">
            <thead>
            <th><fmt:message key="column.table.id"/></th>
            <th><fmt:message key="column.table_name"/></th>
            <th><fmt:message key="column.table_waight"/></th>
            <th><fmt:message key="column.table_group"/></th>
            <th><fmt:message key="column.table_price"/></th>
            <th><fmt:message key="column.table_instruction"/></th>
            </thead>

            <c:forEach items="${sessionScope.currentProducts}" var="product">

                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=addition_picture_page&product_id=${product.productId}">${product.productId}</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=updating_product_name_page&product_id=${product.productId}">${product.name}</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=updating_product_dose_page&product_id=${product.productId}">${product.dose}</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=updating_product_group_page&product_id=${product.productId}">${product.group}</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=updating_product_price_page&product_id=${product.productId}">${product.price}</a>

                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=updating_product_instruction_page&product_id=${product.productId}">${product.instruction}</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </c:when>
</c:choose>

<c:if test="${sessionScope.previousProducts.size() > 0}">
    <a href="${pageContext.request.contextPath}/controller?command=all_products&count_back=true&current_page=${sessionScope.currentPage}"
       style="color: #800000"><fmt:message key="link.previous_products"/> </a>
</c:if>
<c:if test="${sessionScope.nextProducts.size() > 0}">
    <a href="${pageContext.request.contextPath}/controller?command=all_products&count_forward=true&current_page=${sessionScope.currentPage}"
       style="color: #800000"><fmt:message key="link.next_products"/></a>
</c:if><br>

<a href="${pageContext.request.contextPath}/controller?command=main_admin" class="common_link"><fmt:message
        key="link.admin_main"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
        key="link.logout"/></a>
<ftg:footer/>
</body>
</html>