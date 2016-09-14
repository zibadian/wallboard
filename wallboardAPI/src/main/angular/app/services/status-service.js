angular.module('wallboardApp').service('StatusService', function(ConnectionService) {

    this.getStatusLinks = function() {
        return [
            {"name":"Test Application","status":"GREEN","version":"1.0.1","url":"#/testproject"},
            {"name":"Test Tooling","status":"YELLOW","version":"1.0.2","url":"#/testtooling"},
            {"name":"Error Application","status":"RED","version":"0.7.1","url":"#/errorproject"}
        ];
    }

    this.getStatusOverview = function() {
        return [
            {"name":"Test Application","status":"GREEN","version":"1.0.1","url":"#/testproject","partials":[
                {"name":"build","status":"GREEN","url":"#/testproject/build"},
                {"name":"check","status":"YELLOW","url":"#/testproject/sonar"},
                {"name":"test","status":"GREEN","url":"#/testproject/test"},
                {"name":"accp","status":"UNKNOWN","url":"#/testproject/accp"},
                {"name":"prod","status":"UNKNOWN","url":"#/testproject/prod"}
            ]},
                {"name":"Test Tooling","status":"YELLOW","version":"1.0.1","url":"#/testtooling","partials":[
                {"name":"build","status":"GREEN","url":"#/testtooling/build"},
                {"name":"check","status":"RED","url":"#/testtooling/sonar"},
                {"name":"test","status":"GREEN","url":"#/testtooling/test"},
                {"name":"accp","status":"GREEN","url":"#/testtooling/accp"}
            ]},
                {"name":"Error","status":"RED","version":"1.0.1","url":"#/errorproject","partials":[
                {"name":"build","status":"GREEN","url":"#/errorproject/build"},
                {"name":"check","status":"GREEN","url":"#/errorproject/sonar"},
                {"name":"test","status":"GREEN","url":"#/errorproject/test"},
                {"name":"accp","status":"GREEN","url":"#/errorproject/accp"},
                {"name":"prod","status":"RED","url":"#/errorproject/prod"}
            ]}
        ];
    };

    this.getProjectDetails = function(name) {
        return {"name":"Test Application","status":"GREEN","version":"1.0.1","url":"#/testproject","partials":[
            {"name":"build","status":"GREEN","url":"#/testproject/build","version":"1.0.3-SNAPSHOT","template":"build.html"},
            {"name":"check","status":"YELLOW","url":"#/testproject/sonar","version":"1.0.3-SNAPSHOT","template":"sonar.html"},
            {"name":"test","status":"GREEN","url":"#/testproject/test","version":"1.0.1","template":"_server.html"},
            {"name":"accp","status":"UNKNOWN","url":"#/testproject/accp","version":"Unknown","template":"_server.html"},
            {"name":"prod","status":"UNKNOWN","url":"#/testproject/prod","version":"Unknown","template":"_server.html"}
        ]};
    };

});