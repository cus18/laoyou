var healthService = 'health/healthService/';
var healthArchive = 'health/healthArchive/';
var detectionDiagnose = 'health/detectionDiagnose/';
var interventionGuidance = 'health/';
var common = 'laoyou/';

define(['appElder'], function (app) {
    app
        .factory('GetOnGoingHealthServicePackageList',['$resource',function ($resource){
            return $resource(healthService + 'packageList');
        }])
        .factory('GetOnGoingHealthServicePackage',['$resource',function ($resource){
            return $resource(healthService + 'package');
        }])
        .factory('GetHealthServicePackageTemplateList',['$resource',function ($resource){
            return $resource(healthService + 'templateList');
        }])
        .factory('GetHealthServicePackageTemplateDetail',['$resource',function ($resource){
            return $resource(healthService + 'template');
        }])
        .factory('CreateHealthServicePackage',['$resource',function ($resource){
            return $resource(healthService + 'package/create');
        }])
        .factory('GetHealthArchiveBasicInfo',['$resource',function ($resource){
            return $resource(healthArchive + 'basicInfo');
        }])
        .factory('GetHealthArchivePhysicalExamination',['$resource',function ($resource){
            return $resource(healthArchive + 'physicalExamination');
        }])
        .factory('GetHealthArchivePhysicalExaminationList',['$resource',function ($resource){
            return $resource(healthArchive + 'physicalExaminationList');
        }])
        .factory('GetHealthArchivePhysicalExaminationTemplateList',['$resource',function ($resource){
            return $resource(healthArchive + 'physicalExaminationTemplateList');
        }])
        .factory('GetHealthArchiveHealthAssessmentList',['$resource',function ($resource){
            return $resource(healthArchive + 'healthAssessmentList');
        }])
        .factory('GetHealthArchiveHealthAssessmentResult',['$resource',function ($resource){
            return $resource(healthArchive + 'healthAssessment');
        }])
        .factory('GetHealthArchiveHealthAssessmentTemplateList',['$resource',function ($resource){
            return $resource(healthArchive + 'templateList');
        }])
        .factory('GetElderContactInfo',['$resource',function ($resource){
            return $resource(healthService + 'elderContactInfo');
        }])
        .factory('GetDetectionHealthData',['$resource',function ($resource){
            return $resource(detectionDiagnose + 'detection');
        }])
        .factory('CreateDetection',['$resource',function ($resource){
            return $resource(detectionDiagnose + 'createDetection');
        }])
        .factory('ControlTarget',['$resource',function ($resource){
            return $resource(detectionDiagnose + 'controlTarget');
        }])
        .factory('GetControlTarget',['$resource',function ($resource){
            return $resource(detectionDiagnose + 'getControlTarget');
        }])
        .factory('TestReportList',['$resource',function ($resource){
            return $resource(detectionDiagnose + 'testReport')
        }])
        .factory('DiagnoseReportList',['$resource',function ($resource){
            return $resource(detectionDiagnose + 'treatment')
        }])
        .factory('UserLogin',['$resource',function ($resource){
            return $resource(common + 'login');
        }])
        .factory('UserLoginOut',['$resource',function ($resource){
            return $resource(common + 'loginout');
        }])
        .factory('FeedBack',['$resource',function ($resource){
            return $resource(common + 'feedback')
        }])
        .factory('GetGroupChatData',['$resource',function ($resource){
            return $resource(common + 'groupChatData')
        }])
        .factory('GetMedicationPlan',['$resource',function ($resource){
            return $resource(interventionGuidance + 'getMedicationPlan')
        }])
        .factory('InsertMedicationPlan',['$resource',function ($resource){
            return $resource(interventionGuidance + 'insertMedicationPlan')
        }])
        .factory('UpdateMedicationPlan',['$resource',function ($resource){
            return $resource(interventionGuidance + 'updateMedicationPlan')
        }])
        .factory('DeleteMedicationPlan',['$resource',function ($resource){
            return $resource(interventionGuidance + 'deleteMedicationPlan')
        }])
        .factory('GetMedicationPlanByID',['$resource',function ($resource){
            return $resource(interventionGuidance + 'getMedicationPlanByID')
        }])
        .factory('GetDietPlanByDate',['$resource',function ($resource){
            return $resource(interventionGuidance + 'getDietPlanByDate')
        }])
        .factory('GetMedicationTimingPlanByID',['$resource',function ($resource){
            return $resource(interventionGuidance + 'getMedicationTimingPlanByID')
        }])
        .factory('UpdateMedicationPlanStatus',['$resource',function ($resource){
            return $resource(interventionGuidance + 'updateMedicationPlanStatus')
        }])
        .factory('GetMedicationPlanTimingByElderUserID',['$resource',function ($resource){
            return $resource(interventionGuidance + 'getMedicationPlanTimingByElderUserID')
        }])
        .factory('SendIdentifying',['$resource',function ($resource){
            return $resource(common + 'sendIdentifying')
        }])

        //获取已参与的活动群列表信息
        .factory('GetAttendedActivityGroupMessage',['$resource',function ($resource){
            return $resource(common + 'attendedActivityGroupMessage')
        }])
        //获取老友提醒信息
        .factory('GetNotificationMessage',['$resource',function ($resource){
            return $resource(common + 'notificationMessage')
        }])
        //更新老友提醒信息中某条消息的已读状态
        .factory('UpdateNotificationMessageReadStatus',['$resource',function ($resource){
            return $resource(common + 'notificationMessage/update')
        }])
        //获取老友提醒信息
        .factory('GetExtendMessage',['$resource',function ($resource){
            return $resource(common + 'extendMessage')
        }])
        //获取老友提醒信息详情
        .factory('GetExtendMessageDetail',['$resource',function ($resource){
            return $resource(common + 'extendMessage/detail')
        }])
        //获取所有的活动列表
        .factory('GetActivityList',['$resource',function ($resource){
            return $resource(common + 'activityList')
        }])
        //获取某个活动的详情信息
        .factory('GetActivityDetail',['$resource',function ($resource){
            return $resource(common + 'activityDetail')
        }])
        //获取用户参加某个活动的状态
        .factory('GetActivityAttendStatus',['$resource',function ($resource){
            return $resource(common + 'activityAttendStatus')
        }])
        //获取某个活动的评论信息
        .factory('GetActivityDiscuss',['$resource',function ($resource){
            return $resource(common + 'activityDiscuss')
        }])
        //用户真对某个活动的发表评论
        .factory('CreateActivityDiscuss',['$resource',function ($resource){
            return $resource(common + 'activityDiscuss/create')
        }])
        //报名参加某个活动
        .factory('JoinActivity',['$resource',function ($resource){
            return $resource(common + 'joinActivity')
        }])
        //获取推荐的活动
        .factory('GetSuggestActivity',['$resource',function ($resource){
            return $resource(common + 'suggestActivity')
        }])
        //获取近期直播的列表
        .factory('GetRecentLiveBroadCast',['$resource',function ($resource){
            return $resource(common + 'recentLiveBroadCast')
        }])
        //获取历史直播的列表
        .factory('GetHistoryLiveBroadCast',['$resource',function ($resource){
            return $resource(common + 'historyLiveBroadCast')
        }])
        //获取直播的详细内容
        .factory('GetLiveBroadCastDetail',['$resource',function ($resource){
            return $resource(common + 'liveBroadCastDetail')
        }])
        //报名参加某个直播课程
        .factory('RegisterLiveBroadCast',['$resource',function ($resource){
            return $resource(common + 'liveBroadCast/register')
        }])
        //获取在线课堂的列表
        .factory('GetOnlineCourseList',['$resource',function ($resource){
            return $resource(common + 'onlineCourseList')
        }])
        //获取在线课堂的详细内容
        .factory('GetOnlineCourseDetail',['$resource',function ($resource){
            return $resource(common + 'onlineCourseDetail')
        }])
        //获取在线课堂的某个课程的所有评论
        .factory('GetOnlineCourseDiscuss',['$resource',function ($resource){
            return $resource(common + 'onlineCourseDiscuss')
        }])
        //获取在线课堂的某个课程进行评论
        .factory('CreateOnlineCourseDiscuss',['$resource',function ($resource){
            return $resource(common + 'onlineCourseDiscuss/create')
        }])

        //获取自己的亲友群中所有的亲友信息列表
        .factory('GetRelativeElderInfo',['$resource',function ($resource){
            return $resource(common + 'relativeElderInfo')
        }])

        //获取用户的参加的活动信息列表
        .factory('GetMyActivityList',['$resource',function ($resource){
            return $resource(common + 'myActivityList')
        }])

        //获取用户的参加在线课堂列表
        .factory('GetMyCourseList',['$resource',function ($resource){
            return $resource(common + 'myCourseList')
        }])


        //获取问卷所有的题目
        .factory('GetTotalQuestion',['$resource',function ($resource){
            return $resource('survey/list')
        }])
        //获取某道题目的答案
        .factory('GetQuestionAnswer',['$resource',function ($resource){
            return $resource('survey/getAnswer')
        }])
        //提交某道题目的答案
        .factory('SaveQuestionAnswer',['$resource',function ($resource){
            return $resource('survey/answer')
        }])
        //获取省数据列表
        .factory('GetProvinceData',['$resource',function ($resource){
            return $resource('survey/province')
        }])
        //获取城市数据列表
        .factory('GetAreaData',['$resource',function ($resource){
            return $resource('survey/area')
        }])
        .factory('GetQRCodeURL',['$resource',function ($resource){
            return $resource(common + 'getUserQRCode')
        }])
        .factory('LoginOut',['$resource',function ($resource){
            return $resource(common + 'loginout')
        }])


})
