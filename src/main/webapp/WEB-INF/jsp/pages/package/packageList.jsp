<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="imports" tagdir="/WEB-INF/tags/template/imports" %>

<template:pageTemplate title="Households">

    <%-- <imports:getProducts households="${households}"/>--%>

    <div class="row pl-5">
        <div class="col-md-6">
            <h2 class="my-5">Households</h2>
            <table class="table table-striped table-responsive-md">
                <thead>
                <tr>
                    <th>Representative</th>
                    <th>Phone</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${households}" var="household">
                    <tr>
                        <td>${household.representative}</td>
                        <td>${household.phone}</td>
                        <td>${household.status}</td>
                        <td><a href="/packages/deliver/${household.id}/" class="btn btn-secondary"><i
                                class="fa fa-paper-plane" aria-hidden="true"></i>
                        </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</template:pageTemplate>
