//= wrapped
//= require /angular/angular
//= require /angular/angular-route
//= require /socket.io-client/socket.io.js
//= require_self (1)

angular.module("myapp",[]).controller("MainController", MainController);

function MainController($scope,$http) {
    var vm = this;

    $scope.messages = [];
    $scope.newmessage="";

    var socket = io.connect('http://localhost:9092');

    socket.on('connect', function() {
        console.log("connect")
        $scope.messages.push("Connected");
        $scope.$apply();
    });

    socket.on('chatevent', function(data) {
        console.log("ChatEvent")
        $scope.messages.push({'username':data.username,'message':data.message});
        $scope.$apply();
    });

    socket.on('disconnect', function() {
        $scope.messages.push("Disconnected");
        $scope.$apply();
    });

    $scope.init = function(){

        $http.get('http://localhost:8080/messages').then(function(data){
            console.log(data);
            data.data.forEach(function(mes){

                $scope.messages.push({userName:mes.username,
                                        message:mes.message});
            })
        })
    };

    $scope.send = function(){

        var mess= {
            userName:"prova",
            message:$scope.newmessage
        };

        $http.post('http://localhost:8080/messages',mess).then(function(data){

            var jsonObject = {userName: "test",
                message: $scope.newmessage};
            socket.emit('chatevent', jsonObject);
                $scope.newmessage="";

        },function(err){
            console.error(err)
        })
    };

    $scope.init();
}
