angular.module('controllers',[]).controller('activityListCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','GetActivityList','ElderUtil',
        function ($scope,$interval,$rootScope,$stateParams,$state,GetActivityList,ElderUtil) {

            $scope.param = {
                page:{
                    pageNo:1,
                    orderType:1,
                    requestData:"0"
                }
            }

            $scope.$on('$ionicView.enter', function(){
                GetActivityList.save($scope.param.page,
                    function(data){
                    ElderUtil.checkResponseData(data);
                    $scope.activityList = data.responseData;
                })
            });

            $scope.doRefresh = function(){
                $scope.param.page.pageNo = $scope.param.page.pageNo+1;
                GetActivityList.save($scope.param.page,
                    function(data){
                        ElderUtil.checkResponseData(data);
                        $scope.activityList = data.responseData;
                        $scope.$broadcast('scroll.refreshComplete');
                    })
            }

            $scope.chooseActivity = function(type){
                if(type=="comm")
                {
                    $scope.param.page.requestData = "0";
                }
                else if(type=="hot")
                {
                    $scope.param.page.requestData = "2";
                }
                GetActivityList.save($scope.param.page, function(data){
                    ElderUtil.checkResponseData(data);
                    $scope.activityList = data.responseData;
                })
            }

            $scope.return = function(){
                connectWebViewJavascriptBridge(function() {
                    window.WebViewJavascriptBridge.callHandler('returnNative','',function(responseData) {});});
            }
        }])