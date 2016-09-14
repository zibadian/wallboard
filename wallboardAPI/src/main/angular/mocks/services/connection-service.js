angular.module('wallboardApp').service('ConnectionService', function($http, $q, $timeout, $location, dateFilter) {

    this.retrieveData = function(address) {
        console.log(address);
        switch (address) {
            case '/api/top':
                result = {
                    "statuses": [
                        {"name":"Test Application","status":"GREEN","version":"1.0.1","url":"#/testproject"},
                        {"name":"Test Tooling","status":"YELLOW","version":"1.0.2","url":"#/testtooling"},
                        {"name":"Error Application","status":"RED","version":"0.7.1","url":"#/errorproject"}
                    ]};
                result.time = formatTime(result.time);
                return result;
            case '/api/overview':
                result = [
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
                result[0].name = new Date();
                return result;
            case '/api/project/testproject':
                return {"name":"Test Application","status":"GREEN","version":"1.0.1","url":"#/testproject","partials":[
                    {"name":"build","status":"GREEN","url":"#/testproject/build","version":"1.0.3-SNAPSHOT","template":"build.html"},
                    {"name":"check","status":"YELLOW","url":"#/testproject/sonar","version":"1.0.3-SNAPSHOT","template":"sonar.html"},
                    {"name":"test","status":"GREEN","url":"#/testproject/test","version":"1.0.1","template":"_server.html"},
                    {"name":"accp","status":"UNKNOWN","url":"#/testproject/accp","version":"Unknown","template":"_server.html"},
                    {"name":"prod","status":"UNKNOWN","url":"#/testproject/prod","version":"Unknown","template":"_server.html"}
                ]};
            default:
                console.log("Unknown url");
        }
    }

    function formatTime() {
        return dateFilter(new Date(), 'yyyy-MM-dd HH:mm:ss')
    }

});