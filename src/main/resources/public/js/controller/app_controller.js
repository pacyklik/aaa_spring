
angular.module('carApp').controller('appCtrl', function ($scope, $http, $rootScope, $document, $state, $window) {
    $scope.logout = function () {
        $window.sessionStorage.removeItem("authenticatedId");
        $rootScope.userName = null;
        $http.post('logout', {}).finally(function () {
            $state.go('login');
        });
    }
});