angular.module('controllers',[]).controller('physicalExaminationCtrl',
    ['$scope','$rootScope','$stateParams','$state',
        'GetHealthArchivePhysicalExamination',
        function ($scope,$rootScope,$stateParams,$state,
                  GetHealthArchivePhysicalExamination) {

            $scope.loadingStatus = true;

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

                        $scope.physicalExaminationResult = false;

                        console.log($stateParams.physicalExaminationId)

                        GetHealthArchivePhysicalExamination.get({physicalExaminationId:$stateParams.physicalExaminationId},
                            function(data){

                                $scope.loadingStatus = false;

                                $scope.physicalExaminationResult = true;
                                $scope.physicalExaminationResultData = data.responseData;

                            });
                    })
            })

        }])
