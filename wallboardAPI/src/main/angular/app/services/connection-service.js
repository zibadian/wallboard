angular.module('wallboardApp').service('ConnectionService', function($http, $q, $timeout) {

    this.getData = function(address) {
        var deferred = $q.defer();
        config.timeout = deferred.promise;

        $http.get(address, config)
            .then(function(response){
                return response;
            },function(reject){
                if (reject == 0) {
                    return null;
                } else {
                    return null;
                }
            });

        $timeout(function() {
            deferred.resolve();
        }, 5000);
    }

});