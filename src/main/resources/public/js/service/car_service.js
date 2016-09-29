// factory
angular.module('carApp').factory('CarService', function ($http) {

    var oneCar = function (id) {
        return $http.get('car/' + id);
    };

    var allCars = function () {
        return $http.get("car");
    };

    var sendCar = function (data) {
        if (data.id) {
            return $http.put('car', data);
        } else {
            return $http.post('car', data);
        }
    };

    var deleteCar = function (id) {
        return $http.delete('car/' + id);
    };

    return {
        oneCar: oneCar,
        allCars: allCars,
        sendCar: sendCar,
        deleteCar: deleteCar
    };
});

// service
//angular.module('carApp').service('CarService', function ($http) {
//    this.allCars = function () {
//        return $http.get("car");
//    };
//    this.sendCar = function (data) {
//        return $http.post('car', data);
//    };
//    this.deleteCar = function (id) {
//        return $http.delete('car/' + id);
//    };
//});

// provider example TODO