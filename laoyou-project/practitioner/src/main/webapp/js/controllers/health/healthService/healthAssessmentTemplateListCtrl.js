angular.module('controllers',[]).controller('healthAssessmentTemplateListCtrl',
    ['$scope','$rootScope','$stateParams','$state','GetHealthArchiveHealthAssessmentTemplateList',
        function ($scope,$rootScope,$stateParams,$state,GetHealthArchiveHealthAssessmentTemplateList) {

            $scope.loadingStatus = true;

            connectWebViewJavascriptBridge(function() {
                window.WebViewJavascriptBridge.callHandler(
                    'getElderInfo','',function(responseData) {
                        var dataValue = JSON.parse(responseData);
                        $scope.elderId = dataValue.elderId;
                        $scope.elderName = dataValue.elderName;

                        // $scope.elderId = "100000002693";
                        // $scope.elderName = "刘涛";

                        GetHealthArchiveHealthAssessmentTemplateList.save({pageNo:"1", pageSize:"5",
                            orderType:"1",orderBy:"0"},function(data){
                            $scope.loadingStatus = false;
                            $scope.healthAssessmentTemplateList = data.responseData;
                        })

                    })
            })

        }])
