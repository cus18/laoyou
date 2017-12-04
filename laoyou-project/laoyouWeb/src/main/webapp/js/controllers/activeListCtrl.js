angular.module('controllers',[]).controller('activeListCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','Global','$timeout','ActivityListByBackEnd','SearchOffice',
        function ($scope,$interval,$rootScope,$stateParams,$state,Global,$timeout,ActivityListByBackEnd,SearchOffice) {


            $scope.startDate = '';
            $scope.endDate = '';



            $scope.loadPageList = function(){
                //已发布的活动
                ActivityListByBackEnd.get({
                    status:'publish',
                    pageNo:$scope.pageNo,
                    pageSize:$scope.pageSize,
                    searchValue:$scope.param.searchValue,
                    startDate:$scope.startDate,
                    endDate:$scope.endDate
                },function(data){
                    if(data.result == Global.SUCCESS){
                        $scope.PublishResponse = data.responseData;
                        $scope.pageSize = angular.copy($scope.PublishResponse.limit);
                        $scope.param.pageFrom = ($scope.pageNo-1)*$scope.pageSize+1;
                        $scope.param.pageTo = ($scope.pageNo-1)*$scope.pageSize+$scope.pageSize;
                    }
                })
                //已结束的活动
                ActivityListByBackEnd.get({
                    status:'end',
                    pageNo:$scope.pageNo,
                    pageSize:$scope.pageSize,
                    searchValue:$scope.param.searchValue,
                    startDate:$scope.startDate,
                    endDate:$scope.endDate
                },function(data){
                    if(data.result == Global.SUCCESS){
                        $scope.endResponse = data.responseData;
                        $scope.pageSize = angular.copy($scope.endResponse.limit);
                        $scope.param.pageFrom = ($scope.pageNo-1)*$scope.pageSize+1;
                        $scope.param.pageTo = ($scope.pageNo-1)*$scope.pageSize+$scope.pageSize;
                    }
                })
            }



            //删除
            $scope.delete = function(type,id){
                $scope.alertInfo = true;
                $scope.delSure = function(){
                    if(type == 'publish'){
                        Delete.get({id:id},function(data){
                            if(data.result == Global.SUCCESS){
                                $scope.alertInfo = false;
                                $scope.loadPageList();
                            }
                        })
                    }
                    else if(type == 'end')
                    {
                        Delete.get({id:id},function(data){
                            if(data.result == Global.SUCCESS){
                                $scope.alertInfo = false;
                                $scope.loadPageList();
                            }
                        })
                    }
                }
                $scope.delCancle = function(){
                    $scope.alertInfo = false;
                }
            }

            //calendar
            $timeout(function(){
                $(".date-picker").datepicker({
                    language: "zh-CN",
                    autoclose: true
                });
            },1000)

        }])