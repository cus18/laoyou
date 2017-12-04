angular.module('controllers',[]).controller('extendMessageCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','GetExtendMessage',
        function ($scope,$interval,$rootScope,$stateParams,$state,GetExtendMessage) {

            $scope.param = {
                page:{
                    pageNo:1,
                    pageSize:10,
                    orderType:1
                }
            };

            $scope.$on('$ionicView.enter', function(){
                GetExtendMessage.save($scope.param.page,
                    function(data){
                    $scope.extendMessageList = data.responseData;
                })
            });

            $scope.goDetail = function (message) {
                //去扩展消息详情页
                $state.go("extendMessageDetail",{extendMessageId:message.extendMessageId});
            }

            $scope.doRefresh = function(){
                $scope.param.page.pageSize = $scope.param.page.pageSize+10;
                GetExtendMessage.save($scope.param.page,
                    function(data){
                        $scope.extendMessageList = data.responseData;
                        $scope.$broadcast('scroll.refreshComplete');
                })
            }

            $scope.return = function(){
                connectWebViewJavascriptBridge(function() {
                    window.WebViewJavascriptBridge.callHandler('returnNative','',function(responseData) {});});
            }

        }])