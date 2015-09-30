assert = require("assert");

beforeEach(function() {
    console.log('before');
});

afterEach(function() {
    console.log('after');
});

describe('Array', function(){

    describe('#indexOf()', function(){

        it('should return -1 when the value is not present', function(){
            assert.equal(-1, [1,2,3].indexOf(5));
            assert.equal(0, [1,2,3].indexOf(1));
        })

    })

});
