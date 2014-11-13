var searchControllers = angular.module('searchControllers', ['searchServices']);

searchControllers.controller('SearchCtrl', SearchController);

SearchController.$inject = ['Remote'];

function SearchController(Remote) {
    var vm = this;
    vm.query = "";
    vm.search = doSearch;

    function doSearch() {
        Remote.doSearch(vm.query, searchSuccess, searchFail);

    }

    function searchSuccess(data) {
        console.log(data);
        vm.hits = data.hits.hits;
        vm.time = data.took;
        vm.total = data.hits.total;
    }

    function searchFail() {
        vm.hits = null;
        vm.time = null;
        vm.total = null;
    }
}

searchControllers.directive('mySearchHit', function() {
   return {
       templateUrl: 'resources/views/searchHit.html',
       restrict: 'E',
       scope: {
           hit: '='
       }
   }
});

searchControllers.controller('AdminCtrl', ['$scope', '$location', 'Remote', function ($scope, $location, Remote) {
    $scope.response = {};
    $scope.submit = function () {
        Remote.startIndex($scope.startUrl, function(data, status, headers, config) {
            $scope.response = data;
            $location.path("/search");
        });
    }
}]);

searchControllers.directive('mySearchStats', function() {
    return {
        templateUrl: 'resources/views/searchStats.html',
        restrict: 'E'
    }
});