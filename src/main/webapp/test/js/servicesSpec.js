'use strict';

describe('service', function() {

    beforeEach(module('searchApp'));

    it('check the existence of Remote factory', inject(function(Remote) {
        expect(Remote).toBeDefined();
    }));
});