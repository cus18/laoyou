angular.module('controllers',[]).controller('groupActivityMessageCtrl',
    ['$scope','$rootScope','$stateParams','$state','GetAttendedActivityGroupMessage',
        function ($scope,$rootScope,$stateParams,$state,GetAttendedActivityGroupMessage) {

            connectWebViewJavascriptBridge(function() {
                window.WebViewJavascriptBridge.callHandler(
                    'getElderInfo','',function(responseData) {
                        var dataValue = JSON.parse(responseData);
                        $scope.elderId = dataValue.elderId;
                        $scope.elderName = dataValue.elderName;


                        GetAttendedActivityGroupMessage.save({pageNo:"1", pageSize:"5", orderType:"1"},
                            function(data){

                                $scope.attendedActivityList = data.responseData;

                        })


                    })
            })

            $scope.goActivityGroup = function(groupId){
                window.WebViewJavascriptBridge.callHandler('enterGroupTalk', groupId, function(responseData) {});
            }



        }])