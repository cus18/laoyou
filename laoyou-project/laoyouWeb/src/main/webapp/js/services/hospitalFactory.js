var common = 'laoyou/';
var interventionGuidance = 'health/';
var member = 'member/';
var device = 'device/';
var product = 'product/';
var doctor = 'doctor/';
var healthService = 'health/healthService/';
var healthArchive = 'health/healthArchive/';
var detectionDiagnose = 'health/detectionDiagnose/';
var homePageStatistics = 'homePageStatistics/';
var activityDiscuss = 'laoyou/activityDiscuss/';
var office = 'office/';
var survey = 'survey/';
var userManage = 'userManage/';
var extendMessage = 'laoyou/extendMessage/';

define(['appHospital'], function (app) {
    app
        .factory('UserLogin',['$resource',function ($resource){
            return $resource(common + 'login');
        }])
        .factory('UserLoginOut',['$resource',function ($resource){
            return $resource(common + 'loginout');
        }])
        .factory('UserGetUser',['$resource',function ($resource){
            return $resource(common + 'getUser');
        }])
        .factory('SendIdentifying',['$resource',function ($resource){
            return $resource(common + 'sendIdentifying')
        }])
        .factory('AddMember',['$resource',function ($resource){
            return $resource(member + 'addMember')
        }])
        .factory('GetMemberInfoList',['$resource',function ($resource){
            return $resource(member + 'getMemberInfoList')
        }])
        .factory('GetMemberInfo',['$resource',function ($resource){
            return $resource(member + 'getMemberInfo')
        }])
        .factory('UpdateMember',['$resource',function ($resource){
            return $resource(member + 'updateMember')
        }])
        .factory('DelMember',['$resource',function ($resource){
            return $resource(member + 'delMember')
        }])
        .factory('AssignedDoctor',['$resource',function ($resource){
            return $resource(member + 'assignedDoctor')
        }])
        .factory('DeviceList',['$resource',function ($resource){
            return $resource(device + 'searchDevice')
        }])
        .factory('DeviceStatistic',['$resource',function ($resource){
            return $resource(device + 'statisticsDevice')
        }])
        .factory('OperDevice',['$resource',function ($resource){
            return $resource(device + 'operDevice')
        }])
        .factory('FindProduct',['$resource',function ($resource){
            return $resource(product + 'findProductService')
        }])
        .factory('UpdateProduct',['$resource',function ($resource){
            return $resource(product + 'updateProductService')
        }])
        .factory('AddProduct',['$resource',function ($resource){
            return $resource(product + 'addProductService')
        }])
        .factory('DelProduct',['$resource',function ($resource){
            return $resource(product + 'delProductService')
        }])
        .factory('GetDoctorList',['$resource',function ($resource){
            return $resource(doctor + 'getDoctorList')
        }])
        .factory('Create',['$resource',function ($resource){
            return $resource(doctor + 'create')
        }])
        .factory('GetDoctor',['$resource',function ($resource){
            return $resource(doctor + 'getDoctor')
        }])
        .factory('UpdateDoctor',['$resource',function ($resource){
            return $resource(doctor + 'updateDoctor')
        }])
        .factory('DelDoctor',['$resource',function ($resource){
            return $resource(doctor + 'delDoctor')
        }])
        .factory('GetOnGoingHealthServicePackageList',['$resource',function ($resource){
            return $resource(healthService + 'packageList');
        }])
        .factory('GetOnGoingHealthServicePackage',['$resource',function ($resource){
            return $resource(healthService + 'package');
        }])
        .factory('GetHealthServicePackageTemplateDetail',['$resource',function ($resource){
            return $resource(healthService + 'template');
        }])
        .factory('GetHealthArchiveBasicInfo',['$resource',function ($resource){
            return $resource(healthArchive + 'basicInfo');
        }])
        .factory('GetHealthArchivePhysicalExaminationList',['$resource',function ($resource){
            return $resource(healthArchive + 'physicalExaminationList');
        }])
        .factory('GetHealthArchiveHealthAssessmentList',['$resource',function ($resource){
            return $resource(healthArchive + 'healthAssessmentList');
        }])
        .factory('GetHealthArchiveHealthAssessmentResult',['$resource',function ($resource){
            return $resource(healthArchive + 'healthAssessment');
        }])
        .factory('GetDetectionHealthData',['$resource',function ($resource){
            return $resource(detectionDiagnose + 'detection');
        }])
        .factory('TestReportList',['$resource',function ($resource){
            return $resource(detectionDiagnose + 'testReport')
        }])
        .factory('DiagnoseReportList',['$resource',function ($resource){
            return $resource(detectionDiagnose + 'treatment')
        }])
        .factory('GetDietPlanByDate',['$resource',function ($resource){
            return $resource(interventionGuidance + 'getDietPlanByDate')
        }])
        .factory('GetMedicationPlan',['$resource',function ($resource){
            return $resource(interventionGuidance + 'getMedicationPlan')
        }])
        .factory('GetDetectionHealthData',['$resource',function ($resource){
            return $resource(detectionDiagnose + 'detection');
        }])
        .factory('GetMedicationPlanTimingByElderUserID',['$resource',function ($resource){
            return $resource(interventionGuidance + 'getMedicationPlanTimingByElderUserID')
        }])
        .factory('MemberStatistics',['$resource',function ($resource){
            return $resource(homePageStatistics + 'memberStatistics')
        }])
        .factory('DoctorStatistics',['$resource',function ($resource){
            return $resource(homePageStatistics + 'doctorStatistics')
        }])
        .factory('NurseStatistics',['$resource',function ($resource){
            return $resource(homePageStatistics + 'nurseStatistics')
        }])
        .factory('DiabeticStatistics',['$resource',function ($resource){
            return $resource(homePageStatistics + 'diabeticStatistics')
        }])
        .factory('HypertensiveStatistics',['$resource',function ($resource){
            return $resource(homePageStatistics + 'hypertensiveStatistics')
        }])
        .factory('SaveBasicInfo',['$resource',function ($resource){
            return $resource('test/uploadBasicInfo')
        }])
        .factory('SaveServiceInfo',['$resource',function ($resource){
            return $resource('test/uploadServiceInfo')
        }])
        .factory('CreateActivity',['$resource',function ($resource){
            return $resource(common + 'createActivity')
        }])
        .factory('CreateActivityEasemobGroup',['$resource',function ($resource){
            return $resource(activityDiscuss + 'createActivityEasemobGroup')
        }])
        .factory('ActivityListByBackEnd',['$resource',function ($resource){
            return $resource(common + 'activityListByBackEnd')
        }])
        .factory('GetAllActivityListByBackEnd',['$resource',function ($resource){
            return $resource(common + 'getAllActivityListByBackEnd')
        }])
        .factory('ActivityList',['$resource',function ($resource){
            return $resource(common + 'activityList')
        }])
        .factory('ActivityDetail',['$resource',function ($resource){
            return $resource(common + 'activityDetail')
        }])
        .factory('SearchOffice',['$resource',function ($resource){
            return $resource(office + 'searchOffice')
        }])
        .factory('AddOffice',['$resource',function ($resource){
            return $resource(office + 'addOffice')
        }])
        .factory('UpdateOffice',['$resource',function ($resource){
            return $resource(office + 'updateOffice')
        }])
        .factory('DeleteOffice',['$resource',function ($resource){
            return $resource(office + 'deleteOffice')
        }])
        .factory('GetOffice',['$resource',function ($resource){
            return $resource(office + 'getOffice')
        }])
        .factory('SearchUser',['$resource',function ($resource){
            return $resource(userManage + 'searchUser')
        }])
        .factory('AddUser',['$resource',function ($resource){
            return $resource(userManage + 'addUser')
        }])
        .factory('GetUser',['$resource',function ($resource){
            return $resource(userManage + 'getUser')
        }])
        .factory('DeleteUser',['$resource',function ($resource){
            return $resource(userManage + 'deleteUser')
        }])
        .factory('UpdateUser',['$resource',function ($resource){
            return $resource(userManage + 'updateUser')
        }])
        .factory('BannerResourceList',['$resource',function ($resource){
            return $resource(common + 'bannerResourceList')
        }])
        .factory('GetBanner',['$resource',function ($resource){
            return $resource(common + 'getBanner')
        }])
        .factory('AddBanner',['$resource',function ($resource){
            return $resource(common + 'addBanner')
        }])
        .factory('BannerTurnOff',['$resource',function ($resource){
            return $resource(common + 'bannerTurnOff')
        }])
        .factory('GetNotificationTemplateList',['$resource',function ($resource){
            return $resource(extendMessage + 'getNotificationTemplateList')
        }])
        .factory('AddNotificationTemplate',['$resource',function ($resource){
            return $resource(extendMessage + 'addNotificationTemplate')
        }])
        .factory('GetNotificationTemplate',['$resource',function ($resource){
            return $resource(extendMessage + 'getNotificationTemplate')
        }])
        .factory('UpdateNotificationTemplate',['$resource',function ($resource){
            return $resource(extendMessage + 'updateNotificationTemplate')
        }])
        .factory('DeleteNotificationTemplate',['$resource',function ($resource){
            return $resource(extendMessage + 'deleteNotificationTemplate')
        }])
        .factory('GetRemindTemplateEntityList',['$resource',function ($resource){
            return $resource(extendMessage + 'getRemindTemplateEntityList')
        }])
        .factory('AddRemindTemplate',['$resource',function ($resource){
            return $resource(extendMessage + 'addRemindTemplate')
        }])
        .factory('UpdateRemindTemplate',['$resource',function ($resource){
            return $resource(extendMessage + 'updateRemindTemplate')
        }])
        .factory('GetRemindTemplateEntityByID',['$resource',function ($resource){
            return $resource(extendMessage + 'getRemindTemplateEntityByID')
        }])
        .factory('DeleteRemindTemplate',['$resource',function ($resource){
            return $resource(extendMessage + 'deleteRemindTemplate')
        }])






        /*问卷后台*/
        .factory('List',['$resource',function ($resource){
            return $resource(survey + 'list ')
        }])
        .factory('SingleStatistic',['$resource',function ($resource){
            return $resource(survey + 'singleStatistic ')
        }])
        .factory('CrossStatistic',['$resource',function ($resource){
            return $resource(survey + 'crossStatistic ')
        }])
        .factory('DiyStatistic',['$resource',function ($resource){
            return $resource(survey + 'diyStatistic ')
        }])

})
