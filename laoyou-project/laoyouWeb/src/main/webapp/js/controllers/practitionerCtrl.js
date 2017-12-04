angular.module('controllers',[]).controller('practitionerCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','$timeout','GetDoctorList',
        'Create','GetDoctor','UpdateDoctor','Global','DelDoctor',
        function ($scope,$interval,$rootScope,$stateParams,$state,$timeout,GetDoctorList,
                  Create,GetDoctor,UpdateDoctor,Global,DelDoctor) {

            //获取医护人员信息列表
            $scope.loadPageList = function(){
                GetDoctorList.get({searchValue:$scope.param.searchValue, pageNo:$scope.pageNo, pageSize:$scope.pageSize,type:0},function(data){
                    if(data.result == Global.SUCCESS){
                        $scope.response = data.responseData;
                        $scope.pageSize = angular.copy($scope.response.limit);
                        $scope.param.pageFrom = ($scope.pageNo-1)*$scope.pageSize+1;
                        $scope.param.pageTo = ($scope.pageNo-1)*$scope.pageSize+$scope.pageSize;
                    }
                })
            }

            $scope.newPractitioner = function()
            {
                $scope.addPractitioner = !$scope.addPractitioner
            }

            $scope.singleAddPractitioner = function()
            {
                $scope.practitionerInf = {};
                $scope.singlePractitioner = !$scope.singlePractitioner;
                $scope.addPractitioner = false;
                $scope.type = 'new';
                $scope.title = '添加医护人员';
            }

            $scope.editPractitioner = function(id){
                $scope.singlePractitioner = !$scope.singlePractitioner;
                $scope.type = 'edit';
                $scope.title = '修改医护人员信息';
                GetDoctor.get({id:id},function(data){
                    if(data.responseData){
                        $scope.practitionerInf = data.responseData;
                        switch (data.responseData.gender){
                            case '1':
                                $scope.practitionerInf.gender = '男';
                                break;
                            case '2':
                                $scope.practitionerInf.gender = '女';
                                break;
                        }
                        switch (data.responseData.type){
                            case '1':
                                $scope.practitionerInf.type = '医生';
                                break;
                            case '2':
                                $scope.practitionerInf.type = '护士';
                                break;
                        }
                    }
                    console.log(data)
                })
            }

            $scope.multiAddPractitioner = function(){
                $state.go("uploadMultiPractitioner");
            }



            /*保存*/
            var submitOnOff = true;        //防止多次提交
            $scope.subPractitionerForm = function(isValid)
            {
                if(isValid){
                    // if(submitOnOff) {
                        submitOnOff = false;
                        if($scope.practitionerInf.gender == '男'){
                            $scope.practitionerInf.gender = '1';
                        }else if($scope.practitionerInf.gender == '女'){
                            $scope.practitionerInf.gender = '2';
                        }
                        if($scope.practitionerInf.type == '医生'){
                            $scope.practitionerInf.type = '1';
                        }else if($scope.practitionerInf.type == '护士'){
                            $scope.practitionerInf.type = '2';
                        }
                        console.log($scope.practitionerInf)
                        if($scope.type == 'new'){
                            Create.save($scope.practitionerInf, function (data) {
                                console.log(data)
                                if(data.result == Global.SUCCESS){
                                    $scope.singlePractitioner = false;
                                    $scope.loadPageList();
                                }
                            })
                        }else if($scope.type == 'edit'){
                            UpdateDoctor.save($scope.practitionerInf, function (data) {
                                console.log(data)
                                if(data.result == Global.SUCCESS){
                                    $scope.singlePractitioner = false;
                                    $scope.loadPageList();
                                }
                            })
                        }
                    // }
                }else{
                    alert('信息不完整')
                }
            }

            //删除
            $scope.delFun = function(id){
                DelDoctor.get({id:id},function(data){
                    if(data.result == Global.SUCCESS){
                        $scope.alertInfo = false;
                        $scope.loadPageList();
                    }
                })
            }


        }])