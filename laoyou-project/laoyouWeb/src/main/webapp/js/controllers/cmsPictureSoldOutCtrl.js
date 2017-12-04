angular.module('controllers',[]).controller('cmsPictureSoldOutCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','Global','$window','$timeout','GetBanner','BannerTurnOff',
        function ($scope,$interval,$rootScope,$stateParams,$state,Global,$window,$timeout,GetBanner,BannerTurnOff) {


            $scope.id = $stateParams.id;

            GetBanner.save({bannerResourceID:$scope.id},function(data){
                console.log(data)
                $scope.response = data.responseData;
            })


            //删除
            $scope.delFun = function(id){
                BannerTurnOff.save({id:id},function(data){
                    if(data.result == Global.SUCCESS){
                        $scope.alertInfo = false;
                        $window.location.reload();
                    }
                })
            }


        }])