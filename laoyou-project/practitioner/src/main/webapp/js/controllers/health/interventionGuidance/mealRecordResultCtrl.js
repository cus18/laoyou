/**
 * Created by 郑强丽 on 2017/6/5.
 */
angular.module('controllers',[]).controller('mealRecordResultCtrl',
    ['$scope','$rootScope','$stateParams','$state','GetDietPlanByDate',
        function ($scope,$rootScope,$stateParams,$state,GetDietPlanByDate) {

            $scope.loadingStatus = true;

            connectWebViewJavascriptBridge(function() {
                window.WebViewJavascriptBridge.callHandler(
                    'getElderInfo','',function(responseData) {
                        var dataValue = JSON.parse(responseData);
                        $scope.elderId = dataValue.elderId;
                        $scope.elderName = dataValue.elderName;

                            // $scope.elderId = '100000002693';
                            // $scope.elderName = '浦声波';

                            $scope.date = $stateParams.date;
                            $scope.time = $stateParams.time;


                            GetDietPlanByDate.save({elderUserID:$scope.elderId,startDate:$scope.date,endDate:$scope.date},function(data){

                                $scope.loadingStatus = false;

                                angular.forEach(data.responseData, function (value, index, array) {

                                    if(value.createTime == $scope.time){
                                        $scope.mealRecordResultData = value;
                                    }


                                });

                            });

                    })
            })

        }])

