
angular.module('controllers', []).controller('memberInfoCtrl', [
    '$scope','$state','$stateParams','$location','GetElderContactInfo','PractitionerUtil',
    function ($scope,$state,$stateParams,$location,GetElderContactInfo,PractitionerUtil) {


        $scope.loadingStatus = true;

        connectWebViewJavascriptBridge(function() {
            window.WebViewJavascriptBridge.callHandler(
                'getElderInfo','',function(responseData) {
                    var dataValue = JSON.parse(responseData);
                    $scope.elderId = dataValue.elderId;
                    $scope.elderName = dataValue.elderName;

                    GetElderContactInfo.get({elderId:$scope.elderId},function(data){
                        $scope.loadingStatus = false;
                        PractitionerUtil.checkResponseData(data);
                        $scope.memberExtendData = angular.fromJson(data.responseData.memberExtendData);
                    })

                })
        })

    }])
