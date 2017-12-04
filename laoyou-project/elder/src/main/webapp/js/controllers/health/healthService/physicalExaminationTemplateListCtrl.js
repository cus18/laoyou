angular.module('controllers',[]).controller('physicalExaminationTemplateListCtrl',
    ['$scope','$rootScope','$stateParams','$state',
        'GetHealthArchivePhysicalExaminationTemplateList','$location','$anchorScroll',
        function ($scope,$rootScope,$stateParams,$state,
                  GetHealthArchivePhysicalExaminationTemplateList,$location,$anchorScroll) {

            $scope.loadingStatus = true;

            connectWebViewJavascriptBridge(function() {
                window.WebViewJavascriptBridge.callHandler(
                    'getElderInfo','',function(responseData) {
                        var dataValue = JSON.parse(responseData);
                        $scope.elderId = dataValue.elderId;
                        $scope.elderName = dataValue.elderName;
                    })
            })

            // 当前页数
            $scope.pageNo = 1;

            // 总页数
            $scope.pageSize = 15;

            // 防止重复加载
            $scope.busy = false;

            $scope.gotoBottom = function() {
                $location.hash('bottom');
                $anchorScroll();
            };

            $scope.loadMore = function() {

                if ($scope.busy) {
                    return false;
                }
                $scope.busy = true;
                GetHealthArchivePhysicalExaminationTemplateList.save({pageNo:$scope.pageNo,
                    pageSize:$scope.pageSize,
                    orderType:"1",orderBy:"0"},function(data){

                    $scope.loadingStatus = false;
                    $scope.busy = false;
                    if(data.responseData!=undefined)
                    {
                        if($scope.pageNo==1){
                            $scope.physicalExaminationTemplateList = data.responseData;
                        } else {
                            $scope.physicalExaminationTemplateList = $scope.physicalExaminationTemplateList.concat(data.responseData);
                            setTimeout(function() {
                                $scope.gotoBottom();
                            }, 50);
                        }
                        $scope.pageNo++;
                    }
                })
            };

            // 默认第一次加载数据
            $scope.loadMore();

        }])
