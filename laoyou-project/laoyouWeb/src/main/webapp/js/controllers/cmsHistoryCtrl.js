angular.module('controllers',[]).controller('cmsHistoryCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','Global','$timeout',
        function ($scope,$interval,$rootScope,$stateParams,$state,Global,$timeout) {


            $scope.loadPageList = function(){}




            //calendar
            $timeout(function(){
                $(".date-picker").datepicker({
                    language: "zh-CN",
                    autoclose: true
                });
            },1000)

            //查看详情
            $scope.popup = function(type){
                $scope.newsAlert = true;

            }
            $scope.close = function()
            {
                $scope.newsAlert = false;
            }

        }])