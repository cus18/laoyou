angular.module('controllers',[]).controller('courseListCtrl',
    ['$scope','$rootScope','$stateParams','$state','GetRecentLiveBroadCast',
        'GetHistoryLiveBroadCast','GetOnlineCourseList','ElderUtil',
        function ($scope,$rootScope,$stateParams,$state,GetRecentLiveBroadCast,
                  GetHistoryLiveBroadCast,GetOnlineCourseList,ElderUtil) {

            $scope.param = {
                page:{
                    pageNo:1,
                    pageSize:10,
                    orderType:1,
                    requestData:""
                },
                type : $stateParams.type
            }

            if($scope.param.type=="live")
            {
                console.log($scope.param.type)
            }
            else if($scope.param.type=="vod")
            {
                GetOnlineCourseList.save($scope.param.page,function(data){
                    ElderUtil.checkResponseData(data);
                    $scope.onlineCourseList = data.responseData;
                })
            }

            $scope.doRefresh = function(){
                $scope.param.page.pageSize = $scope.param.page.pageSize+10;
                if($scope.param.type=="live")
                {
                    console.log($scope.param.type);
                    $scope.$broadcast('scroll.refreshComplete');
                }
                else if($scope.param.type=="vod")
                {
                    GetOnlineCourseList.save($scope.param.page,function(data){
                        ElderUtil.checkResponseData(data);
                        $scope.onlineCourseList = data.responseData;
                        $scope.$broadcast('scroll.refreshComplete');
                    })
                }
            }

            $scope.return = function(){
                connectWebViewJavascriptBridge(function() {
                    window.WebViewJavascriptBridge.callHandler('returnNative','',function(responseData) {});});
            }

        }])