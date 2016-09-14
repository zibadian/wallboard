angular.module('wallboardApp').controller('TopController', function TopController($scope, $interval, ConnectionService, $rootScope) {

    var timer;

    $scope.start = function() {
        $rootScope.$broadcast('update');
        if ( angular.isDefined(timer) ) {
            return;
        }
        timer = $interval(function() {
            $rootScope.$broadcast('update');
        }, 5000);
    }

    $scope.$on('update', function(event) {
        $scope.topData = ConnectionService.retrieveData('/api/top');
    });

    $scope.getStatusClass = function getStatusClass(status) {
        return status.toLowerCase()+'-status';
    }

    $scope.stopTimer = function() {
        if (angular.isDefined(timer)) {
            $interval.cancel(timer);
            timer = undefined;
        }
    }

    $scope.$on('$destroy', function() {
        // Make sure that the interval is destroyed too
        $scope.stopTimer();
    });

});