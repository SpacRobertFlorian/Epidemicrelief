<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<template:pageTemplate title="Delivery Date Threshold configuration">

    <form action="/configuration/deliverydatethreshold/" method="post">
        <div class="form-group col-md-6">
            <label for="deliveryDateThreshold" class="col-form-label">Delivery Date Threshold</label>
            <input type="number" name="deliveryDateThreshold" id="deliveryDateThreshold" class="form-control"
                   placeholder="Delivery Date Threshold">
        </div>
        <div class="row">
            <div class="col-md-6 mt-5">
                <input type="submit" class="btn btn-primary" value="Set deliver date">
            </div>
        </div>
    </form>
</template:pageTemplate>
