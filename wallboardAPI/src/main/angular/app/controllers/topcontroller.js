angular.module('wallboardApp').controller('TopController', function TopController($scope, StatusService) {

    $scope.update = function() {
        $scope.statuses = StatusService.getStatusLinks();
    }

    $scope.getStatusClass = function getStatusClass(status) {
        return status.toLowerCase()+'-status';
    }

});