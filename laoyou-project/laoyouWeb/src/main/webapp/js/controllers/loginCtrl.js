angular.module('controllers',[]).controller('loginCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','UserLogin','Global','SendIdentifying',
        function ($scope,$interval,$rootScope,$stateParams,$state,UserLogin,Global,SendIdentifying) {

            $scope.login = {
                paraclass : 'getCodeBtn',
                errorHint : false,
                paraevent : true
            }

            /*
             *获取验证码
             */
            $scope.login.loginCode = function(){
                if($scope.login.paraevent){

                    SendIdentifying.save({'phoneNum':$scope.login.userPhone},function(data){
                        console.log(data)
                    });

                    var second = 59;
                    $scope.login.paracont = second + '秒后重发';
                    $scope.login.paraclass = 'null getCodeBtn';
                    var timer = $interval(function(){
                        if(second <=0){
                            $interval.cancel(timer);
                            second = 59;
                            $scope.login.paracont = '重发验证码';
                            $scope.login.paraclass = 'getCodeBtn';
                            $scope.login.paraevent = true;
                        }else{
                            second--;
                            $scope.login.paracont = second + '秒后重发';
                            $scope.login.paraevent = false;
                        }
                    },1000);
                }
            };


            /*
             *提交
             */
            $scope.submitForm = function(isValid){
                //is correct
                if(isValid){
                    $rootScope.user = {
                        userPhone : $scope.userPhone
                    };
                    UserLogin.save({phoneNum:$scope.login.userPhone,
                        identifyNum:$scope.login.userPhoneCode,source:'hospital'},function(data){
                        if(data.result == Global.SUCCESS)
                        {
                            $state.go("index");
                        }
                        else if(data.result == Global.FAILURE)
                        {
                            //验证码不正确
                            $scope.login.errorHint = true;
                        }

                    })
                }
            }

        }])