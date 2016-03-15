<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'check.label', default: 'Check')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<!-- Page Content -->

<div class="row">
    <div class="col-md-4">
        <h2>Check In</h2>
        <table class="table">
            <thead><tr>
                <td>Name</td>
                <td>Room</td>
                <td>Occ</td>
            </tr></thead>
            <tbody><g:each var="res" in="${checkin}">
                <tr>
                    <td>${res.customer.name} ${res.customer.surname}</td>
                    <td>${res.room.name}</td>
                    <td>${res.baseReservation.men }</td>
                </tr>
            </g:each></tbody>
        </table>
    </div>
    <div class="col-md-4">
        <h2>Check Out</h2>

        <table class="table">
            <thead><tr>
                <td>Name</td>
                <td>Room</td>
                <td>Occ</td>
            </tr></thead>
            <tbody><g:each var="res" in="${checkout}">
                <tr>
                    <td>${res.customer.name} ${res.customer.surname}</td>
                    <td>${res.room.name}</td>
                    <td>${res.baseReservation.men }</td>
                </tr>
            </g:each></tbody>
        </table>
    </div>
</div>



</body>
</html>
