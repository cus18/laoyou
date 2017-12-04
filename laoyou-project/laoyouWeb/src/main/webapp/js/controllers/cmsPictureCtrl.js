angular.module('controllers',[]).controller('cmsPictureCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','Global','$timeout','BannerResourceList',
        function ($scope,$interval,$rootScope,$stateParams,$state,Global,$timeout,BannerResourceList) {

            $scope.loadPageList = function(){

                BannerResourceList.save({
                    searchValue:$scope.param.searchValue,
                    pageNo:$scope.pageNo,
                    pageSize:$scope.pageSize
                },function(data){
                    $scope.response = data.responseData;
                    console.log(data)
                })


            }




        }])