angular.module('controllers',[]).controller('attendActivityCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','GetRelativeElderInfo',
        'GetActivityDetail','JoinActivity','Global','ElderUtil',
        function ($scope,$interval,$rootScope,$stateParams,$state,GetRelativeElderInfo,
                  GetActivityDetail,JoinActivity,Global,ElderUtil) {


            $scope.$on('$ionicView.enter', function(){

                console.log();
            });

        }])