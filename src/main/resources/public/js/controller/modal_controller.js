angular.module('carApp').controller('CarModalController', function ($scope, $uibModalInstance, CarService, params) {

    $scope.modal = params;
    $scope.car = {};

    if (params.carId != null) {
        CarService.oneCar(params.carId).then(function (response) {
            $scope.car = response.data;
        });
    }

    $scope.send = function () {
        CarService.sendCar($scope.car).then(function (response) {
            $uibModalInstance.close(response.data);
        }, function (response) {
            alert('error');
        });
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss();
    };


});
