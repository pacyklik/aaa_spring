
angular.module('carApp').controller('loginCtrl', function ($scope, $http, $rootScope, $document, $state, $window) {

    $rootScope.userInfo = null;

    var authenticate = function (credentials, callback) {
        var headers = credentials ? {authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)} : {};
        var params = credentials ? {'remember-me': credentials.remember_me} : {};
        $http.get('/api/user', {headers: headers, params: params}).then(function (response) {
            if (response.data.name) {
                $window.sessionStorage["authenticatedId"] = response.data.name;
                $rootScope.userName = response.data.name;
            } else {
                $window.sessionStorage.removeItem("authenticatedId");
                $rootScope.userName = null;
            }
            callback && callback();
        }, function (error) {
            $window.sessionStorage.removeItem("authenticatedId");
            $rootScope.userName = null;
            callback && callback();
        });
    };

    authenticate();
    $scope.login = function () {
        authenticate($scope.credentials, function () {
            if ($window.sessionStorage["authenticatedId"] != null) {
                // alert("login-ok: " + JSON.stringify($window.sessionStorage["authenticatedId"]));
                $state.go("list");
            } else {
                $state.go('login');
            }
        });
    };

});