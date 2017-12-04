/**
 * 路由
 */
define(['appElder'], function(app){
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
                    .state('healthServiceList', {
                        url: '/healthServiceList',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'healthServiceListCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.healthServiceListCtrl',
                                    ['js/controllers/health/healthServiceListCtrl.js'],
                                    'js/views/health/healthServiceList.html?ver='+elderVersion);
                            }
                        }
                    })
                    .state('healthService', {
                        url: '/healthService/:firstMenu,:secondMenu',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'healthServiceCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.healthServiceCtrl',
                                    ['js/controllers/health/healthService/healthServiceCtrl.js'],
                                    'js/views/health/healthService/healthService.html?ver='+elderVersion);
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
                                    'js/views/health/healthService/healthServicePackageTemplateList.html?ver='+elderVersion);
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
                                    'js/views/health/healthService/healthServicePackage.html?ver='+elderVersion);
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
                                    'js/views/health/healthService/physicalExamination.html?ver='+elderVersion);
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
                                    'js/views/health/healthService/physicalExaminationTemplateList.html?ver='+elderVersion);
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
                                    'js/views/health/healthService/healthAssessmentTemplateList.html?ver='+elderVersion);
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
                                    'js/views/health/healthService/healthAssessmentResult.html?ver='+elderVersion);
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
                                    'js/views/health/detectionDiagnose/detectionDiagnose.html?ver='+elderVersion);
                            }
                        }
                    })
                    .state('testReport', {
                        url: '/testReport',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'testReportCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.testReport',
                                    ['js/controllers/health/detectionDiagnose/testReportCtrl.js'],
                                    'js/views/health/detectionDiagnose/testReport.html?ver='+elderVersion);
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
                                    ['js/controllers/health/detectionDiagnose/testReportResultCtrl.js?ver='+elderVersion ],
                                    'js/views/health/detectionDiagnose/testReportResult.html?ver='+elderVersion);
                            }
                        }
                    })
                    .state('diagnoseReport', {
                        url: '/diagnoseReport',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'diagnoseReportCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.diagnoseReport',
                                    ['js/controllers/health/detectionDiagnose/diagnoseReportCtrl.js?ver='+elderVersion ],
                                    'js/views/health/detectionDiagnose/diagnoseReport.html?ver='+elderVersion);
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
                                    ['js/controllers/health/detectionDiagnose/diagnoseReportResultCtrl.js?ver='+elderVersion ],
                                    'js/views/health/detectionDiagnose/diagnoseReportResult.html?ver='+elderVersion);
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
                                    ['js/controllers/health/detectionDiagnose/bloodSugarRecordCtrl.js?ver='+elderVersion ,
                                        'styles/lib/mobiscroll.custom-3.0.0-beta2.min.css',
                                        'js/libs/tool/mobiscroll.custom-3.0.0-beta2.min.js'],
                                    'js/views/health/detectionDiagnose/bloodSugarRecord.html?ver='+elderVersion);
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
                                    ['js/controllers/health/detectionDiagnose/bloodPressureRecordCtrl.js?ver='+elderVersion ,
                                        'styles/lib/mobiscroll.custom-3.0.0-beta2.min.css',
                                        'js/libs/tool/mobiscroll.custom-3.0.0-beta2.min.js'
                                    ],
                                    'js/views/health/detectionDiagnose/bloodPressureRecord.html?ver='+elderVersion);
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
                                    ['js/controllers/health/detectionDiagnose/controlObjectiveCtrl.js?ver='+elderVersion],
                                    'js/views/health/detectionDiagnose/controlObjective.html?ver='+elderVersion);
                            }
                        }
                    })

                    /*my.elderInfoSetting模块*/
                    .state('AboutUs', {
                        url: '/AboutUs',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'AboutUsCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.AboutUs',
                                    ['js/controllers/my/AboutUsCtrl.js'],
                                    'js/views/my/AboutUs.html?ver='+elderVersion);
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
                                    'js/views/my/adviceFeedBack.html?ver='+elderVersion);
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
                                    ['js/controllers/common/loginCtrl.js?ver='+elderVersion],
                                    'js/views/common/login.html?ver='+elderVersion);
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
                                    ['js/controllers/common/groupChatCtrl.js?ver='+elderVersion],
                                    'js/views/common/groupChat.html?ver='+elderVersion);
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
                                    'js/views/health/interventionGuidance/interventionGuidance.html?ver='+elderVersion);
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
                                    'js/views/health/interventionGuidance/medicationPlan.html?ver='+elderVersion);
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
                                    'js/views/health/interventionGuidance/medicationRemind.html?ver='+elderVersion);
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
                                    'js/views/health/interventionGuidance/mealRecordResult.html?ver='+elderVersion);
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
                                    'js/views/member/memberInfo.html?ver='+elderVersion);
                            }
                        }
                    })

                    /*老友活动消息群列表*/
                    .state('groupActivityMessage', {
                        url: '/groupActivityMessage',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'groupActivityMessageCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.groupActivityMessage',
                                    ['js/controllers/message/groupActivityMessageCtrl.js'],
                                    'js/views/message/groupActivityMessage.html?ver='+elderVersion);
                            }
                        }
                    })
                    /*报名参加活动*/
                    .state('attendActivity', {
                        url: '/attendActivity/:activityId',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'attendActivityCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.attendActivity',
                                    ['js/controllers/activity/attendActivityCtrl.js'],
                                    'js/views/activity/attendActivity.html?ver='+elderVersion);
                            }
                        }
                    })
                    /*老友提醒信息列表*/
                    .state('notification', {
                        url: '/notification',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'notificationCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.notification',
                                    ['js/controllers/message/notificationCtrl.js'],
                                    'js/views/message/notification.html?ver='+elderVersion);
                            }
                        }
                    })
                    /*老友消息中心信息列表*/
                    .state('extendMessage', {
                        url: '/extendMessage',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'extendMessageCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.extendMessage',
                                    ['js/controllers/message/extendMessageCtrl.js'],
                                    'js/views/message/extendMessage.html?ver='+elderVersion);
                            }
                        }
                    })
                    /*老友消息中心某条信息的详情*/
                    .state('extendMessageDetail', {
                        url: '/extendMessageDetail/:extendMessageId',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'extendMessageDetailCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.extendMessageDetail',
                                    ['js/controllers/message/extendMessageDetailCtrl.js'],
                                    'js/views/message/extendMessageDetail.html?ver='+elderVersion);
                            }
                        }
                    })
                    /*系统中所有活动的列表信息*/
                    .state('activityList', {
                        url: '/activityList',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'activityListCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.activityList',
                                    ['js/controllers/activity/activityListCtrl.js'],
                                    'js/views/activity/activityList.html?ver='+elderVersion);
                            }
                        }
                    })
                    /*系统中某个活动的详情信息页*/
                    .state('activityDetail', {
                        url: '/activityDetail/:activityId',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'activityDetailCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.activityDetail',
                                    ['js/controllers/activity/activityDetailCtrl.js'],
                                    'js/views/activity/activityDetail.html?ver='+elderVersion);
                            }
                        }
                    })
                    /*系统中视频的播放列表，涵直播和点播的*/
                    .state('courseList', {
                        url: '/courseList/:type',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'courseListCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.courseList',
                                    ['js/controllers/course/courseListCtrl.js'],
                                    'js/views/course/courseList.html?ver='+elderVersion);
                            }
                        }
                    })
                    /*系统中视频点播视频的详细信息*/
                    .state('courseDetail', {
                        url: '/courseDetail/:courseId,:courseType',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'courseDetailCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.courseDetail',
                                    ['js/controllers/course/courseDetailCtrl.js'],
                                    'js/views/course/courseDetail.html?ver='+elderVersion);
                            }
                        }
                    })
                    /*系统中视频点播视频的详细信息*/
                    .state('healthIndex', {
                        url: '/healthIndex',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'healthIndexCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.healthIndex',
                                    ['js/controllers/health/healthIndexCtrl.js'],
                                    'js/views/health/healthIndex.html?ver='+elderVersion);
                            }
                        }
                    })
                    /*系统中视频点播视频的详细信息*/
                    .state('myRelatedElder', {
                        url: '/myRelatedElder',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'myRelatedElderCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.myRelatedElder',
                                    ['js/controllers/my/myRelatedElderCtrl.js'],
                                    'js/views/my/myRelatedElder.html?ver='+elderVersion);
                            }
                        }
                    })

                    /*问卷调查答题页*/
                    .state('surveyDetail', {
                        url: '/surveyDetail',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'surveyDetailCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.surveyDetailCtrl',
                                    ['js/controllers/survey/surveyDetailCtrl.js'],
                                    'js/views/survey/surveyDetail.html?ver='+elderVersion);
                            }
                        }
                    })
                    /*问卷调查答案预览页*/
                    .state('surveyPreview', {
                        url: '/surveyPreview/:elder_identity_num',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'surveyPreviewCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.surveyPreviewCtrl',
                                    ['js/controllers/survey/surveyPreviewCtrl.js'],
                                    'js/views/survey/surveyPreview.html?ver='+elderVersion);
                            }
                        }
                    })

                $urlRouterProvider.otherwise('/myRelatedElder')
            }])
})