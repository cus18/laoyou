angular.module('controllers',[]).controller('attendActivityCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','GetRelativeElderInfo',
        'GetActivityDetail','JoinActivity','Global','ElderUtil',
        function ($scope,$interval,$rootScope,$stateParams,$state,GetRelativeElderInfo,
                  GetActivityDetail,JoinActivity,Global,ElderUtil) {

            var activityId = $stateParams.activityId;

            $scope.param = {
                attended : false
            };

            $scope.confirmAttend = function(){
                var attendElderList = [];
                angular.forEach($scope.relativeElderList,function(value,index,array){
                    if(value.checked)
                    {
                        attendElderList.push(value.elderID);
                    }
                })
                if(attendElderList.length==0)
                {
                    alert("请选择要参加活动的人");
                }
                else
                {
                    JoinActivity.get({elderList:attendElderList,activityId:activityId},function(data){
                        ElderUtil.checkResponseData(data);
                        if(data.result = Global.SUCCESS)
                        {
                            $scope.param.attended = true;
                            $scope.activityGroupId = data.responseData
                        }
                    })
                }
            };

            $scope.$on('$ionicView.enter', function(){

                GetRelativeElderInfo.save({},function(data){
                    ElderUtil.checkResponseData(data);
                    $scope.relativeElderList = data.responseData;
                    angular.forEach($scope.relativeElderList,function(value,index,array){
                        value.checked = false;
                    })
                })

                GetActivityDetail.get({activityId:activityId}, function(data){
                    ElderUtil.checkResponseData(data);
                    $scope.detailActivityInfo = data.responseData;
                })

            });

            $scope.enterActivityGroupTalk = function(){
                connectWebViewJavascriptBridge(function() {
                    window.WebViewJavascriptBridge.callHandler('attendActivityGroupTalk',
                        $scope.activityGroupId+";"+$scope.detailActivityInfo.activityName,function(responseData) {})
                })
            }
        }])