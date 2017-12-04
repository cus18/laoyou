angular.module('controllers',[]).controller('limitsUsersCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','Global','$timeout','SearchUser',
        'AddUser','$http','GetUser','DeleteUser','SearchOffice','UpdateUser',
        function ($scope,$interval,$rootScope,$stateParams,$state,Global,$timeout,SearchUser,
                  AddUser,$http,GetUser,DeleteUser,SearchOffice,UpdateUser) {

            $scope.userInfo = {};
            $scope.loadPageList = function(){
                SearchUser.get({pageNo:$scope.pageNo,
                    pageSize:$scope.pageSize,
                    searchValue:$scope.param.searchValue
                },function(data){
                    $scope.response = data.responseData;
                    angular.forEach(data.responseData.list,function(data){
                        if(data.sysRoleID == 1){
                            data.sysRoleID = '超级管理员';
                        }
                        else if(data.sysRoleID == 2)
                        {
                            data.sysRoleID = '机构管理员';
                        }
                        else if(data.sysRoleID == 3)
                        {
                            data.sysRoleID = '医生';
                        }
                    })
                    $timeout(function(){
                        var len = $('#table tr').length;
                        for(var i = 1;i<len;i++){
                            $('#table tr:eq('+i+') td:first').text(i);
                        }
                    },500)
                })
            }

            //新增 修改机构
            $scope.officeNames = [];
            $scope.popup = function(type,id){
                $scope.usersAlert = true;
                SearchOffice.get({pageNo:1,pageSize:-1,searchValue:''},function(data){
                    $scope.officeNames = data.responseData.list;
                })

                if(type == 'new'){
                    $scope.type = 'new';
                    $scope.title = '新增';
                }
                else{
                    $scope.type = 'check';
                    $scope.title = '修改';
                    GetUser.get({ID:id},function(data){
                        $scope.userInfo = data.responseData;
                    })
                }
            }

            //关闭弹窗
            $scope.close = function()
            {
                $scope.userInfo = {};
                $scope.usersAlert = false;
            }

            //上传图片
            $scope.saveClick = function() {
                //禁用按钮
                $scope.imgDisabled = true;
                $scope.submitDisabled = true;
                var url = '/laoyouWeb/servlet/UploadFileServlet'; //接口路径
                var fd = new FormData();
                fd.append('key', new Date().getTime());
                fd.append('img', $scope.file[0]); //参数 img=后台定义上传字段名称 ； $scope.file[0] 内容
                $http.post(url, fd, {
                    transformRequest: angular.identity,
                    headers: {
                        'Content-Type': undefined
                    }
                }).success(function(data) {
                    if (data != 'success') {
                        alert(JSON.stringify('图片上传失败：' + $scope.file[0].name + '，请重新上传正确的文件或格式'));
                    } else {
                        alert(JSON.stringify('图片上传成功：' + $scope.file[0]. name));
                    }
                    $scope.userInfo.photo = 'http://yhllaoyou.oss-cn-beijing.aliyuncs.com/' + $scope.file[0].name;
                    //恢复按钮
                    $scope.imgDisabled = false;
                    $scope.submitDisabled = false;
                }).error(function(data) {
                    alert('服务器错误，文件导入失败！');
                    //恢复按钮
                    $scope.imgDisabled = false;
                    $scope.submitDisabled = false;
                });
            };

            /*保存*/
            var submitOnOff = true;        //防止多次提交
            $scope.subUsersForm = function(isValid)
            {
                if(isValid){
                    // if(submitOnOff) {
                        submitOnOff = false;
                        if($scope.type == 'new'){
                            if($scope.userInfo.photo){
                                AddUser.save($scope.userInfo,function(data){
                                    if(data.result == Global.SUCCESS){
                                        $scope.userInfo = {};
                                        $scope.usersAlert = false;
                                        $scope.loadPageList();
                                    }
                                })
                            }else{
                                alert('请先上传头像图片。')
                            }
                        }else if($scope.type == 'check'){
                            UpdateUser.save($scope.userInfo,function(data){
                                if(data.result == Global.SUCCESS){
                                    $scope.userInfo = {};
                                    $scope.usersAlert = false;
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
                DeleteUser.save({sysUserID:id},function(data){
                    if(data.result == Global.SUCCESS){
                        $scope.alertInfo = false;
                        $scope.loadPageList();
                    }
                })
            }



        }])