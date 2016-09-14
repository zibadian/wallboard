angular.module('wallboardApp').controller('MainController', function MainPageController($scope, ConnectionService, $routeParams) {

    $scope.updateOverview = function() {
        $scope.statuses = ConnectionService.retrieveData('/api/overview');
    }

    $scope.updateProject = function() {
        $scope.statuses = ConnectionService.retrieveData('/api/project/' + $routeParams.projectName);
    }

    $scope.getStatusClass = function(status) {
        return status.toLowerCase()+'-status';
    }

    $scope.getTemplate = function(name) {
        return 'partials/details/'+name;
    }
});