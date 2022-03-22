<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<template:pageTemplate title="Households">

    <div class="row pl-5">
        <div class="col-md-6">
            <h2 class="my-5">Households</h2>
            <table class="table table-striped table-responsive-md">
                <thead>
                <tr>
                    <th>Representative</th>
                    <th>Phone</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${households}" var="household">
                    <tr>
                        <td>${household.representative}</td>
                        <td>${household.phone}</td>
                        <td><a href="/households/edit/${household.id}/" class="btn btn-secondary"><i
                                class="fas fa-user-edit ml-2"></i></a></td>
                        <td><a href="/households/delete/${household.id}/" class="btn btn-secondary"><i
                                class="fas fa-user-times ml-2"></i></a></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
            <div class="pt-3">
                <a href="/households/new/" class="btn btn-secondary"><i class="fas fa-user-plus ml-2"></i></a>
            </div>
        </div>
    </div>

</template:pageTemplate>
