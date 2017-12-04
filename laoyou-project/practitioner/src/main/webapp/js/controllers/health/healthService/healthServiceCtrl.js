angular.module('controllers',[]).controller('healthServiceCtrl',
    ['$scope','$rootScope','$stateParams','$state','GetOnGoingHealthServicePackageList',
        'GetHealthArchiveBasicInfo','GetHealthArchivePhysicalExaminationList',
        'GetHealthArchiveHealthAssessmentList', 'GetElderContactInfo',
        'PractitionerUtil','Global','$timeout',
    function ($scope,$rootScope,$stateParams,$state,GetOnGoingHealthServicePackageList,
              GetHealthArchiveBasicInfo,GetHealthArchivePhysicalExaminationList,
              GetHealthArchiveHealthAssessmentList,GetElderContactInfo,
              PractitionerUtil,Global,$timeout) {


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
                        $scope.loadingStatus = true;
                        $timeout(function() {
                            GetHealthArchivePhysicalExaminationList.save({pageNo:"1", pageSize:"5",
                                orderType:"1",orderBy:"0",
                                requestData:{elderId:$scope.elderId}},function(data){
                                PractitionerUtil.checkResponseData(data);
                                $scope.loadingStatus = false;
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

          connectWebViewJavascriptBridge(function() {
              window.WebViewJavascriptBridge.callHandler(
                  'getElderInfo','',function(responseData) {
                      var dataValue = JSON.parse(responseData);
                      $scope.elderId = dataValue.elderId;
                      $scope.elderName = dataValue.elderName;

                     //$scope.elderId = "64da406293dd4f6d9c01172f19b2dd8b";
                     //$scope.elderName = "刘涛";

                    if($scope.firstMenu == "healthServicePackage"){
                        GetOnGoingHealthServicePackageList.save({pageNo:"1", pageSize:"5", orderType:"1",orderBy:"0",
                            requestData:{elderId:$scope.elderId}}, function (data) {
                            PractitionerUtil.checkResponseData(data);
                            $scope.loadingStatus = false;
                            $scope.healthServicePackageList = data.responseData;
                        })
                    }
                    else if($scope.firstMenu == "healthArchives")
                    {
                        if($scope.secondMenu == 'basicInfo'){
                            GetHealthArchiveBasicInfo.get({elderId:$scope.elderId},function(data){
                                PractitionerUtil.checkResponseData(data);
                                $scope.loadingStatus = false;
                                $scope.healthArchiveBasicInfo = data.responseData;
                            });
                        }
                        else if($scope.secondMenu == 'physicalExamination'){
                            GetHealthArchivePhysicalExaminationList.save({pageNo:"1", pageSize:"5",
                                orderType:"1",orderBy:"0",
                                requestData:{elderId:$scope.elderId}},function(data){
                                PractitionerUtil.checkResponseData(data);
                                $scope.loadingStatus = false;
                                $scope.healthArchivePhysicalExaminationList = data.responseData;
                            });
                        }
                    }
                    else if($scope.firstMenu == "healthAssessment")
                    {
                        GetHealthArchiveHealthAssessmentList.save({pageNo:"1", pageSize:"10",
                            orderType:"1",orderBy:"0",
                            requestData:{elderId:$scope.elderId}},function(data){
                            PractitionerUtil.checkResponseData(data);
                            $scope.loadingStatus = false;
                            $scope.healthArchiveAssessmentList = data.responseData;
                        });
                    }
                  });
          })

    }])
