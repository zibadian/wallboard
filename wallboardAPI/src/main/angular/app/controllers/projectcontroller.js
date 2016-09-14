angular.module('wallboardApp').controller('ProjectController', function ProjectController($scope, StatusService, $routeParams) {

    $scope.update = function() {
        $scope.projectDetails = StatusService.getProjectDetails($routeParams.projectName);
    }

    $scope.getStatusClass = function getStatusClass(status) {
        return status.toLowerCase() + '-status';
    }

    $scope.getTemplate = function getTemplate(template) {
        return 'partials/details/' + template;
    }
});