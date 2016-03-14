<%--
  Created by IntelliJ IDEA.
  User: andreaterzani
  Date: 03/03/16
  Time: 12:55
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

<div ng-app="myapp">
    <div ng-controller="MainController">
        {{'This is angular'}}

        <ul>
        <li ng-repeat="message in messages">
            {{message.message}}
        </li>
    </ul>

        <input type="text" ng-model="newmessage"><button ng-click="send()" class="btn btn-success">Send</button>
    </div>
</div>

<asset:javascript src="app/app.js"/>

</body>
</html>
