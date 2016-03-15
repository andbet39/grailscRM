<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'check.label', default: 'Check')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<!-- Page Content -->
<h2>Questa settimana</h2>
 <div class="row">
    <div class="col-lg-3 col-md-6">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-3">
                        <i class="fa fa-comments fa-5x"></i>
                    </div>
                    <div class="col-xs-9 text-right">
                        <div class="huge">${n_colazioni} </div>
                        <div>Colazioni</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-lg-3 col-md-6">
        <div class="panel panel-success">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-3">
                        <i class="fa fa-comments fa-5x"></i>
                    </div>
                    <div class="col-xs-9 text-right">
                        <div class="huge">${costo_colazioni} </div>
                        <div>Costo Colazioni</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<div class="row">
    <div class="col-md-6">
        <h2>Arrivi</h2>
        <table class="table">
            <thead><tr>
                <td>Arrivo</td>
                <td>Partenza</td>
                <td>Name</td>
                <td>Room</td>
                <td>Occ</td>
            </tr></thead>
            <tbody><g:each var="res" in="${checkin}">
                <tr><td>
                    ${res.baseReservation.date_arrival}
                </td>
                    <td>${res.baseReservation.date_departure}</td>
                    <td>${res.customer.name} ${res.customer.surname}</td>
                    <td>${res.room.name}</td>
                    <td>${res.baseReservation.men }</td>
                </tr>
            </g:each></tbody>
        </table>
    </div>

    <div class="col-md-6">
        <h2>Checkout</h2>
        <table class="table">
            <thead><tr>
                <td>Arrivo</td>
                <td>Partenza</td>
                <td>Name</td>
                <td>Room</td>
                <td>Occ</td>
            </tr></thead>
            <tbody><g:each var="res" in="${checkout}">
                <tr><td>
                    ${res.baseReservation.date_arrival}
                </td>
                    <td>${res.baseReservation.date_departure}</td>
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
