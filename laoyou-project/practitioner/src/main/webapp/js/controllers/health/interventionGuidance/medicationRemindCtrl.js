/**
 * Created by 郑强丽 on 2017/5/27.
 */

angular.module('controllers',[]).controller('medicationRemindCtrl',
    ['$scope','$rootScope','$stateParams','$state','GetMedicationTimingPlanByID',
        'UpdateMedicationPlanStatus',
        function($scope,$rootScope,$stateParams,$state,GetMedicationTimingPlanByID,
                 UpdateMedicationPlanStatus){

            $scope.loadingStatus = true;

            $scope.id = $stateParams.remindId;

            // $scope.id = '1d29b88f85924d3f878577478af44590';

            $scope.medicationRemind = {};

            $scope.enterGroupTalk = function(){
                window.WebViewJavascriptBridge.callHandler('enterGroupTalk','',function(responseData){});
            }


            connectWebViewJavascriptBridge(function() {
                 window.WebViewJavascriptBridge.callHandler(
                     'getElderInfo','',function(responseData) {
                         var dataValue = JSON.parse(responseData);
                         $scope.elderId = dataValue.elderId;
                         $scope.elderName = dataValue.elderName;


                         GetMedicationTimingPlanByID.save({id:$scope.id},function(data){

                             $scope.loadingStatus = false;

                             if(data.responseData){
                                 $scope.medicationRemind = data.responseData;
                                 if(data.responseData.status == ''){
                                     $scope.medicationHint = '老友提醒' + $scope.elderName + '按时服用：';
                                 }
                                 else if(data.responseData.status == '1')
                                 {
                                     $scope.medicationHint = $scope.elderName + '已服用：';
                                 }
                                 else if(data.responseData.status == '0')
                                 {
                                     $scope.medicationHint = $scope.elderName + '未服用：';
                                 }
                             }
                         })


                         $scope.updateStatus = function(code){
                             UpdateMedicationPlanStatus.save({id:$scope.medicationRemind.id,status:code},function(data){
                                 $state.go('interventionGuidance',{firstMenu:'medicineIntervention',secondMenu:'interventionPlan'});
                             })
                         }
                     })
             })
        }])
