<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="imports" tagdir="/WEB-INF/tags/template/imports" %>

<template:pageTemplate title="Package History">

    <div class="row pl-5">
        <div class="col-md-6">
            <h2 class="my-5">Package History</h2>
            <table class="table table-striped table-responsive-md">
                <thead>
                <tr>
                    <th>Representative</th>
                    <th>Phone</th>
                    <th>Package Id</th>
                    <th>Status</th>
                    <th>Delivered date</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${households}" var="household">
                        <c:forEach items="${household.packages}" var="package">
                            <tr>
                            <td>${household.representative}</td>
                            <td>${household.phone}</td>
                            <td>${package.id}</td>
                            <td>${package.status}</td>
                            <td>${package.deliveredDate}</td>
                            </tr>
                        </c:forEach>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</template:pageTemplate>
