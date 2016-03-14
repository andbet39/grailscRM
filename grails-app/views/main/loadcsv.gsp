<%--
  Created by IntelliJ IDEA.
  User: andreaterzani
  Date: 02/03/16
  Time: 19:08
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

    <g:form action="importcsv">
        <g:textArea name="csv"  rows="10" cols="40"/>
        <g:submitButton name="import" class="btn btn-default" value="Import" />
    </g:form>
</body>
</html>
