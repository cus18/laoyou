angular.module('controllers',[]).controller('healthServiceCtrl',
    ['$scope','$rootScope','$stateParams','$state','GetOnGoingHealthServicePackageList',
        'GetHealthArchiveBasicInfo','GetHealthArchivePhysicalExaminationList',
        'GetHealthArchiveHealthAssessmentList', 'GetElderContactInfo',
        'ElderUtil','Global','$timeout',
        function ($scope,$rootScope,$stateParams,$state,GetOnGoingHealthServicePackageList,
                  GetHealthArchiveBasicInfo,GetHealthArchivePhysicalExaminationList,
                  GetHealthArchiveHealthAssessmentList,GetElderContactInfo,
                  ElderUtil,Global,$timeout) {

            $scope.loadingStatus = true;

            $scope.goMenu = function(firstMenuParam,secondMenuParam){
                $state.go("healthService", {firstMenu: firstMenuParam, secondMenu: secondMenuParam});
            }

            $scope.goPhysicalExamination = function(physicalExaminationId) {
                $state.go("physicalExamination",{physicalExaminationId:physicalExaminationId});
            }

            $scope.newPhysicalExamination = function () {
                connectWebViewJavascriptBridge(function() {
                    window.WebViewJavascriptBridge.callHandler(
                        'createPhysicalExamination','',function(responseData) {
                        });
                });

                connectWebViewJavascriptBridge(function(bridge) {
                    bridge.registerHandler("createPhysicalExaminationDown", function(data, responseCallback) {
                        if(data == Global.SUCCESS)
                        {
                            $timeout(function() {
                                GetHealthArchivePhysicalExaminationList.save({pageNo:"1", pageSize:"5",
                                    orderType:"1",orderBy:"0",
                                    requestData:{elderId:$scope.elderId}},function(data){
                                    $scope.loadingStatus = false;
                                    ElderUtil.checkResponseData(data);
                                    $scope.healthArchivePhysicalExaminationList = data.responseData;
                                });
                            }, 6000);
                        }
                        responseCallback(responseData);
                    });
                });
            }

            $scope.firstMenu = $stateParams.firstMenu;
            $scope.secondMenu = $stateParams.secondMenu;
            $rootScope.h5Page = true;

            $scope.elderId = $rootScope.rootElderId;
            $scope.elderName = $rootScope.rootElderName;

            if($scope.firstMenu == "healthServicePackage"){
                GetOnGoingHealthServicePackageList.save({pageNo:"1", pageSize:"5", orderType:"1",orderBy:"0",
                    requestData:{elderId:$scope.elderId}}, function (data) {
                    $scope.loadingStatus = false;
                    ElderUtil.checkResponseData(data);
                    $scope.healthServicePackageList = data.responseData;
                })
            }
            else if($scope.firstMenu == "healthArchives")
            {
                if($scope.secondMenu == 'basicInfo'){
                    GetHealthArchiveBasicInfo.get({elderId:$scope.elderId},function(data){
                        $scope.loadingStatus = false;
                        ElderUtil.checkResponseData(data);
                        $scope.healthArchiveBasicInfo = data.responseData;
                    });
                }
                else if($scope.secondMenu == 'physicalExamination'){
                    GetHealthArchivePhysicalExaminationList.save({pageNo:"1", pageSize:"5",
                        orderType:"1",orderBy:"0",
                        requestData:{elderId:$scope.elderId}},function(data){
                        $scope.loadingStatus = false;
                        ElderUtil.checkResponseData(data);
                        $scope.healthArchivePhysicalExaminationList = data.responseData;
                        console.log($scope.healthArchivePhysicalExaminationList);
                    });
                }
            }
            else if($scope.firstMenu == "healthAssessment")
            {
                GetHealthArchiveHealthAssessmentList.save({pageNo:"1", pageSize:"10",
                    orderType:"1",orderBy:"0",
                    requestData:{elderId:$scope.elderId}},function(data){
                    $scope.loadingStatus = false;
                    ElderUtil.checkResponseData(data);
                    $scope.healthArchiveAssessmentList = data.responseData;
                    console.log($scope.healthArchiveAssessmentList);
                });
            }

            $scope.gotoHealthServicePackage = function(servicePackageId,operation){
                $state.go("healthServicePackage",{servicePackageId:servicePackageId,operation:operation});
            }
}])
