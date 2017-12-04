/**
 * 入口文件
 * 2014-11-30 mon
 */
require.config({
    baseUrl: "js/",
    paths: {
        "angular" : "libs/angular/angular.min",
        "angular-resource" : "libs/angular/angular-resource.min",
        "angular-sanitize" : "libs/angular/angular-sanitize.min",
        "angular-ui-router" : "libs/angular/angular-ui-router.min",
        "angular-locale_zh-cn": "libs/angular/angular-locale_zh-cn",
        "ocLazyLoad":"libs/important/ocLazyLoad.require.min",
        "jquery":"libs/jquery/jquery-2.1.3.min",
        "highcharts":"libs/tool/highcharts",
        "highcharts-ng":"libs/tool/highcharts-ng",
        "ng-infinite-scroll":"libs/tool/ng-infinite-scroll.min",
        "moment":'libs/tool/moment.min',
        "fullCalendar" : "libs/tool/fullcalendar",
        "calendar" : "libs/tool/calendar",
        "gcal" : "libs/tool/gcal",
        "uiBootstrapTpls" : "libs/tool/ui-bootstrap-tpls-0.9.0",
        "elderFactory" : "services/elderFactory",
        "elderGlobal" : "services/elderGlobal",
        "surveyGlobal" : "services/surveyGlobal",
        "elderDirective" : "directives/elderDirective",
        "elderRoute" : "routes/appElderRoute",
        "appElder" : "modules/appElder"
    },
    waitSeconds: 0,
    shim: {
        'moment' : ['jquery'],
        'fullCalendar' : ['moment'],
        'gcal':['fullCalendar'],
        'angular': {
            deps: ["gcal"],
            exports: 'angular'
        },
        'calendar': {
            deps:['angular']
        },
        'angular-resource':{
            deps: ["angular"],
            exports: 'angular-resource'
        },
        'angular-ui-router':{
            deps: ['angular'],   //依赖什么模块
            exports: 'angular-route'
        },
        'angular-sanitize':{
            deps: ['angular'],   //依赖什么模块
            exports: 'angular-sanitize'
        },
        'angular-locale_zh-cn':{
            deps: ['angular'],
        },
        'ocLazyLoad': ['angular'],
        'ng-infinite-scroll': ['angular'],
        'uiBootstrapTpls': ['angular'],
        'highcharts': {
            deps: ['angular'],   //依赖什么模块
            exports: 'highcharts'
        },
        'highcharts-ng': {
            deps: ['highcharts'],   //依赖什么模块
        },
        'elderGlobal': ['angular'],
        'surveyGlobal': ['angular'],
        'app':['ocLazyLoad'],
    }
});

require(['angular','angular-resource','angular-sanitize','angular-ui-router','angular-locale_zh-cn',
        'ocLazyLoad', 'jquery', 'highcharts','highcharts-ng','ng-infinite-scroll',
        'moment','fullCalendar', 'calendar', 'gcal',
        'uiBootstrapTpls','elderFactory', 'elderGlobal','surveyGlobal',
        'elderDirective', 'elderRoute','appElder'],
    function (angular){
        angular.bootstrap(document,["elderApp"]);
    });

