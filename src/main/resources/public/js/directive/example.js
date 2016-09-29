angular.module("carApp").directive("exampleDirective", function () {
    return {
        // szablon
        templateUrl: "example.html",
        // typ znacznika - tu tag aka html
        restrict: "E",
        // przekazanie zawartosci wewnatrz znacznika
        transclude: true,
        // zakres
        scope: {
            // parametr przez wartosc
            kolorek: '@'
        }
    };
});
