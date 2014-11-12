var searchControllers = angular.module('searchControllers', ['searchServices']);

searchControllers.controller('SearchCtrl', ['$scope', '$http', 'Remote', function ($scope, $http, Remote) {
    $scope.search = function () {
        Remote.doSearch($scope.query, function (data, status, headers, config) {
            $scope.hits = data.hits.hits;
            $scope.time = data.took;
            $scope.total = data.hits.total;
        }, function(data, status, headers, config) {
            $scope.hits = null;
            $scope.time = null;
            $scope.total = null;
        });
    };

}]);

searchControllers.controller('AdminCtrl', ['$scope', '$http', '$location', 'Remote', function ($scope, $http, $location, Remote) {
    $scope.submit = function () {
        console.log($scope.startUrl);
        Remote.startIndex($scope.startUrl, function(data, status, headers, config) {
            console.log(arguments);
            $location.path("/search");
        });
    }
}]);