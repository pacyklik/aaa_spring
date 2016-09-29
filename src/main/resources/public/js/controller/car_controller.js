angular.module('carApp').controller('carCtrl', function ($scope, $uibModal, $http, CarService, $document, $state) {
    // definiujemy co sie stanie na poczatku
    CarService.allCars().then(function (response) {
        $scope.carData = response.data;
    });
    // definiujemy metody, model
    $scope.send = function () {
        var data = {"numerRej": $scope.numerRej, "silnik": $scope.silnik};
        CarService.sendCar(data).then(function (response) {
            $scope.carData.push(response.data);
            $scope.numerRej = '';
            $scope.silnik = '';
        }, function (response) {
            alert('error');
        });
    };
    $scope.delete = function (id) {
        CarService.deleteCar(id).then(function (response) {
            $scope.carData = response.data;
        }, function (response) {
            alert('error');
        });
    };
    $scope.edit = function (id) {
        var modalInstance = $uibModal.open({
            templateUrl: "car-modal.html",
            controller: "CarModalController",
            resolve: {
                params: function () {
                    return {
                        title: "EDYCJA",
                        carId: id
                    };
                }
            }
        });

        modalInstance.result.then(function (data) {
            // $scope.carData.push(data);
            CarService.allCars().then(function (response) {
                $scope.carData = response.data;
            });
        });
    };

    $scope.newCar = function () {
        var modalInstance = $uibModal.open({
            templateUrl: "car-modal.html",
            controller: "CarModalController",
            resolve: {
                params: function () {
                    return {
                        title: "NOWY"
                    };
                }
            }
        });

        modalInstance.result.then(function (data) {
            $scope.carData.push(data);
            //alert("from modal: " + JSON.stringify(data));
        });
    };
});