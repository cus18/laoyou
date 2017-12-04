angular.module('controllers',[]).controller('testReportResultCtrl',
    ['$scope','$rootScope','$stateParams','$state','TestReportList',
        function ($scope,$rootScope,$stateParams,$state,TestReportList) {

            $scope.loadingStatus = true;

            $scope.enterGroupTalk = function(){
                window.WebViewJavascriptBridge.callHandler('enterGroupTalk','',function(responseData){});
            }

            $scope.elderId = $rootScope.rootElderId;
            $scope.elderName = $rootScope.rootElderName;

            $scope.testReportResult = false;

            TestReportList.get({elderId:$scope.elderId,
                    startDate:$stateParams.testDate,
                    endDate:$stateParams.testDate},
                function(data){
                    $scope.loadingStatus = false;

                    angular.forEach(data.responseData,function(value,index,array){
                        if(value.testTime == $stateParams.testTime){
                            $scope.testReportResultData = value;
                        }
                    })
                    $scope.testReportResult = true;
                });


        }])
