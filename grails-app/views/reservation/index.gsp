<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'reservation.label', default: 'Reservation')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-reservation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-reservation" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <table id="table-reservation" class="table table-striped table-bordered table-hover">
               <thead>
               <tr>
                <td>
                    Codice
                </td>
                <td>
                    Nome
                </td>
                <td>
                    Arrivo
                </td>
                <td>
                    Partenza
                </td>
                <td>
                    Stato
                </td><td>
                    Canale
                </td>
               </tr>
               </thead>
                <g:each var="res" in="${reservationList}">
                    <tr>
                        <td>
                        <g:link action="show" id="${res.id}">${res.wubookcode}</g:link>
                        </td>
                        <td>
                            ${res.guestName}
                        </td>
                        <td>
                            ${res.arrivalDate}
                        </td>
                        <td>
                            ${res.departureDate}
                        </td>

                        <td>
                            ${res.status}
                        </td>

                        <td>
                            ${res.origin}
                        </td>
                    </tr>
             </g:each>
            </table>
        </div>
    </body>
</html>
