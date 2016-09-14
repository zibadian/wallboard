angular.module('wallboardApp').controller('MainController', function MainPageController($scope, ConnectionService, $routeParams) {

    $scope.$on('update', function(event) {
        if (angular.isDefined($scope.updateFunction)) {
            $scope.updateFunction();
        }
    });

    $scope.updateOverview = function() {
        $scope.updateFunction = $scope.updateOverview;
        $scope.statuses = ConnectionService.retrieveData('/api/overview');
    }

    $scope.updateProject = function() {
        $scope.updateFunction = undefined;
        $scope.statuses = ConnectionService.retrieveData('/api/project/' + $routeParams.projectName);
    }

    $scope.getStatusClass = function(status) {
        return status.toLowerCase()+'-status';
    }

    $scope.getTemplate = function(name) {
        return 'partials/details/'+name;
    }
});