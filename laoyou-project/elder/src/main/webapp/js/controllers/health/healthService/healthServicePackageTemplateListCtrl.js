angular.module('controllers',[]).controller('healthServicePackageTemplateListCtrl',
    ['$scope','$rootScope','$stateParams','$state','GetHealthServicePackageTemplateList','ElderUtil',
        function ($scope,$rootScope,$stateParams,$state,GetHealthServicePackageTemplateList,ElderUtil) {

            $scope.loadingStatus = true;

            connectWebViewJavascriptBridge(function() {
                window.WebViewJavascriptBridge.callHandler(
                    'getElderInfo','',function(responseData) {
                        var dataValue = JSON.parse(responseData);
                        $scope.elderId = dataValue.elderId;
                        $scope.elderName = dataValue.elderName;

                        // $scope.elderId = "100000002693";
                        // $scope.elderName = "刘涛";

                        GetHealthServicePackageTemplateList.save({pageNo:"1", pageSize:"5",
                            orderType:"1",orderBy:"0"},function(data){

                            $scope.loadingStatus = false;

                            ElderUtil.checkResponseData(data);
                            $scope.healthServicePackageTemplateList = data.responseData;
                        })
                    })
            })

        }])
