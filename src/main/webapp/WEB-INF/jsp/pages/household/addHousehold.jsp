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
                    </div>
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
