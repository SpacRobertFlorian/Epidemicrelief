<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<template:pageTemplate title="Products">

    <form action="/products/" method="post">
        Select a Category:&nbsp;
        <label>
            <select name="productCategory">
                <c:forEach items="${categories}" var="entry">
                    <option value="${entry}">${entry}</option>
                </c:forEach>
            </select>
        </label>
        <br/><br/>
        <input type="submit" class="btn btn-primary" value=<fmt:setLocale value="${locale}"/>
        <fmt:bundle basename="Bundle">
            <fmt:message key="Submit"/>
        </fmt:bundle>/>
    </form>

    <div class="row pl-5">
        <div class="col-md-6">
            <h2 class="my-5"><fmt:setLocale value="${locale}"/>
                <fmt:bundle basename="Bundle">
                    <fmt:message key="Products"/>
                </fmt:bundle></h2>
            <table class="table table-striped table-responsive-md">
                <thead>
                <tr>
                    <th><fmt:setLocale value="${locale}"/>
                        <fmt:bundle basename="Bundle">
                            <fmt:message key="Name"/>
                        </fmt:bundle></th>
                    <th><fmt:setLocale value="${locale}"/>
                        <fmt:bundle basename="Bundle">
                            <fmt:message key="Category"/>
                        </fmt:bundle></th>
                    <th><fmt:setLocale value="${locale}"/>
                        <fmt:bundle basename="Bundle">
                            <fmt:message key="Stock"/>
                        </fmt:bundle></th>
                    <th><fmt:setLocale value="${locale}"/>
                        <fmt:bundle basename="Bundle">
                            <fmt:message key="Price"/>
                        </fmt:bundle></th>
                    <th><fmt:setLocale value="${locale}"/>
                        <fmt:bundle basename="Bundle">
                            <fmt:message key="Currency"/>
                        </fmt:bundle></th>

                    <th><fmt:setLocale value="${locale}"/>
                        <fmt:bundle basename="Bundle">
                            <fmt:message key="Edit"/>
                        </fmt:bundle></th>
                    <th><fmt:setLocale value="${locale}"/>
                        <fmt:bundle basename="Bundle">
                            <fmt:message key="Delete"/>
                        </fmt:bundle></th>
                </tr>
                </thead>
                <tbody>
                <div>
                    <c:forEach items="${products}" var="product">
                        <tr>
                            <td>${product.name}</td>
                            <td>${product.productCategory}</td>
                                <%--                            <td>${product.stock}</td>--%>
                            <c:choose>
                                <c:when test="${product.stock < threshold}">
                                    <td style="color:red;">${product.stock}</td>
                                    <br/>
                                </c:when>
                                <c:otherwise>
                                    <td>${product.stock}</td>
                                    <br/>
                                </c:otherwise>
                            </c:choose>
                            <td>${product.price}</td>
                            <td>${product.currency}</td>
                            <td><a href="/products/edit/${product.id}/" class="btn btn-secondary"><i
                                    class="fas fa-user-edit ml-2"></i></a></td>
                            <td><a href="/products/delete/${product.id}/" class="btn btn-secondary"><i
                                    class="fas fa-user-times ml-2"></i></a></td>
                        </tr>
                    </c:forEach>
                </div>
                </tbody>
            </table>
            <div class="pt-3">
                <a href="/products/new/" class="btn btn-secondary"><i class="fas fa-user-plus ml-2"></i></a>
            </div>
        </div>
    </div>

</template:pageTemplate>
