
var app = angular.module('carApp', ['ui.router', 'ui.bootstrap']);

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

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('$q', '$window', function ($q, $window) {
        return {
            'responseError': function (rejection) {
                var defer = $q.defer();
                if (rejection.status == 401 && $window.sessionStorage["authenticatedId"] != null) {
                    alert("Sesja wygasla, zaloguj sie ponownie:)");
                    $window.sessionStorage.removeItem("authenticatedId");
                    $window.location.reload(true);
                }
                defer.reject(rejection);
                return defer.promise;
            }
        };
    });
}]);

app.run(function ($rootScope, $timeout, $state, $window) {
    // zdarzenie zmiany widoku
    $rootScope.$on('$stateChangeSuccess', function (event, fromState, fromParams, toState, toParams) {
        // jesli brak usera -> strona logowania
        if ($window.sessionStorage["authenticatedId"] == null) {
            $state.go('login');
        }
    });
});