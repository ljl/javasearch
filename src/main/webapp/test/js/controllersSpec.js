'use strict';

describe('Search controllers', function () {

    beforeEach(module('searchApp'));
    beforeEach(module('searchServices'));

    describe('SearchCtrl', function () {
        var scope, ctrl, $httpBackend;

        beforeEach(inject(function (_$httpBackend_, $rootScope, $controller) {
            $httpBackend = _$httpBackend_;
            $httpBackend.expectGET('/rest/search/test').
                respond({
                    "hits": {
                        "hits": [
                            {
                                "_source": {
                                    "path": "/digital/tester/",
                                    "title": "Tester - Aftenposten",
                                    "url": "http://www.aftenposten.no/digital/tester/"
                                }
                            },
                            {
                                "_source": {
                                    "path": "/rofl/",
                                    "title": "Rofl - Roflesen",
                                    "url": "http://www.rofl.no/rofl/"
                                }
                            }
                        ],
                        "total": 2
                    },
                    "took": 7
                });

            scope = $rootScope.$new();
            ctrl = $controller('SearchCtrl');
            ctrl.query = "test";
            ctrl.search();
        }));


        it('should get 2 search results', function () {
            expect(ctrl.total).toEqual(null);
            $httpBackend.flush();
            expect(ctrl.total).toEqual(2);
        });

    });

    describe('AdminCtrl', function () {
        var scope, $httpBackend, location, ctrl,
            indexData = function() {
                return {
                    message: "Started indexing of site http://itavisen.no",
                    status: "OK"
                }
            };


        beforeEach(inject(function(_$httpBackend_, $rootScope, $location, $routeParams, $controller) {
            $httpBackend = _$httpBackend_;
            $httpBackend.expectPOST('/rest/index/start').respond(indexData());

            scope = $rootScope.$new();
            location = $location;
            ctrl = $controller('AdminCtrl', {$scope: scope, $location: location});
            scope.startUrl = "http://itavisen.no";
            scope.submit();
        }));

        it('should return OK message', function () {
            $httpBackend.flush();
            expect(scope.response.status).toEqual("OK");
        });

        it('should redirect to search page', function() {
            $httpBackend.flush();
            expect(location.path()).toBe("/search");
        })
    });
});
