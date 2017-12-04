/**
 * 建立angular.module
 */

define(['angular'], function (angular) {
    var app = angular.module('elderApp',['ngResource','ui.router','ngSanitize','ionic',
        'oc.lazyLoad','highcharts-ng','infinite-scroll','elderGlobal','ionic-datepicker','surveyGlobal'])
        .config(['$httpProvider',function($httpProvider,$rootScope) {

            $httpProvider.defaults.headers.common = {'logintoken':window.localStorage.getItem("loginToken")};
            if($httpProvider.defaults.headers.common.logintoken==null)
            {
                connectWebViewJavascriptBridge(function(config) {
                    window.WebViewJavascriptBridge.callHandler(
                        'getTokenInfo','',function(responseData) {
                            $httpProvider.defaults.headers.common = {'logintoken':responseData }
                        }
                    );
                });
            }

            $httpProvider.interceptors.push(function($rootScope){
                return {
                    request: function(config){
                        config.headers = config.headers || {};
                        if($rootScope.loginToken!=undefined){
                            config.headers.logintoken = $rootScope.loginToken;
                        }
                        return config;
                    }
                }
            });
        }])
        .run(function($rootScope){
            $rootScope.returnRootNative = function(){
                connectWebViewJavascriptBridge(function() {
                    window.WebViewJavascriptBridge.callHandler('returnNative','',function(responseData) {});});
            }
        })
    return app;
});