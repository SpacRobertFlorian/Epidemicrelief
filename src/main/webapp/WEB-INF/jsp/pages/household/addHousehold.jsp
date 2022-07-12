<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<template:pageTemplate title="Add Household">

    <div class="container my-5">
        <h2 class="mb-5">New Household</h2>
        <div class="row">
            <div class="col-md-6">
                <form action="/households/save/" method="post">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="representative" class="col-form-label">Representative</label>
                            <input type="text" name="representative" id="representative" class="form-control"
                                   placeholder="Representative">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="phone" class="col-form-label">Phone</label>
                            <input type="text" name="phone" id="phone" class="form-control" placeholder="Phone">
                        </div>

                        <div class="form-group col-md-6">
                            <label for="email" class="col-form-label">Email</label>
                            <input type="email" name="email" id="email" class="form-control" placeholder="Email">
                        </div>

                        <div class="form-group col-md-6">
                            <label for="address" class="col-form-label">Contact address</label>
                            <input type="text" name="address" id="address" class="form-control" placeholder="Address">
                        </div>

                        <div class="form-group col-md-6">
                            <label for="numberOfPeople" class="col-form-label">People</label>
                            <input type="number" name="numberOfPeople" id="numberOfPeople" class="form-control"
                                   placeholder="People">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="numberOfChildren" class="col-form-label">Children</label>
                            <input type="number" name="numberOfChildren" id="numberOfChildren" class="form-control"
                                   placeholder="Children">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="numberOfVegans" class="col-form-label">Vegans</label>
                            <input type="number" name="numberOfVegans" id="numberOfVegans" class="form-control"
                                   placeholder="Vegans">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="numberOfNonVegans" class="col-form-label">Non Vegans</label>
                            <input type="number" name="numberOfNonVegans" id="numberOfNonVegans" class="form-control"
                                   placeholder="Non Vegans">
                        </div>
                    </div>
<%--                    <c:forEach items="${bindingResultMsg}" var="resultMsg">--%>
<%--                        <tr>--%>
<%--                            <td>${resultMsg}</td>--%>
<%--                        </tr>--%>
<%--                    </c:forEach>--%>
                    <p>${bindingResultMsg}</p>
                    <div class="row">
                        <div class="col-md-6 mt-5">
                            <input type="submit" class="btn btn-primary" value="Add Household">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</template:pageTemplate>
