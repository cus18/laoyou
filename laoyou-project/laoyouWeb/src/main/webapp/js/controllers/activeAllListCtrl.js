angular.module('controllers',[]).controller('activeAllListCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','Global','$timeout','SearchOffice',
        'GetAllActivityListByBackEnd',
        function ($scope,$interval,$rootScope,$stateParams,$state,Global,$timeout,SearchOffice,
                  GetAllActivityListByBackEnd) {


            $scope.startDate = '';
            $scope.endDate = '';
            $scope.office = {id:''};



            $scope.loadPageList = function(){

                //全部活动列表
                SearchOffice.get({searchValue:'',pageSize:'-1',pageNo:'1'},function(data){
                    $scope.officeList = data.responseData;
                })
                GetAllActivityListByBackEnd.save({
                    hospitalID:$scope.office.id,
                    activityName:$scope.param.searchValue,
                    pageNo:$scope.pageNo,
                    pageSize:$scope.pageSize,
                    activityStartDate:$scope.startDate,
                    activityEndDate:$scope.endDate
                },function(data){
                    console.log(data)
                    if(data.result == Global.SUCCESS){
                        angular.forEach(data.responseData.list,function(data){
                            if(data.activityStatus == 'end'){
                                data.activityStatus = '已结束';
                            }
                            else if(data.activityStatus == 'waiting')
                            {
                                data.activityStatus = '进行中';
                            }
                            else
                            {
                                data.activityStatus = '未开始';
                            }
                        })
                        $scope.response = data.responseData;
                        $scope.pageSize = angular.copy($scope.response.limit);
                        $scope.param.pageFrom = ($scope.pageNo-1)*$scope.pageSize+1;
                        $scope.param.pageTo = ($scope.pageNo-1)*$scope.pageSize+$scope.pageSize;
                    }
                })
            }



            //删除
            $scope.delete = function(type,id){
                $scope.alertInfo = true;
                $scope.delSure = function(){
                    if(type == 'all')
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