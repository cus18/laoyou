angular.module('controllers',[]).controller('myRelatedElderCtrl',
    ['$scope','$rootScope','$stateParams','$state','GetRelativeElderInfo',
        function ($scope,$rootScope,$stateParams,$state,GetRelativeElderInfo) {

            // connectWebViewJavascriptBridge(function() {
            //     window.WebViewJavascriptBridge.callHandler(
            //         'getElderInfo','',function(responseData) {
            //             var dataValue = JSON.parse(responseData);
            //             $scope.elderId = dataValue.elderId;
            //             $scope.elderName = dataValue.elderName;

                            GetRelativeElderInfo.save({},function(data){
                                $scope.relativeElderList = data.responseData;
                            })
            //         })
            // })


            $scope.goHealthService = function(elderInfo){

                //将用户信息放入$rootScope中
                $rootScope.rootElderId = elderInfo.elderId;
                $rootScope.rootElderName = elderInfo.elderName;

                $rootScope.rootElderId = "563cd194da50430c93d2982e88cbfc94";
                $rootScope.rootElderName = "赵健宇";

                $state.go("healthServiceList");

            }

        }])