angular.module('controllers',[]).controller('cmsPicturePutAwayCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','Global','$timeout','$filter','$http','AddBanner',
        function ($scope,$interval,$rootScope,$stateParams,$state,Global,$timeout,$filter,$http,AddBanner) {



            $scope.weight = [];
            $scope.app = $stateParams.app;
            $scope.name = $stateParams.name;
            $scope.banner = {
                bannerResourceID:$stateParams.id
            };

            for(var i = 1; i <= $stateParams.nums; i++){
                $scope.weight.push(i);
            }

            $timeout(function(){
                //calendar
                $(".date-picker").datepicker({
                    language: "zh-CN",
                    autoclose: true
                });
            },1000)

            //上架时间
            $scope.putAwayDate = 1;

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
                    $scope.banner.banner = 'http://yhllaoyou.oss-cn-beijing.aliyuncs.com/' + $scope.file[0].name;
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

// $scope.banner.banner = 'http://yhllaoyou.oss-cn-beijing.aliyuncs.com/timg (2).jpg';

            //保存并发布
            var onOff = true;
            $scope.save = function(){
                console.log($scope.banner)
                if(onOff){
                    if($scope.putAwayDate == 1){
                        $scope.banner.startDate = $filter('date')(new Date(), 'yyyy-MM-dd');
                    }
                    if($scope.banner.weights && $scope.banner.name && $scope.banner.introduce && $scope.banner.endDate){
                        if($scope.banner.startDate < $filter('date')(new Date(), 'yyyy-MM-dd')){
                            $scope.hintInf = '上架时间不能小时当前时间';
                        }
                        else if($scope.banner.startDate >= $scope.banner.endDate)
                        {
                            $scope.hintInf = '上架时间不能大于下架时间';
                        }
                        else
                        {
                            if($scope.banner.banner){
                                onOff = false;
                                AddBanner.save($scope.banner,function(data){
                                    console.log(data)
                                    if(data.result == Global.SUCCESS){
                                        $state.go('cmsPicture');
                                    }
                                    else
                                    {
                                        $scope.hintInf = data.errorInfo;
                                    }
                                })
                            }
                            else
                            {
                                $scope.hintInf = '请先上传banner图片';
                            }
                        }
                    }
                    else
                    {
                        $scope.hintInf = '资源信息不完整';
                    }
                }



            }



        }])