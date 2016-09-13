<!DOCTYPE html>
<html ng-app="carApp">
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script src="http://angular-ui.github.io/ui-router/release/angular-ui-router.min.js"></script>
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

<div ng-controller="appCtrl">
    <button ng-click="logout()">WYLOGUJ</button>
    <hr/>
    <div ui-view>
        Loading...
    </div>
</div>


<script>
    var app = angular.module('carApp', ['ui.router']);

    // dodajemy routing
    // zaleznie czy jest /#/ czy /#/list co innego sie pobierze i wyswietli
    app.config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider
                .state("login", {
                    url: "/login",
                    templateUrl: 'login.html',
                    controller: 'loginCtrl',
                }).state("list", {
                    url: "/list",
                    templateUrl: 'list.html',
                    controller: 'carCtrl'
                });

        $urlRouterProvider.otherwise("/login");
    });

    app.run(function ($rootScope, $timeout, $state, $window) {
        // pobranie z sessionStorage czy użytkownik zalogowany i przekazanie do zmiennej
        $rootScope.authenticatedId = $window.sessionStorage["authenticatedId"];

        // zdarzenie zmiany widoku
        $rootScope.$on('$stateChangeSuccess', function (event, fromState, fromParams, toState, toParams) {

            // jesli brak usera -> strona logowania
            if ($rootScope.authenticatedId === undefined) {
                $state.go('login');
            }
        });
    });

    app.controller('appCtrl', function ($scope, $http, $rootScope, $document, $state, $window) {

        // przyklad obserwowania zmiennych
        $rootScope.$watch('authenticatedId', function (newVal, oldVal) {
            $window.sessionStorage["authenticatedId"] = newVal;
            if (newVal !== undefined) {
                $state.go("list");
            } else {
                $state.go('login');
            }
        });

        $scope.logout = function () {
            $rootScope.authenticatedId = undefined;
        }

    });

    app.controller('carCtrl', function ($scope, $http, $document, $state) {
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
//            alert($document[0].getElementById('#numerRej_' + id));

            $scope.editId = 0;
        };
        $scope.cancel = function () {
            $scope.editId = 0;
        }
    });

    app.controller('loginCtrl', function ($scope, $http, $rootScope, $document, $state) {
        $scope.login = function () {
            var data = {"username": $scope.credentials.username, "password": $scope.credentials.password};
            $http.post('/api/user', data).then(function (response) {
                if (response.data.id != null) {
                    $rootScope.authenticatedId = response.data.id;
                }
            }, function (response) {
                alert('error');
            });
        }
    });
</script>

</body>
</html>

