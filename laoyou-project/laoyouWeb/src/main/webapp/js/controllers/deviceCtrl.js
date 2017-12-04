angular.module('controllers',[]).controller('deviceCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','UserLogin','Global','DeviceList','DeviceStatistic','OperDevice',
        '$timeout',
        function ($scope,$interval,$rootScope,$stateParams,$state,UserLogin,Global,DeviceList,DeviceStatistic,OperDevice,
                  $timeout) {


            $scope.loadPageList = function(){
                DeviceList.get({searchValue:encodeURI($scope.param.searchValue), pageNo:$scope.pageNo, pageSize:$scope.pageSize},function(data){
                    if(data.result == Global.SUCCESS){
                        $scope.response = data.responseData;
                        $scope.pageSize = angular.copy($scope.response.limit);
                        $scope.param.pageFrom = ($scope.pageNo-1)*$scope.pageSize+1;
                        $scope.param.pageTo = ($scope.pageNo-1)*$scope.pageSize+$scope.pageSize;
                    }
                })
            }

            DeviceStatistic.get(function(data){
                $scope.deviceStatisicList = data.responseData;
            })

            $scope.delDeviceInf = function(val)
            {
                OperDevice.save({id:val, oper:"del"},function(data){
                    if(data.result==Global.SUCCESS){
                        $timeout(function() {
                            $scope.loadPageList();
                        }, 1000);
                    }
                })
            }

            $scope.deviceParam = {
                deviceType : "血糖仪"
            };
            $scope.deviceTypes = ["血糖仪","血压计"];


            $scope.newDeviceInf = function()
            {
                $scope.deviceInf = !$scope.deviceInf;
            }

            $scope.CreateDeviceInf = function(val)
            {
                if(val=="add")
                {
                    OperDevice.save({type:$scope.deviceParam.deviceType,name:$scope.deviceParam.deviceName,
                                    deviceId:$scope.deviceParam.deviceId,remarks:$scope.deviceParam.remarks,
                                    memberId:$scope.deviceParam.memberId,oper:"add"
                                    },function(data){
                        if(data.result==Global.SUCCESS){
                            DeviceStatistic.get(function(data){
                                $scope.deviceStatisicList = data.responseData;
                            });
                            $scope.loadPageList();
                        }
                        else if(data.result==Global.FAILURE)
                        {
                            alert("设备录入失败");
                        }
                        $scope.deviceInf = !$scope.deviceInf;
                    })
                }
                else if(val=="cancel")
                {
                    $scope.deviceInf = !$scope.deviceInf;
                }

            }

        }])