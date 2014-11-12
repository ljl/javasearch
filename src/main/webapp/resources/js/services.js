var searchServices = angular.module('searchServices', []);

searchServices.factory('Remote', ['$http',
    function ($http) {
        return {
            doSearch: function (query, callback, error) {
                $http.get('/rest/search/' + query).success(callback).error(error);
            },
            testIndex: function (startUrl, callback) {
                $http.post(
                    '/rest/index/test',
                    $.param({
                        value: startUrl
                    }), {
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    }).success(callback);
            },
            startIndex: function (startUrl, callback) {
                $http.post(
                    '/rest/index/start',
                    $.param({
                        startUrl: startUrl
                    }), {
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    }).success(callback);
            }
        }
    }]);