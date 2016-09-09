angular.module('wallboardApp', []).controller('WallboardController', function WallboardController($scope) {
  $scope.mainStatuses = [
    {"name":"Test Application","status":"GREEN","version":"1.0.1","url":"/testproject","partials":[
        {"name":"build","status":"GREEN","url":"/testproject/build"},
        {"name":"check","status":"YELLOW","url":"/testproject/sonar"},
        {"name":"test","status":"GREEN","url":"/testproject/test"},
        {"name":"accp","status":"UNKNOWN","url":"/testproject/accp"},
        {"name":"prod","status":"UNKNOWN","url":"/testproject/prod"}
    ]},
    {"name":"Test Tooling","status":"YELLOW","version":"1.0.1","url":"/testproject","partials":[
        {"name":"build","status":"GREEN","url":"/testproject/build"},
        {"name":"check","status":"RED","url":"/testproject/sonar"},
        {"name":"test","status":"GREEN","url":"/testproject/test"},
        {"name":"accp","status":"GREEN","url":"/testproject/accp"}
    ]},
    {"name":"Error","status":"RED","version":"1.0.1","url":"/testproject","partials":[
        {"name":"build","status":"GREEN","url":"/testproject/build"},
        {"name":"check","status":"GREEN","url":"/testproject/sonar"},
        {"name":"test","status":"GREEN","url":"/testproject/test"},
        {"name":"accp","status":"GREEN","url":"/testproject/accp"},
        {"name":"prod","status":"RED","url":"/testproject/prod"}
    ]}
  ]

  $scope.getStatusClass = function getStatusClass(status) {
    return status.toLowerCase()+'-status';
  }

});