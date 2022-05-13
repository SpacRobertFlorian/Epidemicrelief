<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<template:pageTemplate title="Products">
    <div class="row pl-5">
        <div class="col-md-6">
            <h2 class="my-5">Products</h2>
            <table class="table table-striped table-responsive-md">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Stock</th>
                    <th>Edit</th>
                </tr>
                </thead>
                <tbody>
                <div>
                    <c:forEach items="${products}" var="product">
                        <tr>
                            <td>${product.name}</td>
                            <td>${product.productCategory}</td>
                            <c:choose>
                                <c:when test="${product.stock < threshold}">
                                    <td style="color:red;">${product.stock}</td>
                                    <td><a href="/packages/create/" class="btn btn-secondary"><i
                                            class="fas fa-user-edit ml-2"></i></a></td>
                                    <br/>
                                </c:when>
                                <c:otherwise>
                                    <td style="color:lawngreen;">${product.stock}</td>
                                    <br/>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </div>
                </tbody>
            </table>
            <form action="/packages/fill/" method="get">
                <input type="submit" class="btn btn-primary" value="Fill"/>
            </form>
        </div>
    </div>

</template:pageTemplate>
