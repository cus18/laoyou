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
            // $scope.id = '9ee2800fecc84aec99b3f987264f2546';

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

                        // $scope.elderId = "100000002693";
                        // $scope.elderName = "刘涛";

                        GetMedicationTimingPlanByID.save({id:$scope.id},function(data){
                            $scope.loadingStatus = false;

                            if(data.responseData){
                                $scope.medicationRemind = data.responseData;
                                if(data.responseData.status == ''){
                                    $scope.medicationHint = '老友提醒您按时服用：';
                                    $scope.medicationBtn = true;
                                }
                                else if(data.responseData.status == '1')
                                {
                                    $scope.medicationHint = '您已服用：';
                                }
                                else if(data.responseData.status == '0')
                                {
                                    $scope.medicationHint = '您未服用：';
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
