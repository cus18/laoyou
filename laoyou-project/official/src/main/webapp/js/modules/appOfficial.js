/**
 * 建立angular.module
 */

define(['angular'], function (angular) {
    var app = angular.module('officialApp',['ngResource','ui.router','ngSanitize',
        'oc.lazyLoad','highcharts-ng','infinite-scroll','officialGlobal'])
        .config(['$httpProvider',function($httpProvider,$rootScope) {

            $httpProvider.interceptors.push(function($rootScope){
                return {
                    request: function(config){
                        config.headers = config.headers || {};
                        if($rootScope.loginToken!=undefined){
                            config.headers.logintoken = $rootScope.loginToken;
                        }
                        return config;
                    }
                }})

        }])
    return app;
});