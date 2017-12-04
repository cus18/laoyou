var healthService = 'health/healthService/';
var healthArchive = 'health/healthArchive/';
var detectionDiagnose = 'health/detectionDiagnose/';
var common = 'laoyou/';
var interventionGuidance = 'health/';


define(['appPractitioner'], function (app) {
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
        .factory('SaveHealthAssessmentAnswer',['$resource',function ($resource){
            return $resource(healthArchive + 'saveHealthAssessmentAnswer');
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


})
