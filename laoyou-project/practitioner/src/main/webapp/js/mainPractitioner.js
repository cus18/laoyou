/**
 * 入口文件
 * 2014-11-30 mon
 */
require.config({
    baseUrl: "js/",
    paths: {
        "angular" : "libs/angular.min",
        "angular-resource" : "libs/angular-resource.min",
        "angular-sanitize" : "libs/angular-sanitize.min",
        "angular-ui-router" : "libs/angular-ui-router.min",
        "angular-locale_zh-cn": "libs/i18n/angular-locale_zh-cn",
        "ocLazyLoad":"libs/ocLazyLoad.require.min",
        "jquery":"libs/jquery-2.1.3.min",
        "highcharts":"libs/highcharts",
        "highcharts-ng":"libs/highcharts-ng",
        "ng-infinite-scroll":"libs/ng-infinite-scroll.min",
        "moment":'libs/moment.min',
        "fullCalendar" : "libs/fullcalendar",
        "calendar" : "libs/calendar",
        "gcal" : "libs/gcal",
        "uiBootstrapTpls" : "libs/ui-bootstrap-tpls-0.9.0",
        "appPractitionerInit" : "init/appPractitionerInit",
        "practitionerFactory" : "services/practitionerFactory",
        "practitionerGlobal" : "services/practitionerGlobal",
        "practitionerDirective" : "directives/practitionerDirective",
        "practitionerRoute" : "routes/appPractitionerRoute",
        "appPractitioner" : "modules/appPractitioner",
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
        'calendar': ['angular'],
        'uiBootstrapTpls': ['angular'],
        'highcharts': {
            deps: ['angular'],   //依赖什么模块
            exports: 'highcharts'
        },
        'highcharts-ng': {
            deps: ['highcharts'],   //依赖什么模块
        },
        'practitionerGlobal': ['angular'],
        'app':['ocLazyLoad'],
    }
});

require(['angular','angular-resource','angular-sanitize','angular-ui-router','angular-locale_zh-cn',
        'ocLazyLoad', 'jquery', 'highcharts','highcharts-ng','ng-infinite-scroll','moment','fullCalendar',
        'calendar', 'gcal','uiBootstrapTpls','appPractitionerInit', 'practitionerFactory', 'practitionerGlobal',
        'practitionerDirective', 'practitionerRoute','appPractitioner'],
    function (angular){
        angular.bootstrap(document,["practitionerApp"]);
    });

