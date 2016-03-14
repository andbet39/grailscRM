<%--
  Created by IntelliJ IDEA.
  User: andreaterzani
  Date: 03/03/16
  Time: 10:27
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>

</head>

<body>

<script src="https://cdn.socket.io/socket.io-1.4.5.js"></script>

<script>

    var userName = 'user' + Math.floor((Math.random()*1000)+1);
    var socket =  io.connect('http://localhost:9092');


    socket.on('connect', function() {
        output('<span class="connect-msg">Client has connected to the server!</span>');
    });

    socket.on('chatevent', function(data) {
        output('<span class="username-msg">' + data.userName + ':</span> ' + data.message);
    });

    socket.on('disconnect', function() {
        output('<span class="disconnect-msg">The client has disconnected!</span>');
    });
    function sendDisconnect() {
        socket.disconnect();
    }

    function sendMessage() {
        var message = $('#msg').val();
        $('#msg').val('');

        var jsonObject = {userName: userName,
            message: message};
        socket.emit('chatevent', jsonObject);
    }

    function output(message) {
        var element = $("<div>" + message + "</div>");
        $('#console').prepend(element);
    }

</script>

<div id="console" class="well">
</div>

<form class="well form-inline" onsubmit="return false;">
    <input id="msg" class="input-xlarge" type="text" placeholder="Type something..."/>
    <button type="button" onClick="sendMessage()" class="btn">Send</button>
    <button type="button" onClick="sendDisconnect()" class="btn">Disconnect</button>
</form>



</body>
</html>
