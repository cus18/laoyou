/**
 * 建立angular.module
 */

// 'highcharts-ng',
define(['angular'], function (angular) {
    var app = angular.module('practitionerApp',['ngResource','ui.router','ngSanitize',
        'oc.lazyLoad','highcharts-ng','infinite-scroll','practitionerGlobal'])
        .config(['$httpProvider',function($httpProvider,$rootScope) {

            connectWebViewJavascriptBridge(function(config) {
                window.WebViewJavascriptBridge.callHandler(
                    'getTokenInfo','',function(responseData) {
                        $httpProvider.defaults.headers.common = { 'logintoken':responseData }
                    }
                );
            });

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