angular.module('wallboardApp').controller('MainPageController', function MainPageController($scope, StatusService, $routeParams) {

    $scope.projectName = $routeParams.projectName;
    $scope.projectDetail = $routeParams.projectDetail;

    $scope.update = function() {
        $scope.statuses = StatusService.getStatusOverview();
    }

    $scope.getStatusClass = function getStatusClass(status) {
        return status.toLowerCase()+'-status';
    }

});