<!DOCTYPE html>
<html ng-app="carApp">
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

<div ng-controller="carCtrl">

    <p>Asynchronous getting message:</p>

    <p>{{carData}}</p>
    <table>
        <tr>
            <th>numer rejestracyjny</th>
            <th>silnik</th>
            <th>akcje</th>
        </tr>
        <tr ng-repeat="x in carData">
            <td>{{x.numerRej}}</td>
            <td>{{x.silnik }}</td>
            <td>
                <button ng-click="delete(x.id)">USUN</button>
            </td>
        </tr>
    </table>
    <br/><br/>

    <form>
        <table>
            <tr>
                <td>numer rejestracyjny:</td>
                <td><input type="text" ng-model="numerRej"></td>
            </tr>
            <tr>
                <td>silnik:</td>
                <td><input type="text" ng-model="silnik"></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button ng-click="send()">ZATWIERDŹ</button>
                </td>
            </tr>

        </table>
        <br/>

    </form>
    <h1>Twój samochód: '{{numerRej}}' ma silnik '{{silnik}}'.</h1>
</div>

<script>
    var app = angular.module('carApp', []);
    app.controller('carCtrl', function ($scope, $http) {
        $http.get("car").then(function (response) {
            $scope.carData = response.data;
        });
        $scope.send = function () {
            var data = {"numerRej": $scope.numerRej, "silnik": $scope.silnik};
            $http.post('car', data).then(function (response) {
                $scope.carData.push(response.data);
            }, function (response) {
                alert('error');
            });
        };
        $scope.delete = function (id) {
            $http.delete('car/' + id).then(function (response) {

            });
            //alert("okidoki: " + id);
        };
    });
</script>

</body>
</html>

