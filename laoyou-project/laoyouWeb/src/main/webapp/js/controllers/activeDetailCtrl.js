angular.module('controllers',[]).controller('activeDetailCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','$filter','$http','Global','$timeout','CreateActivity',
        'CreateActivityEasemobGroup','ActivityDetail',
        function ($scope,$interval,$rootScope,$stateParams,$state,$filter,$http,Global,$timeout,CreateActivity,
                  CreateActivityEasemobGroup,ActivityDetail) {


        $scope.type = $stateParams.type;
        $scope.activityId = $stateParams.activityId;
        $scope.active = {};
        $scope.response={};


        if($scope.type == 'new'){
            //标签
            $scope.labelFocus = function(){
                $scope.labelShow = true;
            }
            $scope.labelSure = function(){
                $scope.labelShow = false;
            }
            $scope.labelCancel = function(){
                $scope.activeLabel = '';
                $scope.labelShow = false;
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
                    $scope.active.banner = 'http://yhllaoyou.oss-cn-beijing.aliyuncs.com/' + $scope.file[0].name;
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
            //保存
            var submitOnOff = true;        //防止多次提交
            $scope.createActive = function(isValid){
                if(isValid) {
                    if($scope.active.banner){
                        if(new Date($scope.response.activityStartDate).getTime() < new Date($scope.response.activityEndDate).getTime()){
                            if(submitOnOff){
                                submitOnOff = false;
                                $scope.response.address = $('#s_province').val() + $('#s_city').val() + $('#s_county').val() + $scope.response.activityAddress;
                                $scope.response.banner = $scope.active.banner;
                                console.log($scope.response)
                                $scope.response.endDate=$scope.response.activityEndDate;
                                $scope.response.startDate=$scope.response.activityStartDate;
                                $scope.response.title=$scope.response.activityName;
                                CreateActivity.save($scope.response, function (data) {
                                    if(data.result == Global.SUCCESS){
                                        $state.go("activeList");
                                    }
                                    CreateActivityEasemobGroup.get({activityID:data.responseData},function(data){

                                    })
                                })
                            }
                        }
                        else
                        {
                            alert('活动结束时间必须大于活动开始时间。')
                        }
                    }
                    else{
                        alert('请先上传活动封面图片。')
                    }
                }
            }

            $timeout(function(){
                //calendar
                $(".date-picker").datetimepicker({
                    language: "zh-CN",
                    autoclose: true,
                    startDate: new Date()
                });
                //地址联动
                _init_area();
            },1000)
        }
        else if($scope.type == 'check')
        {
            //获取活动信息
            ActivityDetail.get({activityId:$scope.activityId},function(data){
                if(data.result == Global.SUCCESS){
                    $scope.response = data.responseData;
                    $scope.response.activityStartDate = $filter('date')(data.responseData.activityStartDate,'yyyy-MM-dd HH:mm');
                    $scope.response.activityEndDate = $filter('date')(data.responseData.activityEndDate,'yyyy-MM-dd HH:mm');
                }
            })
        }






    }])