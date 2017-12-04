/**
 * 路由
 */
define(['appPractitioner'], function(app){
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

                /*health.healthService模块*/
                    .state('healthService', {
                        url: '/healthService/:firstMenu,:secondMenu',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'healthServiceCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.healthServiceCtrl',
                                    ['js/controllers/health/healthService/healthServiceCtrl.js'],
                                    'js/views/health/healthService/healthService.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('healthServicePackageTemplateList', {
                        url: '/healthServicePackageTemplateList',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'healthServicePackageTemplateListCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.healthServicePackageTemplateListCtrl',
                                    ['js/controllers/health/healthService/healthServicePackageTemplateListCtrl.js'],
                                    'js/views/health/healthService/healthServicePackageTemplateList.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('healthServicePackage', {
                        url: '/healthServicePackage/:servicePackageId,:operation',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'healthServicePackageCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.healthServicePackage',
                                    ['js/controllers/health/healthService/healthServicePackageCtrl.js'],
                                    'js/views/health/healthService/healthServicePackage.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('physicalExamination', {
                        url: '/physicalExamination/:physicalExaminationId',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'physicalExaminationCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.physicalExamination',
                                    ['js/controllers/health/healthService/physicalExaminationCtrl.js'],
                                    'js/views/health/healthService/physicalExamination.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('physicalExaminationTemplateList', {
                        url: '/physicalExaminationTemplateList',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'physicalExaminationTemplateListCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.physicalExaminationTemplateList',
                                    ['js/controllers/health/healthService/physicalExaminationTemplateListCtrl.js'],
                                    'js/views/health/healthService/physicalExaminationTemplateList.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('healthAssessmentTemplateList', {
                        url: '/healthAssessmentTemplateList',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'healthAssessmentTemplateListCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.healthAssessmentTemplateList',
                                    ['js/controllers/health/healthService/healthAssessmentTemplateListCtrl.js'],
                                    'js/views/health/healthService/healthAssessmentTemplateList.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('healthAssessmentResult', {
                        url: '/healthAssessmentResult/:existHealthAssessmentId,:keyId',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'healthAssessmentResultCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.healthAssessmentResult',
                                    ['js/controllers/health/healthService/healthAssessmentResultCtrl.js'],
                                    'js/views/health/healthService/healthAssessmentResult.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('healthAssessment', {
                        url: '/healthAssessment/:healthAssessmentTemplateId',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'healthAssessmentCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.healthAssessment',
                                    ['js/controllers/health/healthService/healthAssessmentCtrl.js'],
                                    'js/views/health/healthService/healthAssessment.html?ver='+practitionerVersion);
                            }
                        }
                    })

                /*health.detectionDiagnose模块*/
                    .state('detectionDiagnose', {
                        url: '/detectionDiagnose/:firstMenu,:secondMenu',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'detectionDiagnoseCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.detectionDiagnose',
                                    ['js/controllers/health/detectionDiagnose/detectionDiagnoseCtrl.js'],
                                    'js/views/health/detectionDiagnose/detectionDiagnose.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('testReportResult', {
                        url: '/testReportResult/:testDate,:testTime',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'testReportResultCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.testReportResult',
                                    ['js/controllers/health/detectionDiagnose/testReportResultCtrl.js?ver='+practitionerVersion ],
                                    'js/views/health/detectionDiagnose/testReportResult.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('diagnoseReportResult', {
                        url: '/diagnoseReportResult/:recordDate',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'diagnoseReportResultCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.diagnoseReportResult',
                                    ['js/controllers/health/detectionDiagnose/diagnoseReportResultCtrl.js?ver='+practitionerVersion ],
                                    'js/views/health/detectionDiagnose/diagnoseReportResult.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('bloodSugarRecord', {
                        url: '/bloodSugarRecord/:bloodSugarNum,:recorded,:timeType,:timeDate,:readOnly',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'bloodSugarRecordCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.bloodSugarRecord',
                                    ['js/controllers/health/detectionDiagnose/bloodSugarRecordCtrl.js?ver='+practitionerVersion ,
                                        'styles/lib/mobiscroll.custom-3.0.0-beta2.min.css','js/libs/mobiscroll.custom-3.0.0-beta2.min.js'],
                                    'js/views/health/detectionDiagnose/bloodSugarRecord.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('bloodPressureRecord', {
                        url: '/bloodPressureRecord/:emptyCont,:measureTime,:diastolic,:systolic,:heartRate,:readOnly',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'bloodPressureRecordCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.bloodPressureRecord',
                                    ['js/controllers/health/detectionDiagnose/bloodPressureRecordCtrl.js?ver='+practitionerVersion ,
                                        'styles/lib/mobiscroll.custom-3.0.0-beta2.min.css','js/libs/mobiscroll.custom-3.0.0-beta2.min.js'
                                    ],
                                    'js/views/health/detectionDiagnose/bloodPressureRecord.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('controlObjective', {
                        url: '/controlObjective/:objectiveType',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'controlObjectiveCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.controlObjective',
                                    ['js/controllers/health/detectionDiagnose/controlObjectiveCtrl.js?ver='+practitionerVersion],
                                    'js/views/health/detectionDiagnose/controlObjective.html?ver='+practitionerVersion);
                            }
                        }
                    })

                /*my.practitionerInfoSetting模块*/
                    .state('AboutUs', {
                        url: '/AboutUs',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'AboutUsCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.AboutUs',
                                    ['js/controllers/my/AboutUsCtrl.js'],
                                    'js/views/my/AboutUs.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('adviceFeedBack', {
                        url: '/adviceFeedBack',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'adviceFeedBackCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.adviceFeedBack',
                                    ['js/controllers/my/adviceFeedBackCtrl.js'],
                                    'js/views/my/adviceFeedBack.html?ver='+practitionerVersion);
                            }
                        }
                    })

                /*common模块 login*/
                    .state('login', {
                        url: '/login',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'loginCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.login',
                                    ['js/controllers/common/loginCtrl.js?ver='+practitionerVersion],
                                    'js/views/common/login.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('groupChat', {
                        url: '/groupChat/:messageType,:id',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'groupChatCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.groupChat',
                                    ['js/controllers/common/groupChatCtrl.js?ver='+practitionerVersion],
                                    'js/views/common/groupChat.html?ver='+practitionerVersion);
                            }
                        }
                    })


                /*health.interventionGuidance模块*/
                    .state('interventionGuidance', {
                        url: '/interventionGuidance/:firstMenu,:secondMenu,:recordDate',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'interventionGuidanceCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.interventionGuidance',
                                    ['js/controllers/health/interventionGuidance/interventionGuidanceCtrl.js'],
                                    'js/views/health/interventionGuidance/interventionGuidance.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('medicationPlan', {
                        url: '/medicationPlan/:pageType,:editable,:listId',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'medicationPlanCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.medicationPlan',
                                    ['js/controllers/health/interventionGuidance/medicationPlanCtrl.js',
                                        'styles/lib/mobiscroll.custom-3.0.0-beta2.min.css','js/libs/mobiscroll.custom-3.0.0-beta2.min.js'],
                                    'js/views/health/interventionGuidance/medicationPlan.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('medicationRemind', {
                        url: '/medicationRemind/:remindId',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'medicationRemindCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.medicationRemind',
                                    ['js/controllers/health/interventionGuidance/medicationRemindCtrl.js'],
                                    'js/views/health/interventionGuidance/medicationRemind.html?ver='+practitionerVersion);
                            }
                        }
                    })
                    .state('mealRecordResult', {
                        url: '/mealRecordResult/:date/:time',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'mealRecordResultCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.mealRecordResult',
                                    ['js/controllers/health/interventionGuidance/mealRecordResultCtrl.js'],
                                    'js/views/health/interventionGuidance/mealRecordResult.html?ver='+practitionerVersion);
                            }
                        }
                    })





                    /*member模块*/
                    .state('memberInfo', {
                        url: '/memberInfo',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'memberInfoCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.memberInfo',
                                    ['js/controllers/member/memberInfoCtrl.js'],
                                    'js/views/member/memberInfo.html?ver='+practitionerVersion);
                            }
                        }
                    })


                $urlRouterProvider.otherwise('/healthService/healthServicePackage,')
            }])
})