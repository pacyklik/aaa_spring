<!DOCTYPE html>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<style>
    table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 500px;
    }

    td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even) {
        background-color: #dddddd;
    }
</style>
</head>
<body>

<div ng-app="myApp" ng-controller="myCtrl">
    <p>Asynchronous getting message:</p>
    <p>{{carData}}</p>
    <table>
        <tr><th>numer rejestracyjny</th><th>silnik</th></tr>
        <tr ng-repeat="x in carData">
            <td>{{ x.numerRej}}</td><td>{{x.silnik }}</td>
        </tr>
    </table>
</div>

<script>
    var app = angular.module('myApp', []);
    app.controller('myCtrl', function($scope, $http) {
        $http.get("car").then(function (response) {
            $scope.carData = response.data;
        });
    });
</script>

</body>
</html>

