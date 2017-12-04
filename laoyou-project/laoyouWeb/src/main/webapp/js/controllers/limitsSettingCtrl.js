angular.module('controllers',[]).controller('limitsSettingCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','Global','$timeout','SearchOffice',
        'AddOffice','UpdateOffice','DeleteOffice','GetOffice',
        function ($scope,$interval,$rootScope,$stateParams,$state,Global,$timeout,SearchOffice,
                  AddOffice,UpdateOffice,DeleteOffice,GetOffice) {


            $scope.loadPageList = function(){
                SearchOffice.get({
                    pageNo:$scope.pageNo,
                    pageSize:$scope.pageSize,
                    searchValue:$scope.param.searchValue
                },function(data){
                    console.log(data)
                    $scope.response = data.responseData;
                    $timeout(function(){
                        var len = $('#table tr').length;
                        for(var i = 1;i<len;i++){
                            $('#table tr:eq('+i+') td:first').text(i);
                        }
                    },500)
                })

            }


            //新增 修改机构
            $scope.popup = function(type,id){
                $scope.settingAlert = true;
                if(type == 'new'){
                    $scope.type = 'new';
                    $scope.title = '新增';
                    $scope.setting = {};
                }
                else if(type == 'edit')
                {
                    $scope.type = 'edit';
                    $scope.title = '修改';
                    GetOffice.get({ID:id},function(data){
                        console.log(id)
                        $scope.setting = data.responseData;

                        // var area = $scope.setting.area.split('-');
                        // $('#s_province').val(area[0]);
                        // $('#s_city').val(area[1]);
                        // $('#s_county').val(area[2]);
                        console.log(data)
                    })
                }
            }
            $scope.close = function()
            {
                $scope.settingAlert = false;
            }

            /*保存*/
            var submitOnOff = true;        //防止多次提交
            $scope.subSettingForm = function(isValid)
            {
                $scope.setting.area = $('#s_province').val() + '-' + $('#s_city').val() + '-' + $('#s_county').val();
                console.log($scope.setting)
                if(isValid){
                    // if(submitOnOff) {
                    submitOnOff = false;
                    if($scope.type == 'new'){
                        AddOffice.save($scope.setting,function(data){
                            console.log(data)
                            if(data.result == Global.SUCCESS){
                                $scope.loadPageList();
                                $scope.settingAlert = false;
                            }
                        })

                    }
                    else if($scope.type == 'edit')
                    {
                        UpdateOffice.save($scope.setting,function(data){
                            console.log(data)
                            if(data.result == Global.SUCCESS){
                                $scope.loadPageList();
                                $scope.settingAlert = false;
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
                DeleteOffice.save({ID:id},function(data){
                    console.log(id)
                    console.log(data)
                    if(data.result == Global.SUCCESS){
                        $scope.alertInfo = false;
                        $scope.loadPageList();
                    }
                })
            }



        }])