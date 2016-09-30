angular.module("carApp").directive("exampleDirective", function () {
    return {
        // szablon
        templateUrl: "example.html",
        // typ znacznika - tu tag aka html
        restrict: "E",
        // przekazanie zawartosci wewnatrz znacznika
        transclude: true,
        // wymagania: tylko jeden root element -> zmiana ciała elementu
        replace: true,
        // zakres
        scope: {
            // parametr przez wartosc
            kolorek: '@',
            // parametr przez referencje
            elementModelu: '=dwukierunkowo'
        },
        link: function(scope, element, attrs) {
            scope.elementModelu = 'ustawiam co chce w 2 strony';

            // dodaje zdarzenie
            element.bind("click", function (event) {
                alert('hoppi!');
            })
        }
    };
});
