angular.module('controllers',[]).controller('healthIndexCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','GetRelativeElderInfo',
        'GetOnGoingHealthServicePackageList','ElderUtil','GetOnlineCourseList',
        function ($scope,$interval,$rootScope,$stateParams,$state,GetRelativeElderInfo,
                  GetOnGoingHealthServicePackageList,ElderUtil,GetOnlineCourseList) {

            GetRelativeElderInfo.save({},function(data){
                console.log(data);
                ElderUtil.checkResponseData(data);
                $scope.relativeElderList = data.responseData;

                connectWebViewJavascriptBridge(function() {
                    window.WebViewJavascriptBridge.callHandler(
                        'getElderInfo','',function(responseData) {
                            var dataValue = JSON.parse(responseData);
                            $scope.elderId = dataValue.elderId;
                            $scope.elderName = dataValue.elderName;

                            //$scope.elderId = $scope.relativeElderList[0].elderID;
                            $scope.chooseRelativeElder($scope.elderId);
                        })
                })
            })


            $scope.chooseRelativeElder = function(elderId){
                angular.forEach($scope.relativeElderList,function(value,index,array){
                    if(value.elderID==elderId)
                    {
                        $scope.elderId = value.elderID;
                        $scope.elderName = value.elderName;
                        $scope.age = value.age;
                        if(value.gender == '1'){
                            $scope.gender = '男';
                        }
                        else if(value.gender == '2')
                        {
                            $scope.gender = '女';
                        }
                        else{
                            $scope.gender = value.gender;
                        }

                        //根据elderId获取用户的健康产品列表信息
                        GetOnGoingHealthServicePackageList.save({pageNo:"1", pageSize:"3", orderType:"1",orderBy:"0",
                            requestData:{elderId:$scope.elderId}}, function (data) {
                            ElderUtil.checkResponseData(data);
                            $scope.healthServicePackageList = data.responseData;
                        })

                        //将用户信息放入$rootScope中
                        $rootScope.rootElderId = angular.copy($scope.elderId);
                        $rootScope.rootElderName = angular.copy($scope.elderName);
                    }
                })
            }

            $scope.param = {
                page:{
                    pageNo:1,
                    pageSize:5,
                    orderType:1,
                    requestData:"health"
                }
            }

            //获取健康类的课程列表
            GetOnlineCourseList.save($scope.param.page,function(data){
                $scope.onlineCourseList = data.responseData;
            })

}])