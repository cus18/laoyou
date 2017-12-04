angular.module('controllers',[]).controller('memberCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','UserLogin','Global','$location','$anchorScroll',
        'GetMemberInfoList','DelMember',
        function ($scope,$interval,$rootScope,$stateParams,$state,UserLogin,Global,$location,$anchorScroll,
                  GetMemberInfoList,DelMember) {

        $location.hash('wrapper');
        $anchorScroll();

        $scope.addMember = false;

        $scope.newMember = function()
        {
            $scope.addMember = !$scope.addMember
        }

        $scope.singleAddMember = function()
        {
            $state.go("editMember",{type:'new'});
        }

        $scope.multiAddMember = function()
        {
            $state.go("uploadMultiMember");
        }

        if($rootScope.productServcieAction=="goPackageMemberList")
        {
            console.log($rootScope.productServicePackageName);
        }

        //获取会员列表
        $scope.loadPageList = function(){
            GetMemberInfoList.get({searchValue:$scope.param.searchValue, pageNo:$scope.pageNo, pageSize:$scope.pageSize},function(data){
                console.log(data)
                if(data.result == Global.SUCCESS){
                    $scope.response = data.responseData;
                    $scope.pageSize = angular.copy($scope.response.limit);
                    $scope.param.pageFrom = ($scope.pageNo-1)*$scope.pageSize+1;
                    $scope.param.pageTo = ($scope.pageNo-1)*$scope.pageSize+$scope.pageSize;

                    angular.forEach(data.responseData.list,function(data,index,array){
                        if(data.nurseName){
                            data.nurseName = data.nurseName.split(';').join('\n');
                        }
                        if(data.memberExtendData){
                            data.memberExtendData = data.memberExtendData.split(';').join('\n');
                        }
                    })
                }
            })
        }

        //删除
        $scope.delMember = function(id){
            $scope.alertInfo = true;
            $scope.delSure = function(){
                DelMember.get({id:id},function(data){
                    if(data.result == Global.SUCCESS){
                        $scope.alertInfo = false;
                        $scope.loadPageList();
                    }
                })
            }
            $scope.delCancle = function(){
                $scope.alertInfo = false;
            }
        }

}])