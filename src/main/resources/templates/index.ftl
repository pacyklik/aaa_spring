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

    // by not sending a “WWW-Authenticate” header in a 401 response
    app.config(function ($httpProvider) {
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    });


    app.run(function ($rootScope, $timeout, $state, $window) {

        // zdarzenie zmiany widoku
        $rootScope.$on('$stateChangeSuccess', function (event, fromState, fromParams, toState, toParams) {

            // jesli brak usera -> strona logowania
            if ($window.sessionStorage["authenticatedId"] === undefined) {
                $state.go('login');
            }
        });
    });

    app.controller('appCtrl', function ($scope, $http, $rootScope, $document, $state, $window) {

        $scope.logout = function () {
            $http.post('logout', {}).finally(function() {
                $window.sessionStorage["authenticatedId"] = undefined;
                $state.go('login');
            });
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

    app.controller('loginCtrl', function ($scope, $http, $rootScope, $document, $state, $window) {

        $rootScope.userInfo = undefined;

        var authenticate = function (credentials, callback) {
            var headers = credentials ? {authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)} : {};
            var params = credentials ? {'remember-me': credentials.remember_me} : {};
            $http.get('/api/user', {headers: headers, params: params}).then(function (response) {
                if (response.data.name) {
                    // good way
                    $window.sessionStorage["authenticatedId"] = response.data.name;
                } else {
                    $window.sessionStorage["authenticatedId"] = undefined;
                }
                callback && callback();
            }, function (error) {
                $window.sessionStorage["authenticatedId"] = undefined;
                callback && callback();
            });
        };

        authenticate();
        $scope.login = function () {
            authenticate($scope.credentials, function () {
                if ($window.sessionStorage["authenticatedId"] !== "undefined") {
                    alert("login-ok: " + JSON.stringify($window.sessionStorage["authenticatedId"]));
                    $state.go("list");
                } else {
                    alert("login-error");
                    $state.go('login');
                }
            });
        };

    });
</script>

</body>
</html>

