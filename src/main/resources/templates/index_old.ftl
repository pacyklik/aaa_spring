<!DOCTYPE html>
<html ng-app="carApp">
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>
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
            <td ng-hide="editId==x.id">{{x.numerRej}}</td>
            <td ng-hide="editId==x.id">{{x.silnik }}</td>
            <td ng-show="editId==x.id"><input type="text" id="numerRej_{{x.id}}" value="{{x.numerRej}}"/></td>
            <td ng-show="editId==x.id"><input type="text" id="silnik_{{x.id}}" value="{{x.silnik }}"/></td>
            <td ng-hide="editId==x.id">
                <button ng-click="delete(x.id)">USUN</button>
                <button ng-click="edit(x.id)">EDYTUJ</button>
            </td>
            <td ng-show="editId==x.id">
                <button ng-click="confirm(x.id)">ZATWIERDZ</button>
                <button ng-click="cancel()">ANULUJ</button>
            </td>
        </tr>
    </table>
    <button ng-click="debug()">DEBUG</button>
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
    <br/>
    <!-- tutaj wstrzykujemy nasz widok -->
    ngRoute example:
    <br/>

    <div data-ng-view>
        Loading...
    </div>
</div>

<script>
    var app = angular.module('carApp', ['ngRoute']);
    // dodajemy kontroler
    app.controller('carCtrl', function ($scope, $http, $document) {
        // definiujemy co sie stanie na poczatku
        $http.get("car").then(function (response) {
            $scope.carData = response.data;
        });
        // definiujemy metody, model
        $scope.send = function () {
            var data = {"numerRej": $scope.numerRej, "silnik": $scope.silnik};
            $http.post('car', data).then(function (response) {
                $scope.carData.push(response.data);
                $scope.numerRej = '';
                $scope.silnik = '';
            }, function (response) {
                alert('error');
            });
        };
        $scope.delete = function (id) {
            $http.delete('car/' + id).then(function (response) {
                $scope.carData = response.data;
            }, function (response) {
                alert('error');
            });
        };
        $scope.edit = function (id) {
            $scope.editId = id;
        };
        $scope.confirm = function (id) {
            // TODO
//            var numerRej = $document.getElementById('#numerRej_' + id);
//            var numerRej = angular.element('#numerRej_' + id);
            alert($document[0].getElementById('#numerRej_' + id));

            $scope.editId = 0;
        };
        $scope.cancel = function () {
            $scope.editId = 0;
        }
        $scope.debug = function () {
            alert($scope.editId);
        }
    });
    // dodajemy routing
    // zaleznie czy jest /#/ czy /#/list co innego sie pobierze i wyswietli
    app.config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider
                    .when('/', {
                        templateUrl: 'example.html',
                        controller: 'carCtrl'
                    }, null)
                    .when('/list', {
                        templateUrl: 'list.html',
                        controller: 'carCtrl'
                    }, null);
        }]);
</script>

</body>
</html>

