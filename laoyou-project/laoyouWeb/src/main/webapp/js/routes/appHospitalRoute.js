/**
 * 路由
 */
define(['appHospital'], function(app){
    return app
        .config(['$stateProvider','$urlRouterProvider',
            function($stateProvider,$urlRouterProvider) {
                var loadFunction = function($templateCache, $ocLazyLoad, $q, $http,name,files,htmlURL){
                    lazyDeferred = $q.defer();
                    return $ocLazyLoad.load ({
                        name: name,
                        files: files
                    }).then(function() {
                        return $http.get(htmlURL)
                            .success(function(data, status, headers, config) {
                                return lazyDeferred.resolve(data);
                            }).
                            error(function(data, status, headers, config) {
                                return lazyDeferred.resolve(data);
                            });
                    });
                };

                $stateProvider

                    .state('login', {
                        url: '/login',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'loginCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/loginCtrl.js?ver='+hospitalVersion],
                                    'js/views/login.html?ver='+hospitalVersion);
                            }
                        }
                    })

                    .state('index', {
                        url: '/index',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'indexCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/indexCtrl.js?ver='+hospitalVersion],
                                    'js/views/index.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('member', {
                        url: '/member',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'memberCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/memberCtrl.js?ver='+hospitalVersion],
                                    'js/views/member.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('editMember', {
                        url: '/editMember/:type,:elderId',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'editMemberCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/editMemberCtrl.js?ver='+hospitalVersion],
                                    'js/views/editMember.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('uploadMultiMember', {
                        url: '/uploadMultiMember',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'uploadMultiMemberCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/uploadMultiMemberCtrl.js?ver='+hospitalVersion],
                                    'js/views/uploadMultiMember.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('memberDetail', {
                        url: '/memberDetail/:menu,:elderId',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'memberDetailCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/memberDetailCtrl.js?ver='+hospitalVersion],
                                    'js/views/memberDetail.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('device', {
                        url: '/device',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'deviceCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/deviceCtrl.js?ver='+hospitalVersion],
                                    'js/views/device.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('practitioner', {
                        url: '/practitioner',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'practitionerCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/practitionerCtrl.js?ver='+hospitalVersion],
                                    'js/views/practitioner.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('uploadMultiPractitioner', {
                        url: '/uploadMultiPractitioner',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'uploadMultiPractitionerCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/uploadMultiPractitionerCtrl.js?ver='+hospitalVersion],
                                    'js/views/uploadMultiPractitioner.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('product', {
                        url: '/product',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'productCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/productCtrl.js?ver='+hospitalVersion],
                                    'js/views/product.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('setting', {
                        url: '/setting',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'settingCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/settingCtrl.js?ver='+hospitalVersion],
                                    'js/views/setting.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('question', {
                        url: '/question/:type',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'questionCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/questionCtrl.js?ver='+hospitalVersion],
                                    'js/views/question.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('activeAllList', {
                        url: '/activeAllList',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'activeAllListCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/activeAllListCtrl.js?ver='+hospitalVersion],
                                    'js/views/activeAllList.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('activeList', {
                        url: '/activeList',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'activeListCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/activeListCtrl.js?ver='+hospitalVersion],
                                    'js/views/activeList.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('activeDetail', {
                        url: '/activeDetail/:type,:activityId',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'activeDetailCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/activeDetailCtrl.js?ver='+hospitalVersion],
                                    'js/views/activeDetail.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('activeStatistics', {
                        url: '/activeStatistics',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'activeStatisticsCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/activeStatisticsCtrl.js?ver='+hospitalVersion],
                                    'js/views/activeStatistics.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('video', {
                        url: '/video/:type',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'videoCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/videoCtrl.js?ver='+hospitalVersion],
                                    'js/views/video.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('videoDetail', {
                        url: '/videoDetail/:type',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'videoDetailCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/videoDetailCtrl.js?ver='+hospitalVersion],
                                    'js/views/videoDetail.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('limitsSetting', {
                        url: '/limitsSetting',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'limitsSettingCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/limitsSettingCtrl.js?ver='+hospitalVersion,'js/libs/area.js'],
                                    'js/views/limitsSetting.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('limitsUsers', {
                        url: '/limitsUsers',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'limitsUsersCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/limitsUsersCtrl.js?ver='+hospitalVersion],
                                    'js/views/limitsUsers.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('cmsPicture', {
                        url: '/cmsPicture',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'cmsPictureCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/cmsPictureCtrl.js?ver='+hospitalVersion],
                                    'js/views/cmsPicture.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('cmsTemplate', {
                        url: '/cmsTemplate',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'cmsTemplateCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/cmsTemplateCtrl.js?ver='+hospitalVersion],
                                    'js/views/cmsTemplate.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('cmsSend', {
                        url: '/cmsSend',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'cmsSendCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/cmsSendCtrl.js?ver='+hospitalVersion],
                                    'js/views/cmsSend.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('cmsHistory', {
                        url: '/cmsHistory',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'cmsHistoryCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/cmsHistoryCtrl.js?ver='+hospitalVersion],
                                    'js/views/cmsHistory.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('cmsPictureSoldOut', {
                        url: '/cmsPictureSoldOut/:id',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'cmsPictureSoldOutCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/cmsPictureSoldOutCtrl.js?ver='+hospitalVersion],
                                    'js/views/cmsPictureSoldOut.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('cmsPicturePutAway', {
                        url: '/cmsPicturePutAway/:id,:app,:name,:nums',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'cmsPicturePutAwayCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/cmsPicturePutAwayCtrl.js?ver='+hospitalVersion],
                                    'js/views/cmsPicturePutAway.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('livingAllOrder', {
                        url: '/livingAllOrder',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'livingAllOrderCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/livingAllOrderCtrl.js?ver='+hospitalVersion],
                                    'js/views/livingAllOrder.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('livingAllService', {
                        url: '/livingAllService',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'livingAllServiceCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/livingAllServiceCtrl.js?ver='+hospitalVersion],
                                    'js/views/livingAllService.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('livingOrderList', {
                        url: '/livingOrderList',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'livingOrderListCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/livingOrderListCtrl.js?ver='+hospitalVersion],
                                    'js/views/livingOrderList.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('livingServiceList', {
                        url: '/livingServiceList',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'livingServiceListCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/livingServiceListCtrl.js?ver='+hospitalVersion],
                                    'js/views/livingServiceList.html?ver='+hospitalVersion);
                            }
                        }
                    })



                    /*问卷后台*/
                    .state('surveyLogin', {
                        url: '/surveyLogin',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'surveyLoginCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.surveyLogin',
                                    ['js/controllers/survey/surveyLoginCtrl.js?ver='+hospitalVersion],
                                    'js/views/survey/surveyLogin.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('singleStatistic', {
                        url: '/singleStatistic',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'singleStatisticCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.singleStatistic',
                                    ['js/controllers/survey/singleStatisticCtrl.js?ver='+hospitalVersion],
                                    'js/views/survey/singleStatistic.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('crossStatistic', {
                        url: '/crossStatistic',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'crossStatisticCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.crossStatistic',
                                    ['js/controllers/survey/crossStatisticCtrl.js?ver='+hospitalVersion],
                                    'js/views/survey/crossStatistic.html?ver='+hospitalVersion);
                            }
                        }
                    })
                    .state('diyStatistic', {
                        url: '/diyStatistic',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'diyStatisticCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.diyStatistic',
                                    ['js/controllers/survey/diyStatisticCtrl.js?ver='+hospitalVersion],
                                    'js/views/survey/diyStatistic.html?ver='+hospitalVersion);
                            }
                        }
                    })

                $urlRouterProvider.otherwise('/index')
                //$urlRouterProvider.otherwise('/question/basicInfo')
            }])
})