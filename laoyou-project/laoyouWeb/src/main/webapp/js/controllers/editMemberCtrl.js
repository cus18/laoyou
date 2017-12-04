angular.module('controllers',[]).controller('editMemberCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','UserLogin','Global','$location',
        '$anchorScroll','AddMember','GetMemberInfo','$filter','UpdateMember',
        function ($scope,$interval,$rootScope,$stateParams,$state,UserLogin,Global,$location,
                  $anchorScroll,AddMember,GetMemberInfo,$filter,UpdateMember){

            $location.hash('wrapper');
            $anchorScroll();

            $scope.singleAddMemberCancel = function()
            {
                $state.go("member");
            }

            $scope.singleMember = {};
            $scope.elderId = $stateParams.elderId;
            $scope.type = $stateParams.type;


            //calendar
            $(".datepicker").datepicker({
                language: "zh-CN",
                autoclose: true,//选中之后自动隐藏日期选择框
                format: "yyyy-mm-dd"
            });

            if($scope.type == 'edit'){
                $scope.pageTitle = '编辑会员';
                GetMemberInfo.get({id:$scope.elderId},function(data){
                    console.log(data)
                    if(data.responseData){
                        $scope.singleMember = data.responseData;
                        $scope.singleMember.birthday = $filter('date')(new Date(data.responseData.birthday),'yyyy-MM-dd');

                    }
                })
            }else if($scope.type == 'new'){
                $scope.pageTitle = '新增会员';
            }



            /*保存*/
            var submitOnOff = true;        //防止多次提交
            $scope.singleAddMemberSubmit = function(Valid){
                if(Valid){
                    // if(submitOnOff){
                        submitOnOff = false;
                        console.log($scope.singleMember);
                        if($scope.type == 'new'){
                            AddMember.save($scope.singleMember,function(data){
                                console.log(data)
                                if(data.result == Global.SUCCESS){
                                    $state.go('member')
                                }
                            })
                        }
                        else if($scope.type == 'edit'){
                            UpdateMember.save($scope.singleMember,function(data){
                                console.log(data);
                                if(data.result == Global.SUCCESS){
                                    $state.go('member')
                                }
                            })
                        }

                    // }
                }else{
                    alert('信息不完整')
                }
            }

        }])