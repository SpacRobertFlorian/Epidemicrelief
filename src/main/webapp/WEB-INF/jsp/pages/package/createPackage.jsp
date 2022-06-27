<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>


<template:pageTemplate title="Products">
    <div class="row pl-5">
        <div class="col-md-6">
            <h2 class="my-5">Products</h2>
            <c:choose>
                <c:when test="${package.status =='DELIVERED'}">
                    <c:if test="${package.dateDiff < dateThreshold}">
                        <p>Can't deliver new package. ${dateThreshold} days have not passed</p>
                    </c:if>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${package.status =='CREATED'}">
                    <div style='position:relative; padding-bottom:calc(75.00% + 44px)'>
                        <iframe src='https://gfycat.com/ifr/AdorableSmugDragonfly' frameborder='0'
                                scrolling='no'
                                width='100%' height='100%' style='position:absolute;top:0;left:0;'
                                allowfullscreen></iframe>
                    </div>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${package.status == 'READY' }">
                    <table class="table table-striped table-responsive-md">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Stock</th>
                        </tr>
                        </thead>
                        <tbody>
                        <div>
                            <c:forEach items="${package.products}" var="product">
                                <tr>
                                    <td>${product.product.name}</td>
                                    <td>${product.product.productCategory}</td>
                                    <c:choose>
                                        <c:when test="${product.product.stock < threshold}">
                                            <td style="color:red;">${product.product.stock}</td>
                                            <c:if test="${status =='CREATED'}">
                                                <td><a href="/packages/create/" class="btn btn-secondary"><i
                                                        class="fas fa-user-edit ml-2"></i></a></td>
                                                <br/>
                                            </c:if>
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
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${package.status =='NOT_CREATED' or status == 'NOT_CREATED'}">
                    <c:if test="${package.dateDiff > dateThreshold or package.createdDate == null }">
                        <form action="/packages/deliver/${idHousehold}" method="post">
                            <input type="submit" class="btn btn-primary" value="Create"/>
                        </form>
                    </c:if>
                </c:when>
                <c:when test="${package.status =='CREATED'}">
                    <form action="/packages/deliver/${package.household.id}/fill" method="post">
                        <input type="submit" class="btn btn-primary" value="Ready"/>
                    </form>
                    <form action="/packages/cancel/${package.household.id}" method="post">
                        <input type="submit" class="btn btn-primary" value="Cancel"/>
                    </form>
                </c:when>
                <c:when test="${package.status =='READY'}">
                    <form action="/packages/deliver/${package.household.id}/send" method="post">
                        <input type="submit" class="btn btn-primary" value="Delivered"/>
                    </form>
                    <form action="/packages/cancel/${package.household.id}" method="post">
                        <input type="submit" class="btn btn-primary" value="Cancel"/>
                    </form>
                </c:when>
                <c:when test="${package.status =='DELIVERED'}">
                    <c:if test="${package.dateDiff > dateThreshold or package.createdDate == null }">
                        <form action="/packages/deliver/${package.household.id}" method="post">
                            <input type="submit" class="btn btn-primary" value="Create"/>
                        </form>
                        <form action="/packages/cancel/${package.household.id}" method="post">
                            <input type="submit" class="btn btn-primary" value="Cancel"/>
                        </form>
                    </c:if>
                </c:when>
            </c:choose>
        </div>
    </div>

</template:pageTemplate>