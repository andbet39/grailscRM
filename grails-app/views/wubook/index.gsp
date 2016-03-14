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
<g:link controller="wubook" action="wubookreseervation"><button class="btn btn-danger">New Reservation</button></g:link>


</body>
</html>
