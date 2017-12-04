angular.module('controllers',[]).controller('videoDetailCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','Global','$timeout',
        function ($scope,$interval,$rootScope,$stateParams,$state,Global,$timeout) {


            $scope.type = $stateParams.type;
            $scope.loadPageList = function(){}



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



            $timeout(function(){
                //calendar
                $(".date-picker").datepicker({
                    language: "zh-CN",
                    autoclose: true
                });
                //上传活动图片
                $('#uploadInp').on('change',function(){
                    var mine = $('#uploadInp').val();
                    if(mine != ''){
                        mine = mine.toLowerCase().substring(mine.lastIndexOf('.'));
                        if(mine != '.jpg' && mine != '.png' && mine != '.jpeg' && mine != '.gif' && mine != '.bmp' && mine != '.webp'){
                            alert('暂不支持' + mine + '格式的图片，请上传(jpg/png/gif/bmp/webp)的图片');
                        }else{
                            var s = document.getElementById('uploadInp').files[0].size;
                            if( s > 1024*1024*5 ){
                                alert('图片大小不能超过5M');
                            }else{
                                var f = document.getElementById('uploadInp').files[0];
                                var src = window.URL.createObjectURL(f);
                                $('#uploadImg').attr('src',src);
                            }
                        }
                    }
                })
            },1000)

        }])