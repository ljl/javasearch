var searchApp = angular.module('searchApp', ['ngRoute', 'searchControllers']);

searchApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/search', {
                templateUrl: 'resources/views/search.html',
                controller: 'SearchCtrl',
                controllerAs: 'search'
            }).
            when('/admin', {
                templateUrl: 'resources/views/admin.html',
                controller: 'AdminCtrl'
            }).
            otherwise({
                redirectTo: '/search'
            });
    }]);