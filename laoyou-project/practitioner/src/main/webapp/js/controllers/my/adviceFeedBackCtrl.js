angular.module('controllers',[]).controller('adviceFeedBackCtrl',
    ['$scope','$rootScope','$stateParams','$state','FeedBack','Global',
        function ($scope,$rootScope,$stateParams,$state,FeedBack,Global) {
            /*表单提交*/
            $scope.submitForm = function(isValid){
                if(isValid){
                    FeedBack.save({userID:$rootScope.user.userID,feedback:$scope.adviceCont},function(data){
                        if(data.result == Global.SUCCESS){              
                            //反馈成功
                        }
                    })
                }
            }
        }])