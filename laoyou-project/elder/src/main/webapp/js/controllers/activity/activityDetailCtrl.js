angular.module('controllers',[]).controller('activityDetailCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','GetActivityDetail',
        'GetActivityAttendStatus','GetActivityDiscuss','CreateActivityDiscuss','ElderUtil',
        function ($scope,$interval,$rootScope,$stateParams,$state,GetActivityDetail,
                  GetActivityAttendStatus,GetActivityDiscuss,CreateActivityDiscuss,ElderUtil) {

            var activityId = $stateParams.activityId;

            $scope.param = {
                activityAttendStatus : "0",
                operation : "introduce",
                page : {pageNo:1, pageSize:5, orderType:1},
                activityId : activityId,
                attendDiscuss: false,
                discussContent: ""
            }

            $scope.$on('$ionicView.enter', function(){
                GetActivityDetail.get({activityId:activityId}, function(data){
                    ElderUtil.checkResponseData(data);
                    $scope.detailActivityInfo = data.responseData;
                    GetActivityAttendStatus.get({activityId:activityId},function(data){
                        ElderUtil.checkResponseData(data);
                        $scope.param.activityAttendStatus = data.responseData;
                    })
                })
            });

            $scope.discuss = function(){
                $scope.param.operation = "discuss";
                $scope.param.page.requestData = activityId;
                console.log($scope.param.page);
                GetActivityDiscuss.save($scope.param.page,function(data){
                    ElderUtil.checkResponseData(data);
                    $scope.activityDiscussList = data.responseData;
                })
            }

            $scope.introduce = function(){
                $scope.param.operation = "introduce";
            }

            $scope.attendActivityGroupTalk = function(){
                //进入活动群聊圈
                connectWebViewJavascriptBridge(function() {
                    window.WebViewJavascriptBridge.callHandler('attendActivityGroupTalk',
                        $scope.detailActivityInfo.activityEasemobGroupID+";"+$scope.detailActivityInfo.activityName,function(responseData) {})
                })
            }

            $scope.doRefresh = function(){
                $scope.param.page.pageSize = $scope.param.page.pageSize + 5;
                GetActivityDiscuss.save($scope.param.page,function(data){
                    ElderUtil.checkResponseData(data);
                    $scope.activityDiscussList = data.responseData;
                    $scope.$broadcast('scroll.refreshComplete');
                })
            }

            $scope.attendDiscuss = function(){
                $scope.param.attendDiscuss = true;
            }

            $scope.cancelDiscuss = function(){
                $scope.param.discussContent="";
                $scope.param.attendDiscuss = false;
            }

            $scope.submitDiscuss = function(){

                if($scope.param.discussContent=="")
                {
                    alert("请输入评论内容再提交");
                }
                else
                {
                    CreateActivityDiscuss.save({discussContent:$scope.param.discussContent,activityId:activityId},
                        function(data){
                        ElderUtil.checkResponseData(data);
                        $scope.param.discussContent="";
                        GetActivityDiscuss.save($scope.param.page,function(data){
                            ElderUtil.checkResponseData(data);
                            $scope.activityDiscussList = data.responseData;
                        })
                    })
                    $scope.param.attendDiscuss = false;
                }
            }

            $scope.return = function(){
                connectWebViewJavascriptBridge(function() {
                    window.WebViewJavascriptBridge.callHandler('returnNative','',function(responseData) {});});
            }

        }])