/**
 * Created by 郑强丽 on 2017/5/20.
 */
angular.module('controllers',[]).controller('controlObjectiveCtrl',
    ['$scope','$interval','$stateParams','$state','ControlTarget','GetControlTarget','ElderUtil',
        function ($scope,$interval,$stateParams,$state,ControlTarget,GetControlTarget,ElderUtil) {

            $scope.loadingStatus = true;

            $scope.firstMenu = $stateParams.objectiveType;

            /*
             * 保存血糖目标值
             * */
            var submitOnOff = true;        //防止多次提交
            $scope.submitBloodSugarForm = function(isValid){
                if(isValid){
                    var detectionData = [];
                    var controlObjective = {
                        fasting:$scope.controlObjectiveData.limosis_min + '-' + $scope.controlObjectiveData.limosis_max,
                        beforeMeal:$scope.controlObjectiveData.before_meal_min + '-' + $scope.controlObjectiveData.before_meal_max,
                        afterMeal:$scope.controlObjectiveData.after_meal_min + '-' + $scope.controlObjectiveData.after_meal_max,
                        beforeBed:$scope.controlObjectiveData.before_sleep_min + '-' + $scope.controlObjectiveData.before_sleep_max,
                        dawn:$scope.controlObjectiveData.before_dawn_min + '-' + $scope.controlObjectiveData.before_dawn_max
                    }
                    detectionData.push(controlObjective);

                    if(submitOnOff) {
                        submitOnOff = false;
                        ControlTarget.save({
                            elderId: $scope.elderId,
                            elderName: $scope.elderName,
                            detectionType: 'bg',
                            detectionData: detectionData
                        }, function (data) {
                            $state.go('bloodSugarRecord');
                        });
                    }
                }
            }

            /*
             * 保存血压目标值
             * */
            $scope.submitBloodPressureForm = function(isValid){
                if(isValid){
                    var detectionData = [];
                    var controlObjective = {
                        diastolic:$scope.controlObjectiveData.diastolic_min + '-' + $scope.controlObjectiveData.diastolic_max,
                        systolic:$scope.controlObjectiveData.systolic_min + '-' + $scope.controlObjectiveData.systolic_max,
                        heartRate:$scope.controlObjectiveData.heart_rate_min + '-' + $scope.controlObjectiveData.heart_rate_max
                    }
                    detectionData.push(controlObjective);

                    if(submitOnOff) {
                        submitOnOff = false;
                        ControlTarget.save({
                            elderId: $scope.elderId,
                            elderName: $scope.elderName,
                            detectionType: 'bp',
                            detectionData: detectionData
                        }, function (data) {
                            $state.go('bloodPressureRecord');
                        });
                    }
                }
            }

            connectWebViewJavascriptBridge(function() {
                window.WebViewJavascriptBridge.callHandler(
                    'getElderInfo','',function(responseData) {
                        var dataValue = JSON.parse(responseData);
                        $scope.elderId = dataValue.elderId;
                        $scope.elderName = dataValue.elderName;

                        // $scope.elderId = "100000002693";
                        // $scope.elderName = "刘涛";

                        $scope.controlObjectiveData = {};

                        if($scope.firstMenu == 'bloodSugar'){

                            /*获取血糖目标值*/
                            GetControlTarget.get({elderId:$scope.elderId,detectionType:'bg'},function(data){

                                $scope.loadingStatus = false;

                                if(data){
                                    var data = data.responseData.detectionData[0];
                                    function stringToNum(data,index){
                                        return Number(data.split('-')[index]);
                                    }
                                    $scope.controlObjectiveData.limosis_min = stringToNum(data.fasting,0);
                                    $scope.controlObjectiveData.limosis_max = stringToNum(data.fasting,1);
                                    $scope.controlObjectiveData.before_meal_min = stringToNum(data.beforeMeal,0);
                                    $scope.controlObjectiveData.before_meal_max = stringToNum(data.beforeMeal,1);
                                    $scope.controlObjectiveData.after_meal_min = stringToNum(data.afterMeal,0);
                                    $scope.controlObjectiveData.after_meal_max = stringToNum(data.afterMeal,1);
                                    $scope.controlObjectiveData.before_sleep_min = stringToNum(data.beforeBed,0);
                                    $scope.controlObjectiveData.before_sleep_max = stringToNum(data.beforeBed,1);
                                    $scope.controlObjectiveData.before_dawn_min = stringToNum(data.dawn,0);
                                    $scope.controlObjectiveData.before_dawn_max = stringToNum(data.dawn,1);
                                }

                            });

                        }
                        if($scope.firstMenu == 'bloodPressure'){

                            /*获取血压目标值*/
                            GetControlTarget.get({elderId:$scope.elderId,detectionType:'bp'},function(data){

                                $scope.loadingStatus = false;

                                if(data){
                                    var data = data.responseData.detectionData[0];
                                    function stringToNum(data,index){
                                        return Number(data.split('-')[index]);
                                    }
                                    $scope.controlObjectiveData.diastolic_min = stringToNum(data.diastolic,0);
                                    $scope.controlObjectiveData.diastolic_max = stringToNum(data.diastolic,1);
                                    $scope.controlObjectiveData.systolic_min = stringToNum(data.systolic,0);
                                    $scope.controlObjectiveData.systolic_max = stringToNum(data.systolic,1);
                                    $scope.controlObjectiveData.heart_rate_min = stringToNum(data.heartRate,0);
                                    $scope.controlObjectiveData.heart_rate_max = stringToNum(data.heartRate,1);
                                }
                            });


                        }
                    })
            })

        }])
