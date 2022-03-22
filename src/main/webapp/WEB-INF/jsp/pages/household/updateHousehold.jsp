<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<template:pageTemplate title="UpdateHousehold">

    <div class="container my-5">
        <h2 class="mb-5">Update Household</h2>
        <div class="row pl-5">
            <div class="col-md-6">
                <form action="/households/update/" method="post">
                    <input name="id" type="hidden" value="${household.id}">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="representative" class="col-form-label">Representative</label>
                            <input type="text" name="representative" id="representative" class="form-control"
                                   value="${household.representative}" placeholder="Representative">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="phone" class="col-form-label">Phone</label>
                            <input type="text" name="phone" id="phone" class="form-control" value="${household.phone}"
                                   placeholder="Phone">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mt-5">
                            <input type="submit" class="btn btn-primary" value="Update Household">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </body>
    </html>

</template:pageTemplate>
