angular.module('controllers',[]).controller('videoCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','Global','$timeout',
        function ($scope,$interval,$rootScope,$stateParams,$state,Global,$timeout) {


            $scope.type = $stateParams.type;

            if($scope.type == 'liveVideo'){
                $scope.video = {
                    hospitalMenuTitle : 'liveVideo',
                    pageTitle : '全部直播',
                    listTitle : '直播列表',
                    newVideo : '发起直播',
                    placeholder:'直播主题'
                }



            }
            else if($scope.type == 'video')
            {
                $scope.video = {
                    hospitalMenuTitle : 'video',
                    pageTitle : '全部视频',
                    listTitle : '视频列表',
                    newVideo : '上传视频',
                    placeholder:'视频名称'
                }



            }

            $scope.loadPageList = function(){}





            //删除
            $scope.delFun = function(id){
                // DelMember.get({id:id},function(data){
                //     if(data.result == Global.SUCCESS){
                $scope.alertInfo = false;
                $scope.loadPageList();
                // }
                // })
            }

            //calendar
            $timeout(function(){
                $(".date-picker").datepicker({
                    language: "zh-CN",
                    autoclose: true
                });
            },1000)

        }])