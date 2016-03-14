<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'reservation.label', default: 'Reservation')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>

<g:link controller="wubook" action="wubooktoken"><button class="btn btn-succes">Token</button></g:link>
<g:link controller="wubook" action="wubookroom"><button class="btn btn-danger">Room</button></g:link>
 <g:link controller="wubook" action="wubooknewreservation"><button class="btn btn-danger">New Reservation</button></g:link>

<g:link controller="reservationWobook" action="index"><button class="btn btn-danger">View Reservation</button></g:link>

<g:form controller="wubook" action="wubookreservation">
    <g:datePicker  precision="day" name="date_from"></g:datePicker>
    <br>
    <g:datePicker  precision="day" name="date_to"></g:datePicker>
    <g:submitButton name="update" value="Fetch" />
</g:form>
</body>
</html>
