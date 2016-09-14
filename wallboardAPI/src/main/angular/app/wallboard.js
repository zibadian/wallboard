var wallboardApp = angular.module('wallboardApp', ["ngRoute"]);

wallboardApp.config(function($routeProvider) {
    $routeProvider.when("/", {
        templateUrl : "partials/main.html"
    })
    .when("/:projectName", {
        templateUrl: "partials/project.html",
    })
});